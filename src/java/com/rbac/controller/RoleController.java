/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rbac.controller;

import com.rbac.entities.Permissions;
import com.rbac.entities.Roles;
import com.rbac.model.PermissionsFacade;
import com.rbac.model.RolesFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author jeffersonbienaime
 */
@Named(value = "roleController")
@SessionScoped
public class RoleController implements Serializable {

    @EJB
    private RolesFacade roleFacade;
    @EJB
    private PermissionsFacade permissionFacade;

    private Roles role = new Roles();
    private Integer[] selectedPermissions;
    private List<Permissions> permissions;

    public RoleController() {
    }

    @PostConstruct
    public void init() {
        permissions = new ArrayList<>();
        //permissionFacade = new PermissionsFacade();
        permissions.addAll(permissionFacade.findAll());
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public List<Roles> findAll() {
        return roleFacade.findAll();
    }

    public String insert() {
        
        List<Permissions> collect = Arrays.stream(selectedPermissions).map(
                permissionFacade::getPermissionbyID)
                .collect(Collectors.toList());
        role.setPermissionsCollection(collect);
        this.roleFacade.create(role);
        this.role = new Roles();
        return "role";
    }

    public void delete(Roles role) {
        this.roleFacade.remove(role);
    }

    public String update(Roles role) {

        this.role = role;
        return "update";
    }

    public String update() {

        this.roleFacade.edit(role);
        return "role";
    }

    public Integer[] getSelectedPermissions() {
        return selectedPermissions;
    }

    public void setSelectedPermissions(Integer[] selectedPermissions) {
        this.selectedPermissions = selectedPermissions;
    }

    public List<Permissions> getPermissions() {
        return permissions;
    }

}
