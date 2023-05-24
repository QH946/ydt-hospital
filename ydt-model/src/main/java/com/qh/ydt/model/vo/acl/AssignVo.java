package com.qh.ydt.model.vo.acl;

import lombok.Data;

/**
 * 签证实体
 *
 * @author 清欢
 * @date 2023/05/16  10:35:40
 */
@Data
public class AssignVo {

    private Long roleId;

    private Long[] permissionId;
}
