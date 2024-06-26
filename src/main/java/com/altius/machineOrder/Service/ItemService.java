/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.altius.machineOrder.Service;

import com.altius.machineOrder.Model.Items;
import com.altius.machineOrder.Model.User;
import java.util.List;

/**
 *
 * @author altius
 */
public interface ItemService {
    
   public int addItems(Items items);

    public List<Items> getAllItems();

    public List<Items> getItemsByLevel(int level);

    public int editItem(Items items);

    public Items getItemByItemId(int itemId);

}
