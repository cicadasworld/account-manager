package com.jin.mapper;

import com.jin.bean.entity.Account;
import com.jin.bean.vo.QueryVO;

import java.util.List;
import java.util.Map;

/**
 * @author hujin
 * @version 2022/1/22
 */
public interface AccountMapper {

    /**
     * 根据Id查询账户
     * @param id 账户Id
     * @return 账户对象
     */
    Account selectById(Long id);

    /**
     * 增加账户
     * @param account 账户
     * @return 受影响的行数
     */
    int insert(Account account);

    /**
     * 修改账户
     * @param account 账户
     * @return 受影响的行数
     */
    int update(Account account);

    /**
     * 删除账户
     * @param id 账户Id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 分页查询账户列表
     * @param map 参数映射
     * @return 账户列表
     */
    List<Account> selectByPage(Map<String, Long> map);

    /**
     * 分页查询账户列表
     * @param queryVO 包装查询POJO对象
     * @return 账户列表
     */
    List<Account> selectByQueryVO(QueryVO<Account> queryVO);

    /**
     * 根据账户状态查询账户列表, 不指定状态时查询所有账户
     * @param status 账户状态
     * @return 账户列表
     */
    List<Account> selectByStatus(Integer status);

    /**
     * 根据账户状态和账户名查询账户，不指定条件时查询所有账户
     * @param account 账户
     * @return 账户列表
     */
    List<Account> selectByStatusAndName(Account account);

    /**
     * 根据账户Id列表删除多个账户
     * @param ids 账户Id列表
     * @return 受影响的行数
     */
    int deleteByIds(List<Long> ids);
}
