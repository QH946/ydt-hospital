package com.qh.ydt.hosp.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qh.ydt.hosp.repository.HospitalRepository;
import com.qh.ydt.hosp.service.HospitalService;
import com.qh.ydt.model.model.hosp.Hospital;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    HospitalRepository hospitalRepository;
    ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void saveHospital(Map<String, Object> map) {
        Hospital hospital = objectMapper.readValue(objectMapper.writeValueAsString(map), Hospital.class);
        // 判断是否存在
        Hospital targetHospital = hospitalRepository.findHospitalByHoscode(hospital.getHoscode());
        // 存在, 更新
        if (null != targetHospital) {
            hospital.setStatus(targetHospital.getStatus());
            hospital.setCreateTime(targetHospital.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
        } else {
            // 不存在, 新增
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
        }
        hospitalRepository.save(hospital);
    }

    @Override
    public Hospital findHospitalByHoscode(String hoscode) {
        return hospitalRepository.findHospitalByHoscode(hoscode);
    }

}