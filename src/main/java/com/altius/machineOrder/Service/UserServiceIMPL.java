/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.altius.machineOrder.Service;

import com.altius.machineOrder.Dao.UserDao;
import com.altius.machineOrder.Model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author altius
 */
@Service
public class UserServiceIMPL  implements UserService,UserDetailsService{
    
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUserList() {
        return this.userDao.getUserList();
    }

    @Override
    public int addUser(User user) {
      return this.userDao.addUser(user);
    }

    @Override
    public User getUserById(int userId) {
       return this.userDao.getUserByUserId(userId);
    }

    @Override
    public int editUser(User user) {
      return this.userDao.editUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = this.userDao.loadUserByUsername(username);
       if(user == null){
           throw  new UsernameNotFoundException("User Not Found");
       }
       else {
           return user;
    }
    
    
    } 
    
}
