package com.qh.ydt.model.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单数量签证官
 *
 * @author 清欢
 * @date 2023/05/16  10:36:47
 */
@Data
@ApiModel(description = "OrderCountVo")
public class OrderCountVo {

	@ApiModelProperty(value = "安排日期")
	private String reserveDate;

	@ApiModelProperty(value = "预约单数")
	private Integer count;

}

