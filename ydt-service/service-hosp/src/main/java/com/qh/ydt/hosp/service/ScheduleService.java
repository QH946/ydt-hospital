package com.qh.ydt.hosp.service;


import com.qh.ydt.model.model.hosp.Schedule;
import com.qh.ydt.model.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface ScheduleService {

    /**
     * 上传排班
     *
     * @param paramMap 参数映射
     */
    void saveSchedule(Map<String, Object> paramMap);


    /**
     * 分页查询排班
     *
     * @param page            页面
     * @param limit           限制
     * @param scheduleQueryVo 进度查询签证官
     * @return {@link Page}<{@link Schedule}>
     */
    Page<Schedule> findPageSchedule(int page, int limit, ScheduleQueryVo scheduleQueryVo);


    /**
     * 删除排班
     *
     * @param paramMap 参数映射
     * @return int
     */
    int removeSchedule(Map<String, Object> paramMap);


}
