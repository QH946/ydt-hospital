package com.qh.ydt.hosp.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qh.ydt.common_utils.result.Result;
import com.qh.ydt.hosp.service.HospitalSetService;
import com.qh.ydt.model.model.hosp.HospitalSet;
import com.qh.ydt.model.vo.hosp.HospitalSetQueryVo;
import com.qh.ydt.service_utils.utils.MD5;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;


/**
 * 医院设置
 *
 * @author 清欢
 * @date 2023/05/16  12:47:22
 */
@Slf4j
@Api(tags = "医院设置信息管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {
    @Autowired
    private HospitalSetService hospitalSetService;

    /**
     * 查找所有医院设置信息
     *
     * @return
     */
    //http:localhost:8201/admin/hosp/hospitalSet/findAll
    @ApiOperation(value = "获取所有医院设置信息")
    @GetMapping("/findAll")
    public Result findAll() {
        List<HospitalSet> hospitalSetList = hospitalSetService.list();
        return Result.ok(hospitalSetList);
    }

    /**
     * 逻辑删除HospSet
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id逻辑删除id信息")
    @DeleteMapping("{id}")
    public Result DeleteById(@PathVariable Long id) {
        boolean remove = hospitalSetService.removeById(id);
        return remove ? Result.ok() : Result.fail();
    }

    /**
     * 分页接口
     * 参数：当前页，大小，参数对象（医院名称，医院代号）
     *
     * @param current
     * @param limit
     * @param hospitalSetQueryVo
     * @return
     */
    //使用@RequestBody后使用POSTMapping获取请求的值
    @ApiOperation(value = "分页查询")
    @PostMapping("/page/{current}/{limit}")
    public Result pageAll(@PathVariable Integer current,
                          @PathVariable Integer limit,
                          @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo) {
        log.info("分页查询接口调用" + LocalTime.now());
        Page<HospitalSet> page = new Page<>(current, limit);
        //条件构造器
        LambdaQueryWrapper<HospitalSet> queryWrapper = new LambdaQueryWrapper<>();
        //模糊查询,lambdaQueryWrapper先判空
        if (hospitalSetQueryVo.getHosname() != null && !hospitalSetQueryVo.getHosname().equals("")) {
            queryWrapper.like(HospitalSet::getHosname, hospitalSetQueryVo.getHosname());
        }
        if (hospitalSetQueryVo.getHoscode() != null && !hospitalSetQueryVo.getHoscode().equals("")) {
            queryWrapper.eq(HospitalSet::getHoscode, hospitalSetQueryVo.getHoscode());
        }
        Page<HospitalSet> hospitalSetPage = hospitalSetService.page(page, queryWrapper);
        return Result.ok(hospitalSetPage);
    }

    /**
     * 添加医院信息
     *
     * @param hospitalSet 医院设置
     * @return {@link Result}
     */
    @ApiOperation(value = "添加医院设置信息")
    @PostMapping("/add")
    public Result addHospital(@RequestBody HospitalSet hospitalSet) {
        //1可使用，0不可使用
        hospitalSet.setStatus(1);
        Random random = new Random();
        //当前时间戳＋随机数+MD5
        String encrypt = MD5.encrypt(System.currentTimeMillis() + String.valueOf(random.nextInt(1000)));
        hospitalSet.setSignKey(encrypt);
        boolean save = hospitalSetService.save(hospitalSet);
        return save ? Result.ok() : Result.fail();
    }

    /**
     * 根据id查找医院信息
     *
     * @param id id
     * @return {@link Result}
     */
    @ApiOperation(value = "根据id查找医院信息")
    @GetMapping("{id}")
    public Result findById(@PathVariable Long id) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        return Result.ok(hospitalSet);
    }

    /**
     * 根据id更新医院信息
     *
     * @param hospitalSet 医院设置
     * @return {@link Result}
     */
    @ApiOperation(value = "根据id更新医院信息")
    @PutMapping("/update")
    public Result updateHospSet(@RequestBody HospitalSet hospitalSet) {
        boolean update = hospitalSetService.updateById(hospitalSet);
        return update ? Result.ok() : Result.fail();
    }

    /**
     * 根据id批量删除医院信息
     *
     * @param idList
     * @return {@link Result}
     */
    @ApiOperation(value = "根据数组类型ids批量删除医院信息")
    @DeleteMapping("/deleteSelected")
    public Result deleteSelected(@RequestBody List<Long> idList) {
        boolean remove = hospitalSetService.removeByIds(idList);
        return remove ? Result.ok() : Result.fail();
    }

    /**
     * 设置医院锁定还是解锁
     *
     * @param id
     * @param status
     * @return
     */
    @ApiOperation(value = "设置医院锁定和解锁")
    @PutMapping("/changeHospStatus/{id}/{status}")
    public Result changeStatus(@PathVariable Long id, @PathVariable Integer status) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        hospitalSet.setStatus(status);
        boolean update = hospitalSetService.updateById(hospitalSet);
        return update ? Result.ok() : Result.fail();
    }

    /**
     * 根据id发送密钥
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "发送密钥")
    @PutMapping("/sendKey/{id}")
    public Result sendKey(@PathVariable Long id) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hosCode = hospitalSet.getHoscode();
        //TODO 发送短信
        return Result.ok();
    }
}


