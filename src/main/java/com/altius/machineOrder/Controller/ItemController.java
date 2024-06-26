/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.altius.machineOrder.Controller;

import com.altius.machineOrder.Model.IdAndLabel;
import com.altius.machineOrder.Model.Items;
import com.altius.machineOrder.Model.PropertyEditor.IdAndLabelPropertyEditor;
import com.altius.machineOrder.Model.User;
import com.altius.machineOrder.Service.ItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class ItemController {

    @Autowired
    private ItemService itemService;
    
    
       @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(IdAndLabel.class, new IdAndLabelPropertyEditor());
    }
    
    
    @RequestMapping(value = "addItems.htm", method = RequestMethod.GET)
     public String showItems(ModelMap model) {
        model.addAttribute("addItem", new Items());
        return "addItems";
    }
     
      @RequestMapping(value = "addItems.htm", method = RequestMethod.POST)
    public String addItems(@ModelAttribute("addItem") Items items, ModelMap model) {
        try {
            int itemId = this.itemService.addItems(items);
            return "redirect:items.htm?msg=User Id " + itemId + " added successfully";

        } catch (Exception e) {
            model.addAttribute("addItem",items );
            model.addAttribute("msg", "Item not added - " + e.getMessage());
            return "addItems";
        }
    }

    

    @RequestMapping(value = "/items.htm")
    public String getAllItems(ModelMap model) {
        List<Items> itemList = itemService.getAllItems();
        model.addAttribute("items", itemList);
        return "items";
    }

    @RequestMapping(value = "/level.htm", method = RequestMethod.GET)
    public String getItemsByLevel(@RequestParam( defaultValue = "0")int level, ModelMap model) {
        model.addAttribute("items", this.itemService.getItemsByLevel(level));
        return "level";
    }

    @RequestMapping(value = "showEditItem.htm", method = RequestMethod.POST)
    public String showEditUser( int itemId, ModelMap model){
         model.addAttribute("item", this.itemService.getItemByItemId(itemId));
         return "editItem";
    }
    
    
    @RequestMapping(value = "editItem.htm", method = RequestMethod.POST)
    public String editItem(@ModelAttribute("item") Items items, ModelMap model){
        
        try{
            int rows = this.itemService.editItem(items);
             if (rows == 0) {
                return "redirect:items.htm?msg=Nothing to update";
            } else {
                return "redirect:items.htm?msg=Item successfully updated   "+ items.getItemId();
            }
        }
        catch(Exception e){
          model.addAttribute("items", items);
             model.addAttribute("msg", "Item not be updated - " + e.getMessage());
            return "editItem";
    }
    }
    
    
     
    
    
   
}


 
//            <form:form  modelAttribute="item" method="POST" >
//            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
//            <div class="form-group">
//                <label>Level:</label>
//                <form:input  path="level"  />
//            </div>
//            <div class="form-group">
//                <label>Code:</label>
//                <form:input path="code"  />
//            </div
//            <div class="form-group">
//                <label>Code:</label>
//                <form:input path="itemDesc"  />
//            </div>
//             <div class="form-group">
//                <label>Item Desc:</label>
//                <form:input path="nextLevel"  />
//            </div>
//           
//            <div class="form-buttons">
//                <input type="submit" value="Submit" />
//                <input type="button" value="Cancel" onclick="window.location.href = 'index.htm?msg=Cancelled';" />
//            </div>
//        </form:form>