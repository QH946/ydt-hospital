package com.qh.ydt.hosp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qh.ydt.model.model.hosp.HospitalSet;

public interface HospitalSetService extends IService<HospitalSet> {


    /**
     * 根据传递的医院编码查询签名
     *
     * @param hoscode hoscode
     * @return {@link String}
     */
    String getSignKey(String hoscode);


}
