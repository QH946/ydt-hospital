package com.qh.ydt.hosp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qh.ydt.hosp.mapper.ScheduleMapper;
import com.qh.ydt.hosp.repository.ScheduleRepository;
import com.qh.ydt.hosp.service.ScheduleService;
import com.qh.ydt.model.model.hosp.Schedule;
import com.qh.ydt.model.vo.hosp.ScheduleQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class ScheduleServiceImpl extends
        ServiceImpl<ScheduleMapper, Schedule> implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;


    @Override
    public void saveSchedule(Map<String, Object> parameterMap) {
        Schedule schedule = JSONObject.parseObject(JSONObject.toJSONString(parameterMap), Schedule.class);
        Schedule targetSchedule = scheduleRepository.getScheduleByHoscodeAndHosScheduleId(schedule.getHoscode(), schedule.getHosScheduleId());
        if (null != targetSchedule) {
            BeanUtil.copyProperties(schedule, targetSchedule);
            scheduleRepository.save(targetSchedule);
        } else {
            schedule.setCreateTime(new Date());
            schedule.setUpdateTime(new Date());
            schedule.setIsDeleted(0);
            scheduleRepository.save(schedule);

        }
    }

    @Override
    public Page<Schedule> findPageSchedule(int pageInt, int limitInt, ScheduleQueryVo scheduleQueryVo) {
        PageRequest pageRequest = PageRequest.of(pageInt - 1, limitInt, Sort.by(Sort.Direction.DESC, "createTime"));
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING)
                .withIgnoreCase();
        Schedule schedule = BeanUtil.copyProperties(scheduleQueryVo, Schedule.class);
        Example<Schedule> scheduleExample = Example.of(schedule, exampleMatcher);
        return scheduleRepository.findAll(scheduleExample, pageRequest);
    }

    @Override
    public int removeSchedule(Map<String, Object> paramMap) {
        String hoscode = (String) paramMap.get("hoscode");
        String hosScheduleId = (String) paramMap.get("hosScheduleId");
        return scheduleRepository.deleteScheduleByHoscodeAndHosScheduleId(hoscode, hosScheduleId);
    }


}
