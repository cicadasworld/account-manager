package com.jin.mapper;

import com.jin.bean.entity.Role;

/**
 * @author hujin
 * @version 2022/1/28
 */
public interface RoleMapper {

    Role selectRoleUserByRoleId(Long roleId);
}
