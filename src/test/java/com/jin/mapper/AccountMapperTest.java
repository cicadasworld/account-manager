package com.jin.mapper;

import com.jin.bean.entity.Account;
import com.jin.bean.vo.QueryVO;
import com.jin.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

/**
 * @author hujin
 * @version 2022/1/22
 */
public class AccountMapperTest {

    @Test
    public void testSelectById() {
        try (SqlSession sqlSession = SqlSessionUtil.openSqlSession()) {
            AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
            Account account = mapper.selectById(1L);
            assertEquals(account.getName(), "Tony");
        }
    }

    @Test
    public void testInsert() {
        try (SqlSession sqlSession = SqlSessionUtil.openSqlSession()) {
            AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
            Account account = new Account();
            account.setName("Tim");
            account.setBalance(new BigDecimal("1000000.00"));
            account.setStatus(1);
            int insertRow = mapper.insert(account);
            // 手动提交事务
            sqlSession.commit();
            assertEquals(insertRow, 1);
            assertEquals(account.getId().longValue(), 9L);
        }
    }

    @Test
    public void testUpdate() {
        // 自动提交事务
        try (SqlSession sqlSession = SqlSessionUtil.openSqlSession(true)) {
            AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
            Account account = new Account();
            account.setId(7L);
            account.setName("Emma");
            account.setBalance(new BigDecimal(88888888));
            account.setStatus(0);
            int updateRow = mapper.update(account);
            // sqlSession.commit();
            assertEquals(updateRow, 1);
        }
    }

    @Test
    public void testDeleteById() {
        try (SqlSession sqlSession = SqlSessionUtil.openSqlSession()) {
            AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
            int deleteRow = mapper.deleteById(9L);
            // 手动提交事务
            sqlSession.commit();
            assertEquals(deleteRow, 1);
        }
    }

    @Test
    public void testSelectByPage() {
        try (SqlSession sqlSession = SqlSessionUtil.openSqlSession()) {
            AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
            Map<String, Long> map = new HashMap<>();
            map.put("startIndex", 0L);
            map.put("pageSize", 2L);
            map.put("status", 0L);
            List<Account> accounts = mapper.selectByPage(map);
            assertEquals(accounts.size(), 1);
        }
    }

    @Test
    public void testSelectByQueryVO() {
        try (SqlSession sqlSession = SqlSessionUtil.openSqlSession()) {
            AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
            QueryVO<Account> queryVO = new QueryVO();
            Account account = new Account();
            account.setStatus(0);
            queryVO.setCondition(account);
            queryVO.setPageNo(1L);
            queryVO.setPageSize(2L);
            List<Account> accounts = mapper.selectByQueryVO(queryVO);
            assertEquals(accounts.size(), 1);
        }
    }

    @Test
    public void testSelectByStatus() {
        try (SqlSession sqlSession = SqlSessionUtil.openSqlSession()) {
            AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
            List<Account> accounts = mapper.selectByStatus(null);
            assertEquals(accounts.size(), 7);
        }
    }

    @Test
    public void testSelectByStatusAndName() {
        try (SqlSession sqlSession = SqlSessionUtil.openSqlSession()) {
            AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
            Account account = new Account();
            account.setStatus(1);
            account.setName("Monica");
            List<Account> accounts = mapper.selectByStatusAndName(null);
            assertEquals(accounts.size(), 7);
        }
    }

    @Test
    public void testDeleteByIds() {
        try (SqlSession sqlSession = SqlSessionUtil.openSqlSession()) {
            AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
            List<Long> ids = Arrays.asList(10L, 11L, 12L);
            if (ids != null && !ids.isEmpty()) {
                int deleteRow = mapper.deleteByIds(ids);
                sqlSession.commit();
                assertEquals(deleteRow, 3);
            }
        }
    }

    @Test
    public void testSelectAccountUserByAccountId() {
        try (SqlSession sqlSession = SqlSessionUtil.openSqlSession()) {
            AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
            Account account = mapper.selectAccountUserByAccountId(1L);
            assertEquals(account.getName(), "Tony");
        }
    }

    @Test
    public void testSelectAccountUserLazyLoadByAccountId() {
        try (SqlSession sqlSession = SqlSessionUtil.openSqlSession()) {
            AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
            Account account = mapper.selectAccountUserLazyLoadByAccountId(1L);
            assertEquals(account.getName(), "Tony");
//            assertEquals(account.getUser().getName(), "admin");
        }
    }

    @Test
    public void testSelectByUserId() {
        try (SqlSession sqlSession = SqlSessionUtil.openSqlSession()) {
            AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
            List<Account> accounts = mapper.selectByUserId(1L);
            assertEquals(accounts.size(), 3);
        }
    }
}
