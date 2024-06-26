/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.altius.machineOrder.Service;

import com.altius.machineOrder.Dao.OrderDao;
import com.altius.machineOrder.Model.Orders;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author altius
 */
@Service
public class OrderServiceIMPL implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public int createOrder(Orders od) {

        return this.orderDao.createOrder(od);
        
    }

    @Override
    public List<Orders> getOrderList() {
        return this.orderDao.getOrderList();
    }

    @Override
    public int deleteOrderById(int orderId) {
      return this.orderDao.deleteOrderById(orderId);
    }

    @Override
    public List<Orders> myOrders() {
      return this.orderDao.myOrders();
    }

    
    
    
    
}
