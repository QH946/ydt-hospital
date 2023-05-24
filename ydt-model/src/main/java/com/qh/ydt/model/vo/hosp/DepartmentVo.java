package com.qh.ydt.model.vo.hosp;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 美国签证官
 *
 * @author 清欢
 * @date 2023/05/16  10:37:22
 */
@Data
@ApiModel(description = "Department")
public class DepartmentVo {

	@ApiModelProperty(value = "科室编号")
	private String depcode;

	@ApiModelProperty(value = "科室名称")
	private String depname;

	@ApiModelProperty(value = "下级节点")
	private List<DepartmentVo> children;

}

