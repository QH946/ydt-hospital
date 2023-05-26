package com.qh.ydt.hosp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qh.ydt.hosp.repository.DepartmentRepository;
import com.qh.ydt.hosp.service.DepartmentService;
import com.qh.ydt.model.model.hosp.Department;
import com.qh.ydt.model.vo.hosp.DepartmentQueryVo;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

     ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public void saveDepartment(Map<String, Object> parameterMap) {
        String str = objectMapper.writeValueAsString(parameterMap);
        Department department = objectMapper.readValue(str, Department.class);
        String hoscode = (String) parameterMap.get("hoscode");
        String decode = (String) parameterMap.get("depcode");
        Department targetDepartment = getDepartmentByHoscodeAndDepcode(hoscode, decode);
        if (targetDepartment != null) {
            // 如果不等于null, 则更新
            BeanUtil.copyProperties(department, targetDepartment);
            departmentRepository.save(targetDepartment);
        } else {
            department.setCreateTime(new Date());
            department.setUpdateTime(new Date());
            department.setIsDeleted(0);
            departmentRepository.save(department);
        }
    }

    public Department getDepartmentByHoscodeAndDepcode(String hoscode, String decode) {
        return departmentRepository.findDepartmentByHoscodeAndDepcode(hoscode, decode);
    }

    @Override
    public Page<Department> findPageDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo) {
        // 创建Pageable对象，设置当前页和每页记录数
        //0是第一页
        Pageable pageable = PageRequest.of(page-1,limit);
        // 创建匹配器
        Department department = new Department();
        BeanUtils.copyProperties(departmentQueryVo,department);
        department.setIsDeleted(0);
        ExampleMatcher matcher = ExampleMatcher.matching()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
            .withIgnoreCase(true);
        Example<Department> example = Example.of(department,matcher);
        return departmentRepository.findAll(example, pageable);
    }

    //删除科室接口
    @Override
    public int removeDep(String hoscode, String depcode) {
        return departmentRepository.deleteDepartmentByHoscodeAndDepcode(hoscode, depcode);
    }


}
