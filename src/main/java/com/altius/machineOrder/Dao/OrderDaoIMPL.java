/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.altius.machineOrder.Dao;

import cc.altius.utils.DateUtils;
import com.altius.machineOrder.Model.Orders;
import com.altius.machineOrder.Model.User;
import com.altius.machineOrder.RowMapper.OrderRowMapper;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author altius
 */
@Repository
public class OrderDaoIMPL implements OrderDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    }

    private String orderString = "SELECT  a.ORDER_ID, p.USER_ID, p.NAME `USER_NAME`, "
            + "         a.CODE, s.ORDER_STATUS_ID, s.ORDER_STATUS_DESC, "
            + "        a.ORDER_DATE FROM mo_order a LEFT JOIN mo_user p ON a.USER_ID=p.USER_ID "
            + "           LEFT JOIN mo_order_status s ON a.ORDER_STATUS_ID=s.ORDER_STATUS_ID  "
            + "  WHERE TRUE ";

    @Override
    public int createOrder(Orders od) {
        SimpleJdbcInsert si = new SimpleJdbcInsert(dataSource).withTableName("mo_order")
                .usingGeneratedKeyColumns("ORDER_ID");
        Map<String, Object> params = new HashMap<>();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        params.put("USER_ID", user.getUserId());
        params.put("CODE", od.getCode());
        params.put("ORDER_STATUS_ID", 1);
        Date curDate = DateUtils.getCurrentDateObject(DateUtils.IST);
        params.put("ORDER_DATE", curDate);
        return si.executeAndReturnKey(params).intValue();
    }

    @Override
    public List<Orders> getOrderList() {
        return this.jdbcTemplate.query(this.orderString, new OrderRowMapper());
    }

    @Override
    public int deleteOrderById(int orderId) {
        String sqlString = "DELETE from mo_order where ORDER_ID=:orderId";
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        return this.namedParameterJdbcTemplate.update(sqlString, params);

    }

    private String sqlString = "SELECT  a.ORDER_ID, p.USER_ID, p.NAME `USER_NAME`, "
            + "         a.CODE, s.ORDER_STATUS_ID, s.ORDER_STATUS_DESC, "
            + "        a.ORDER_DATE FROM mo_order a LEFT JOIN mo_user p ON a.USER_ID=p.USER_ID "
            + "           LEFT JOIN mo_order_status s ON a.ORDER_STATUS_ID=s.ORDER_STATUS_ID  "
            + "  WHERE a.USER_ID=:userId  ";

    @Override
    public List<Orders> myOrders() {
        String sql = this.sqlString;
        Map<String, Object> params = new HashMap<>();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        params.put("userId", user.getUserId());
        return this.namedParameterJdbcTemplate.query(sql, params, new OrderRowMapper());

    }

}
