package com.qh.ydt.hosp.repository;


import com.qh.ydt.model.model.hosp.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 医院库
 *
 * @author 清欢
 * @date 2023/05/26  17:02:56
 */
@Repository
public interface HospitalRepository extends MongoRepository<Hospital,String> {

    /**
     * 根据Hoscode的值查询医院
     *
     * @param hoscode hoscode
     * @return {@link Hospital}
     */
    Hospital findHospitalByHoscode(String hoscode);



}
