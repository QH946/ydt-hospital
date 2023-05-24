package com.qh.ydt.model.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单统计查询签证官
 *
 * @author 清欢
 * @date 2023/05/16  10:36:43
 */
@Data
@ApiModel(description = "OrderCountQueryVo")
public class OrderCountQueryVo {

	@ApiModelProperty(value = "医院编号")
	private String hoscode;

	@ApiModelProperty(value = "医院名称")
	private String hosname;

	@ApiModelProperty(value = "安排日期")
	private String reserveDateBegin;
	private String reserveDateEnd;

}

