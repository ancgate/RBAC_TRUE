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
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author jeffersonbienaime
 */
@Named(value = "roleController")
@Dependent
public class RoleController {

    @EJB
    private RolesFacade roleFacade;
    @EJB
    private PermissionsFacade permissionFacade;

    private Roles role = new Roles();
    private Permissions[] selectedPermissions;
    private List<Permissions> permissions;

    public RoleController() {
    }

    @PostConstruct
    public void init() {
        permissions = new ArrayList<Permissions>();
        // permissionFacade = new PermissionsFacade();
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
        
        for (Permissions perm : selectedPermissions  ){
        role.setPermissionsCollection(permissionFacade.getPermissionbyID(perm.getId()));
        }        
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

    public Permissions[] getSelectedPermissions() {
        return selectedPermissions;
    }

    public void setSelectedPermissions(Permissions[] selectedPermissions) {
        this.selectedPermissions = selectedPermissions;
    }

    public List<Permissions> getPermission() {
        return permissions;
    }

}
