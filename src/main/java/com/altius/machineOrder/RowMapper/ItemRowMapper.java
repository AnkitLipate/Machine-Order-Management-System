/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.altius.machineOrder.RowMapper;

import com.altius.machineOrder.Model.Items;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author altius
 */
public class ItemRowMapper implements RowMapper<Items>{

    @Override
    public Items mapRow(ResultSet rs, int rowNum) throws SQLException {
       return new Items(rs.getInt("ITEM_ID"),
       rs.getInt("LEVEL"),
       rs.getString("CODE"),
       rs.getString("ITEM_DESCRIPTION"),
       rs.getInt("NEXT_LEVEL"));
    
    }
    
    
    
}
