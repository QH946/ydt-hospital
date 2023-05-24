package com.qh.ydt.model.vo.hosp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 进度查询签证官
 *
 * @author 清欢
 * @date 2023/05/16  10:37:06
 */
@Data
@ApiModel(description = "Schedule")
public class ScheduleQueryVo {

	@ApiModelProperty(value = "医院编号")
	private String hoscode;

	@ApiModelProperty(value = "科室编号")
	private String depcode;

	@ApiModelProperty(value = "医生编号")
	private String doccode;

	@ApiModelProperty(value = "安排日期")
	private Date workDate;

	@ApiModelProperty(value = "安排时间（0：上午 1：下午）")
	private Integer workTime;

}

