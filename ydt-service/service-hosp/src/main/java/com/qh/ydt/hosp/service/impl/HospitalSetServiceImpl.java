package com.qh.ydt.hosp.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qh.ydt.common_utils.exception.HospitalException;
import com.qh.ydt.common_utils.result.ResultCodeEnum;
import com.qh.ydt.hosp.mapper.HospitalSetMapper;
import com.qh.ydt.hosp.service.HospitalSetService;
import com.qh.ydt.model.model.hosp.HospitalSet;
import org.springframework.stereotype.Service;

@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {

    @Override
    public String getSignKey(String hoscode) {
        HospitalSet hospitalSet = baseMapper.selectOne(new QueryWrapper<HospitalSet>()
                .eq("hoscode", hoscode));
        if (hospitalSet == null) {
            throw new HospitalException(ResultCodeEnum.DATA_ERROR);
        }
        return hospitalSet.getSignKey();
    }

}
