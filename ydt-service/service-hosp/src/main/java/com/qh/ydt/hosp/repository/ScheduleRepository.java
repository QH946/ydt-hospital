package com.qh.ydt.hosp.repository;


import com.qh.ydt.model.model.hosp.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 安排仓库
 *
 * @author 清欢
 * @date 2023/05/26  17:04:20
 */
@Repository
public interface ScheduleRepository extends MongoRepository<Schedule, String> {
    Schedule getScheduleByHoscodeAndHosScheduleId(@Param("hoscode") String hoscode,
                                                  @Param("hosScheduleId") String hosScheduleId);

    int deleteScheduleByHoscodeAndHosScheduleId(@Param("hoscode") String hoscode,
                                                @Param("hosScheduleId") String hosScheduleId);

}
