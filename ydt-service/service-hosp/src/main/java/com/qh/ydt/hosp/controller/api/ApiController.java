package com.qh.ydt.hosp.controller.api;

import cn.hutool.core.util.StrUtil;
import com.qh.ydt.common_utils.exception.HospitalException;
import com.qh.ydt.common_utils.result.Result;
import com.qh.ydt.common_utils.result.ResultCodeEnum;
import com.qh.ydt.hosp.service.DepartmentService;
import com.qh.ydt.hosp.service.HospitalService;
import com.qh.ydt.hosp.service.HospitalSetService;
import com.qh.ydt.hosp.service.ScheduleService;
import com.qh.ydt.model.model.hosp.Department;
import com.qh.ydt.model.model.hosp.Schedule;
import com.qh.ydt.model.vo.hosp.DepartmentQueryVo;
import com.qh.ydt.model.vo.hosp.ScheduleQueryVo;
import com.qh.ydt.service_utils.helper.HttpRequestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 第三方医院对接api
 *
 * @author 清欢
 * @date 2023/05/25  12:01:58
 */
@RestController
@RequestMapping("/api/hosp")
public class ApiController {

    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private HospitalSetService hospitalSetService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ScheduleService scheduleService;


    /**
     * 上传医院
     *
     * @param request 请求
     * @return {@link Result}
     */
    @PostMapping("/saveHospital")
    public Result saveHospital(HttpServletRequest request) {
        Map<String, Object> parameterMap = getParameterMap(request);
        // base64 空格转换
        extractLogData(parameterMap);
        hospitalService.saveHospital(parameterMap);
        return Result.ok(true);
    }

    /**
     * 查询医院
     *
     * @param request 请求
     * @return {@link Result}
     */
    @PostMapping("hospital/show")
    public Result getHospital(HttpServletRequest request) {
        Map<String, Object> parameterMap = getParameterMap(request);
        String hoscode = (String) parameterMap.get("hoscode");
        // 签名校验
        checkSign(parameterMap);
        return Result.ok(hospitalService.findHospitalByHoscode(hoscode));
    }

    /**
     * 上传科室接口
     *
     * @param request 请求
     * @return {@link Result}
     */
    @PostMapping("saveDepartment")
    public Result saveDepartment(HttpServletRequest request) {
        Map<String, Object> parameterMap = getParameterMap(request);
        // 签名校验
        checkSign(parameterMap);
        departmentService.saveDepartment(parameterMap);
        return Result.ok(Boolean.TRUE);
    }

    /**
     * 分页查询科室
     *
     * @param request 请求
     * @return {@link Result}<{@link Page}<{@link Department}>>
     */
    @PostMapping("/department/list")
    public Result<Page<Department>> departmentListPage(HttpServletRequest request) {
        Map<String, Object> parameterMap = getParameterMap(request);
        String hoscode = (String) parameterMap.get("hoscode");
        // 签名校验
        checkSign(parameterMap);
        String depcode = ((String) parameterMap.get("depcode"));
        String page = (String) parameterMap.get("page");
        String limit = (String) parameterMap.get("limit");
        // 默认值
        int pageInt = StrUtil.isEmpty(page) ? 1 : Integer.parseInt(page);
        int limitInt = StrUtil.isEmpty(limit) ? 10 : Integer.parseInt(limit);
        DepartmentQueryVo departmentQueryVo = new DepartmentQueryVo();
        departmentQueryVo.setHoscode(hoscode);
        departmentQueryVo.setDepcode(depcode);
        Page<Department> depPage = departmentService.findPageDepartment(pageInt, limitInt, departmentQueryVo);
        return Result.ok(depPage);
    }


    /**
     * 删除科室
     *
     * @param request 请求
     * @return {@link Result}<{@link Boolean}>
     */
    @PostMapping("/department/remove")
    public Result<Boolean> removeDep(HttpServletRequest request) {
        Map<String, Object> parameterMap = getParameterMap(request);
        // 签名校验
        checkSign(parameterMap);
        String hoscode = (String) parameterMap.get("hoscode");
        String depcode = (String) parameterMap.get("depcode");
        return Result.ok(departmentService.removeDep(hoscode, depcode) > 0);
    }


    /**
     * 上传排班
     *
     * @param request 请求
     * @return {@link Result}<{@link Boolean}>
     */
    @PostMapping("/saveSchedule")
    public Result<Boolean> saveSchedule(HttpServletRequest request) {
        Map<String, Object> parameterMap = getParameterMap(request);
        // 签名校验
        checkSign(parameterMap);
        scheduleService.saveSchedule(parameterMap);
        return Result.ok(Boolean.TRUE);
    }


    /**
     * 排班分页
     *
     * @param request 请求
     * @return {@link Result}<{@link Page}<{@link Schedule}>>
     */
    @PostMapping("/schedule/list")
    public Result<Page<Schedule>> scheduleList(HttpServletRequest request) {
        Map<String, Object> parameterMap = getParameterMap(request);
        // 签名校验
        checkSign(parameterMap);
        String hoscode = (String) parameterMap.get("hoscode");
        String depcode = (String) parameterMap.get("depcode");
        String page = (String) parameterMap.get("page");
        String limit = (String) parameterMap.get("limit");
        // 默认值
        int pageInt = StrUtil.isEmpty(page) ? 1 : Integer.parseInt(page);
        int limitInt = StrUtil.isEmpty(limit) ? 10 : Integer.parseInt(limit);
        ScheduleQueryVo scheduleQueryVo = new ScheduleQueryVo();
        scheduleQueryVo.setHoscode(hoscode);
        scheduleQueryVo.setDepcode(depcode);
        Page<Schedule> schedulePage = scheduleService.findPageSchedule(pageInt, limitInt, scheduleQueryVo);
        return Result.ok(schedulePage);
    }


    /**
     * 删除排班
     *
     * @param request 请求
     * @return {@link Result}<{@link Boolean}>
     */
    @PostMapping("/schedule/remove")
    public Result<Boolean> removeSchedule(HttpServletRequest request) {
        Map<String, Object> parameterMap = getParameterMap(request);
        // 签名校验
        checkSign(parameterMap);
        return Result.ok(scheduleService.removeSchedule(parameterMap) > 0);
    }

    /**
     * 获取传递的信息
     *
     * @param request 请求
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    private Map<String, Object> getParameterMap(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        Map<String, Object> parameterMap = HttpRequestHelper.switchMap(map);
        String hoscode = (String) parameterMap.get("hoscode");
        if (StrUtil.isEmpty(hoscode)) {
            throw new HospitalException(ResultCodeEnum.PARAM_ERROR);
        }
        return parameterMap;
    }

    /**
     * 签名校验
     *
     * @param parameterMap
     */
    private void checkSign(Map<String, Object> parameterMap) {
        extractLogData(parameterMap);
        // 签名校验
        String hoscode = (String) parameterMap.get("hoscode");
        if (!HttpRequestHelper.isSignEquals(parameterMap, hospitalSetService.getSignKey(hoscode))) {
            throw new HospitalException(ResultCodeEnum.SIGN_ERROR);
        }
    }

    /**
     * base64 空格转换
     *
     * @param parameterMap
     */
    private void extractLogData(Map<String, Object> parameterMap) {
        // base64码通过http传输 +号变 空格
        String logoData = (String) parameterMap.get("logoData");
        if (StrUtil.isNotEmpty(logoData)) {
            logoData = logoData.replaceAll(" ", "+");
            parameterMap.put("logoData", logoData);
        }
    }


}
