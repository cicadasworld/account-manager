package com.jin.mapper;

import com.jin.bean.entity.User;

/**
 * @author hujin
 * @version 2022/1/28
 */
public interface UserMapper {

    /**
     * 根据用户Id查询用户
     * @param userId 用户Id
     * @return 用户
     */
    User selectById(Long userId);

    /**
     * 根据用户Id查询用户以及关联的账户列表信息
     * @param userId 用户Id
     * @return 用户以及关联的账户列表信息
     */
    User selectUserAccountByUserId(Long userId);

    /**
     * 根据用户Id查询用户以及延迟加载关联的账户列表信息
     * @param userId 用户Id
     * @return 用户以及关联的账户列表信息
     */
    User selectUserAccountLazyLoadById(Long userId);

    /**
     * 根据用户Id查询用户以及关联的角色列表信息
     * @param userId 用户Id
     * @return 用户以及关联的角色列表信息
     */
    User selectUserRoleByUserId(Long userId);
}
