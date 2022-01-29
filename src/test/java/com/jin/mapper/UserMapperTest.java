package com.jin.mapper;

import com.jin.bean.entity.User;
import com.jin.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author hujin
 * @version 2022/1/28
 */
public class UserMapperTest {

    @Test
    public void testSelectById() {
        try (SqlSession sqlSession = SqlSessionUtil.openSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.selectById(1L);
            assertEquals(user.getName(), "admin");
        }
    }

    @Test
    public void testSelectAccountUserByAccountId() {
        try (SqlSession sqlSession = SqlSessionUtil.openSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.selectUserAccountByUserId(1L);
            assertEquals(user.getName(), "admin");
        }
    }

    @Test
    public void testSelectUserRoleByUserId() {
        try (SqlSession sqlSession = SqlSessionUtil.openSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.selectUserRoleByUserId(1L);
            System.out.println(user);
        }
    }

    @Test
    public void testSelectUserAccountLazyLoadById() {
        try (SqlSession sqlSession = SqlSessionUtil.openSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.selectUserAccountLazyLoadById(1L);
            System.out.println(user);
        }
    }
}
