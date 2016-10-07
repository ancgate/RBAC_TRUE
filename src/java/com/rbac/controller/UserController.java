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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author jeffersonbienaime
 */
@Named(value = "userController")
@SessionScoped
//@ViewScoped
public class UserController implements Serializable {

    @EJB
    private UsersFacade userFacade;
    private Users user = new Users();
    
    private Users selectedUser;
    private List<Users> selectedUsers;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Users getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Users selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<Users> getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(List<Users> selectedUsers) {
        this.selectedUsers = selectedUsers;
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
    
    
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Car Selected", ((Users) event.getObject()).getUsername());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Car Unselected", ((Users) event.getObject()).getUsername());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
        
    
    

}
