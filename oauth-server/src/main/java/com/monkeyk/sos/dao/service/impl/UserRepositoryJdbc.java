/*
 * Copyright (c) 2015 MONKEYK Information Technology Co. Ltd
 * www.monkeyk.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * MONKEYK Information Technology Co. Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with MONKEYK Information Technology Co. Ltd.
 */
package com.monkeyk.sos.dao.service.impl;

import com.monkeyk.sos.domain.User;
import com.monkeyk.sos.dao.service.UserRepository;

import static com.monkeyk.sos.utils.CacheConstants.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 2015/11/16
 *
 * @author Shengzhao Li
 */
@Repository("userRepositoryJdbc")
public class UserRepositoryJdbc implements UserRepository {
    private RowMapper userRowMapper = new BeanPropertyRowMapper(User.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private Integer[] findPrivileges(int userId) {
        final String sql = " select role_id from t_open_user where user_id = ? ";
        final List<Integer> strings = this.jdbcTemplate.queryForList(sql, new Object[]{userId}, Integer.class);
        return strings.toArray(new Integer[strings.size()]);
    }

    @Override
    public void saveUser(final User user) {
        final String sql = " insert into t_open_user(ACCOUNT,PASSWORD,TEL,EMAIL,NICKNAME,DESCRIPT," +
                "ORG_ID,STATUS,CREATE_TIME,CREATE_USER,PIC_PATH,USER_TYPE) values (?,?,?,?,?,?,?,?,?,?,?,?) ";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getAccount());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getTel());
                ps.setString(4, user.getEmail());
                ps.setString(5, user.getNickname());
                ps.setString(6, user.getDescript());
                ps.setObject(7, user.getOrgId());
                ps.setInt(8, 2);//status
                ps.setObject(9, user.getCreateTime());
                ps.setObject(10, user.getCreateUser());//CREATE_USER
                ps.setString(11, user.getPicPath());//PIC_PATH
                ps.setObject(12, 1);//USER_TYPE
                return ps;
            }
        },keyHolder);
        final int userId = keyHolder.getKey().intValue();
        user.setId(userId);
        //insert privileges
//        this.jdbcTemplate.update("insert into t_open_user_role(user_id, role_id) values (?,?)", new PreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps) throws SQLException {
//                ps.setInt(1, userId);
//                ps.setInt(2, 2842513);
//            }
//        });
    }

    @Override
    @CacheEvict(value = USER_CACHE, key = "#user.getAccount()")
    public void updateUser(final User user) {
        final String sql = " update t_open_user set PASSWORD = ?, TEL = ?, EMAIL = ?,NICKNAME = ?," +
                " DESCRIPT=?,ORG_ID=?,PIC_PATH=? where id = ? ";


        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, user.getAccount());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getTel());
                ps.setString(4, user.getEmail());
                ps.setString(5, user.getNickname());
                ps.setString(6, user.getDescript());
                ps.setInt(7, user.getOrgId());
                ps.setString(8, user.getPicPath());//PIC_PATH
                ps.setInt(9, user.getId());
            }
        });
    }

    @Override
    @Cacheable(value = USER_CACHE, key = "#account")
    public User findByUsername(String account) {
        final String sql = " select * from t_open_user where account = ? and status = 2";
        final List<User> list = this.jdbcTemplate.query(sql, new Object[]{account}, userRowMapper);

        User user = null;
        if (!list.isEmpty()) {
            user = list.get(0);
            //user.setRoleIds(findPrivileges(user.getId()));
        }
        return user;
    }

    @Override
    public List<User> findUsersByUsername(String account) {
        String sql = " select a.* from t_open_user a  where a.status = 2  ";
        Object[] params = new Object[]{};
        if (StringUtils.isNotEmpty(account)) {
            sql += " and account like ?";
            params = new Object[]{"%" + account + "%"};
        }
        sql += " order by create_time desc ";

        final List<User> list = this.jdbcTemplate.query(sql, params, userRowMapper);
//        for (User user : list) {
//            user.setRoleIds(findPrivileges(user.getId()));
//        }
        return list;
    }
}
