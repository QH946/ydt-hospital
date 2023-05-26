package com.qh.ydt.hosp.service;


import com.qh.ydt.model.model.hosp.Department;
import com.qh.ydt.model.vo.hosp.DepartmentQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface DepartmentService {

    /**
     * 上传科室
     *
     * @param paramMap 参数映射
     */
    void saveDepartment(Map<String, Object> paramMap);


    /**
     * 查询科室
     *
     * @param page              页面
     * @param limit             限制
     * @param departmentQueryVo 部门查询签证官
     * @return {@link Page}<{@link Department}>
     */
    Page<Department> findPageDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo);


    /**
     * 删除科室
     *
     * @param hoscode hoscode
     * @param depcode depcode
     */
    int removeDep(String hoscode, String depcode);



}
