/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.altius.machineOrder.Service;

import com.altius.machineOrder.Dao.ItemDao;
import com.altius.machineOrder.Model.Items;
import com.altius.machineOrder.Model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author altius
 */
@Service
public class ItemsServiceIMPL implements ItemService {

    @Autowired
    private ItemDao itemDAO;

    @Override
    public int addItems(Items items) {
        return this.itemDAO.addItems(items);
    }

    @Override
    public List<Items> getAllItems() {
        return this.itemDAO.findAll();
    }

    @Override
    public List<Items> getItemsByLevel(int level) {
        return this.itemDAO.findByLevel(level);
    }

    @Override
    public int editItem(Items items) {
        return this.itemDAO.editItem(items);
    }

    @Override
    public Items getItemByItemId(int itemId) {
        return this.itemDAO.getItemByItemId(itemId);
    }

}
