package com.qh.ydt.hosp.repository;


import com.qh.ydt.model.model.hosp.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 部存储库
 *
 * @author 清欢
 * @date 2023/05/26  17:03:00
 */
@Repository
public interface DepartmentRepository extends MongoRepository<Department,String> {

    Department findDepartmentByHoscodeAndDepcode(@Param("hoscode")String hoscode,
                                                 @Param("depcode")String depcode);

    int deleteDepartmentByHoscodeAndDepcode(@Param("hoscode")String hoscode,
                                            @Param("depcode")String depcode);
}
