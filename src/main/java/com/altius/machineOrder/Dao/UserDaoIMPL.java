/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.altius.machineOrder.Dao;

import cc.altius.utils.PassPhrase;
import com.altius.machineOrder.Model.IdAndLabel;
import com.altius.machineOrder.Model.User;
import com.altius.machineOrder.RowMapper.UserRowMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author altius
 */
@Repository
public class UserDaoIMPL implements UserDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    }

    private final String userSql = "SELECT u1.* "
            + "FROM ( "
            + "    SELECT  "
            + "        u.USER_ID,  "
            + "        u.NAME,  "
            + "        u.USERNAME,  "
            + "        u.PASSWORD,  "
            + "        u.ACTIVE,  "
            + "        r.ROLE_ID,  "
            + "        r.ROLE_DESC, "
            + "        GROUP_CONCAT(rbf.BUSINESS_FUNCTION_ID) AS BUSINESS_FUNCTION_IDS "
            + "    FROM  "
            + "        mo_user u "
            + "    LEFT JOIN "
            + "        mo_role r ON u.ROLE_ID = r.ROLE_ID "
            + "    LEFT JOIN "
            + "        mo_role_business_function rbf ON r.ROLE_ID = rbf.ROLE_ID "
            + "    GROUP BY "
            + "        u.USER_ID "
            + "       "
            + ") AS u1 "
            + "WHERE TRUE ";

    @Override
    public List<User> getUserList() {
        return this.jdbcTemplate.query(userSql, new UserRowMapper());
    }

    @Transactional
    @Override
    public int addUser(User user) {

        SimpleJdbcInsert si = new SimpleJdbcInsert(dataSource).withTableName("mo_user").usingGeneratedKeyColumns("USER_ID");
        Map<String, Object> params = new HashMap<>();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        params.put("NAME", user.getName());
        params.put("USERNAME", user.getUsername());
        String password = PassPhrase.getPassword(8);
        System.out.println("Password--------" + password);
        params.put("PASSWORD", encoder.encode(password));
        params.put("ROLE_ID", user.getRole().getId());
        params.put("ACTIVE", true);
        int userId = si.executeAndReturnKey(params).intValue();
        return userId;

    }

    @Override
    public User getUserByUserId(int userId) {
        String sqlString = this.userSql + "AND u1.USER_ID=:userId ";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        return this.namedParameterJdbcTemplate.queryForObject(sqlString, params, new UserRowMapper());

    }
    
    

    @Override
    public int editUser(User user) {
        String sqlString = "UPDATE mo_user u  SET u.NAME=:name,  u.USERNAME=:username, u.ROLE_ID=:roleId, u.ACTIVE=:active WHERE u.USER_ID=:userId";
        Map<String, Object> params = new HashMap<>();
        params.put("name", user.getName());
        params.put("username", user.getUsername());
        params.put("active", user.isActive());
        params.put("userId", user.getUserId());
        params.put("roleId", user.getRole().getId());
        return this.namedParameterJdbcTemplate.update(sqlString, params);

    }

    @Override
    public User loadUserByUsername(String username) {
        String sqlString = this.userSql + "AND u1.USERNAME=:username";
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        return this.namedParameterJdbcTemplate.queryForObject(sqlString, params, new UserRowMapper());

    }

}
