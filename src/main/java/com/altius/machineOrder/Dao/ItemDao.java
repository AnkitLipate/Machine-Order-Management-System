/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.altius.machineOrder.Dao;

import com.altius.machineOrder.Model.Items;
import com.altius.machineOrder.Model.User;
import java.util.List;

/**
 *
 * @author altius
 */
public interface ItemDao {

    public List<Items> findAll();

    public List<Items> findByLevel(int level);

    public Items getItemByItemId(int itemId);

    public int editItem(Items items);
    
     public int addItems(Items items);

}
