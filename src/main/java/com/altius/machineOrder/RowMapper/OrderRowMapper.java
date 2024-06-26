/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.altius.machineOrder.RowMapper;

import com.altius.machineOrder.Model.IdAndLabel;
import com.altius.machineOrder.Model.Orders;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author altius
 */
public class OrderRowMapper  implements RowMapper<Orders>{

    @Override
    public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
       return new Orders(rs.getInt("ORDER_ID"), 
               new IdAndLabel(rs.getString("USER_ID"), rs.getString("USER_NAME")),
       rs.getString("CODE"), 
               new IdAndLabel(rs.getString("ORDER_STATUS_ID"), rs.getString("ORDER_STATUS_DESC")), 
                rs.getDate("ORDER_DATE"));
    
    }
    
    
}
