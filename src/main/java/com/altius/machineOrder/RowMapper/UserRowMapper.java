/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.altius.machineOrder.RowMapper;

import com.altius.machineOrder.Model.IdAndLabel;
import com.altius.machineOrder.Model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 *
 * @author altius
 */
public class UserRowMapper implements RowMapper<User>{

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
       User u = new User(rs.getInt("USER_ID"),rs.getString("NAME"),
       rs.getString("USERNAME"),rs.getString("PASSWORD"),
       new IdAndLabel(rs.getString("ROLE_ID"),rs.getString("ROLE_DESC")),
       null, rs.getBoolean("ACTIVE"));
       
       
         String businessFunctionIds = rs.getString("BUSINESS_FUNCTION_IDS");
        List<GrantedAuthority> businessFunctionList = new LinkedList<>();
        if(businessFunctionIds!= null){
            for (String businessFunctionId : businessFunctionIds.split(",")) {
            businessFunctionList.add(new SimpleGrantedAuthority(businessFunctionId));
        }
        }
        u.setBusinessFunctionList(businessFunctionList);
        return u;
    }
    
   
    
    
}
