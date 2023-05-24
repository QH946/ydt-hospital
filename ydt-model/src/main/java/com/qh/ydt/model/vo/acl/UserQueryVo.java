package com.qh.ydt.model.vo.acl;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 用户查询实体
 *
 * @author 清欢
 * @date 2023/05/16  10:35:58
 */
@Data
@ApiModel(description = "用户查询实体")
public class UserQueryVo implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户名")
	private String username;

	@ApiModelProperty(value = "昵称")
	private String nickName;

}

