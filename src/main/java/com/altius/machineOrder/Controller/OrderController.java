/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.altius.machineOrder.Controller;

import com.altius.machineOrder.Model.IdAndLabel;
import com.altius.machineOrder.Model.Orders;
import com.altius.machineOrder.Model.PropertyEditor.IdAndLabelPropertyEditor;
import com.altius.machineOrder.Service.MasterService;
import com.altius.machineOrder.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author altius
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MasterService masterService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(IdAndLabel.class, new IdAndLabelPropertyEditor());
    }

    @RequestMapping(value = "addOrder.htm", method = RequestMethod.GET)
    public String showOrder(ModelMap model) {
        model.addAttribute("order", new Orders());
        return "addOrder";
    }

    @RequestMapping(value = "addOrder.htm", method = RequestMethod.POST)
    public String createOrder(@ModelAttribute("order") Orders orders, ModelMap model) {
        try {
            int orderId = this.orderService.createOrder(orders);
            return "redirect:myOrder.htm?msg=Order added successfully the Order Id is "+orderId ;
        } catch (Exception e) {
            model.addAttribute("order", new Orders());
            model.addAttribute("msg", "Order could not be Placed - " + e.getMessage());
            return "addOrder";
        }
    }

    @RequestMapping(value = "listOrder.htm", method = RequestMethod.GET)
    public String listOrder(ModelMap model) {
        model.addAttribute("orderList", this.orderService.getOrderList());
        return "listOrder";
    }

    
    @RequestMapping(value = "deleteOrder.htm",method = RequestMethod.POST)
     public String deleteOrder( int deleteOrderId){
         int rows = this.orderService.deleteOrderById(deleteOrderId);
         return "redirect:listOrder.htm";
     }
     
       @RequestMapping(value = "deleteMyOrder.htm",method = RequestMethod.POST)
     public String deleteMyOrder( int deleteOrderId){
         int rows = this.orderService.deleteOrderById(deleteOrderId);
         return "redirect:myOrder.htm";
     }
     
     
     @RequestMapping(value="myOrder.htm", method = RequestMethod.GET)
     public String myorders(ModelMap model){
         model.addAttribute("myOrderList", this.orderService.myOrders());
         return "myOrders";
         
     }
    
}
