package com.qh.ydt.model.vo.acl;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 角色查询实体
 *
 * @author 清欢
 * @date 2023/05/16  10:35:47
 */
@Data
@ApiModel(description = "角色查询实体")
public class RoleQueryVo implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "角色名称")
	private String roleName;

}

