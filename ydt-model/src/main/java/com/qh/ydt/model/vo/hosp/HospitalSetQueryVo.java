package com.qh.ydt.model.vo.hosp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 医院设置查询签证官
 *
 * @author 清欢
 * @date 2023/05/16  10:37:11
 */
@Data
public class HospitalSetQueryVo {

    @ApiModelProperty(value = "医院名称")
    private String hosname;

    @ApiModelProperty(value = "医院编号")
    private String hoscode;
}
