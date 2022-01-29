package com.jin.mapper;

import com.jin.bean.entity.Role;
import com.jin.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

/**
 * @author hujin
 * @version 2022/1/28
 */
public class RoleMapperTest {

    @Test
    public void testSelectRoleUserByRoleId() {
        try (SqlSession sqlSession = SqlSessionUtil.openSqlSession()) {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            Role role = mapper.selectRoleUserByRoleId(1L);
            System.out.println("role = " + role);
        }
    }
}
