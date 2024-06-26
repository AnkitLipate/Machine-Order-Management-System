/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.altius.machineOrder.Dao;

import com.altius.machineOrder.Model.Items;
import com.altius.machineOrder.RowMapper.ItemRowMapper;
import com.altius.machineOrder.RowMapper.UserRowMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author altius
 */
@Repository
public class ItemDaoIMPL implements ItemDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    }

    @Transactional
    @Override
    public int addItems(Items items) {
        SimpleJdbcInsert si = new SimpleJdbcInsert(dataSource).withTableName("mo_items").usingGeneratedKeyColumns("ITEM_ID");
        Map<String, Object> params = new HashMap<>();
        params.put("LEVEL", items.getLevel());
        params.put("CODE", items.getCode());
        params.put("ITEM_DESCRIPTION", items.getItemDesc());
        params.put("NEXT_LEVEL", items.getNextLevel());
        int itemId = si.executeAndReturnKey(params).intValue();
        return itemId;

    }

    @Override
    public List<Items> findAll() {
        String sql = "SELECT * FROM mo_items order by LEVEL";
        return jdbcTemplate.query(sql, new ItemRowMapper());

    }

    @Override
    public List<Items> findByLevel(int level) {
        String sql = "SELECT * FROM mo_items WHERE LEVEL =:level";
        Map<String, Object> params = new HashMap<>();
        params.put("level", level);
        return namedParameterJdbcTemplate.query(sql, params, new ItemRowMapper());

    }

    @Override
    public Items getItemByItemId(int itemId) {
        String sqlString = " SELECT * FROM mo_items WHERE ITEM_ID =:itemId ";
        Map<String, Object> params = new HashMap<>();
        params.put("itemId", itemId);
        return this.namedParameterJdbcTemplate.queryForObject(sqlString, params, new ItemRowMapper());

    }

    @Override
    public int editItem(Items items) {
        String sql = "UPDATE mo_items SET CODE=:code, ITEM_DESCRIPTION=:itemDesc WHERE ITEM_ID=:itemId";
        Map<String, Object> params = new HashMap<>();
        params.put("code", items.getCode());
        params.put("itemDesc", items.getItemDesc());
        params.put("itemId", items.getItemId());
        return this.namedParameterJdbcTemplate.update(sql, params);
    }

}
