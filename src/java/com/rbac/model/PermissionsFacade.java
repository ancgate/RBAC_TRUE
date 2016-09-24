/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rbac.model;

import com.rbac.entities.Permissions;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jeffersonbienaime
 */
@Stateless
public class PermissionsFacade extends AbstractFacade<Permissions> {

    @PersistenceContext(unitName = "RBACTRUEPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PermissionsFacade() {
        super(Permissions.class);
    }
    
    
    public List<Permissions> getPermissionbyID(Integer p){
        
       TypedQuery<Permissions> query = em.createNamedQuery("Users.findById", Permissions.class);
       query.setParameter("id", p);
       return query.getResultList();
    }
    
}
