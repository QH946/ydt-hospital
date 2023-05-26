package com.qh.ydt.model.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 标志信息签证官
 * <p>
 * HospitalSet
 * </p>
 *
 * @author 清欢
 * @date 2023/05/16  10:37:00
 */
@Data
@ApiModel(description = "签名信息")
public class SignInfoVo  implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "api基础路径")
	private String apiUrl;

	@ApiModelProperty(value = "签名秘钥")
	private String signKey;

}

