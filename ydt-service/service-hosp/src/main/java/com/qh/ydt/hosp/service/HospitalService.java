package com.qh.ydt.hosp.service;

import com.qh.ydt.model.model.hosp.Hospital;

import java.util.Map;

public interface HospitalService {

    /**
     * 上传医院
     *
     * @param paramMap 参数映射
     */
    void saveHospital(Map<String, Object> paramMap);

    /**
     * 根据医院编号查询
     *
     * @param hoscode hoscode
     * @return {@link Hospital}
     */
    Hospital findHospitalByHoscode(String hoscode);




}
