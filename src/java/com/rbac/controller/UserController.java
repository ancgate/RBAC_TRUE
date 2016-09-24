/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rbac.controller;

import com.rbac.entities.Users;
import com.rbac.model.UsersFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author jeffersonbienaime
 */
@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {

    @EJB
    private UsersFacade userFacade;
    private Users user = new Users();

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
    public List<Users> findAll() {
        return userFacade.findAll();
    }

    /**
     * Creates a new instance of UserController
     */
    public UserController() {
    }
    
    
    public String insert(){
        
     this.userFacade.create(user);
     this.user = new Users();
     return "index";
        
    }
    
    public void delete(Users user){
    
    this.userFacade.remove(user);
    
    }
    
    public String update(Users user){
    
    this.user=user;
    return "update";
    
    }
    
    public String update(){
        
        this.userFacade.edit(user);
        return "index";
    }
        
    
    

}
