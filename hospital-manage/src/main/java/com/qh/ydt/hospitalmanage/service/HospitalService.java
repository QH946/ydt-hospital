package com.qh.ydt.hospitalmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qh.ydt.hospitalmanage.entity.HospitalSet;

import java.util.Map;

public interface HospitalService extends IService<HospitalSet> {

    HospitalSet getFirst();

    /**
     * 预约下单
     * @param paramMap
     * @return
     */
    Map<String, Object> submitOrder(Map<String, Object> paramMap);

    /**
     * 更新支付状态
     * @param paramMap
     */
    void updatePayStatus(Map<String, Object> paramMap);

    /**
     * 更新取消预约状态
     * @param paramMap
     */
    void updateCancelStatus(Map<String, Object> paramMap);


}
