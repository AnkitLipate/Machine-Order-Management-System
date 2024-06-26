/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.altius.machineOrder.Service;

import com.altius.machineOrder.Model.Orders;
import java.util.List;

/**
 *
 * @author altius
 */
public interface OrderService {

    public int createOrder(Orders od);

    public List<Orders> getOrderList();

    public int deleteOrderById(int orderId);

    public List<Orders> myOrders();

}
