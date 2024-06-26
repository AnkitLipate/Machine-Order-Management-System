/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.altius.machineOrder.Dao;

import com.altius.machineOrder.Model.Orders;
import com.altius.machineOrder.Model.User;
import java.util.List;

/**
 *
 * @author altius
 */
public interface OrderDao {
    
    public int createOrder(Orders od);
    
     public List<Orders> getOrderList();
     
    public int deleteOrderById(int orderId);
    
    public List<Orders> myOrders();
    
}
