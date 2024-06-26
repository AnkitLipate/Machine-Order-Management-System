/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.altius.machineOrder.Dao;

import com.altius.machineOrder.Model.User;
import java.util.List;

/**
 *
 * @author altius
 */
public interface UserDao {
     public List<User> getUserList();

    public int addUser(User user);

    public User getUserByUserId(int userId);

    public int editUser(User user);

    public User loadUserByUsername(String username);
}
