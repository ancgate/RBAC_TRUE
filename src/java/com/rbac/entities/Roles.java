/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rbac.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jeffersonbienaime
 */
@Entity
@Table(name = "Roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r"),
    @NamedQuery(name = "Roles.findById", query = "SELECT r FROM Roles r WHERE r.id = :id"),
    @NamedQuery(name = "Roles.findByName", query = "SELECT r FROM Roles r WHERE r.name = :name"),
    @NamedQuery(name = "Roles.findByDescription", query = "SELECT r FROM Roles r WHERE r.description = :description"),
    @NamedQuery(name = "Roles.findByCreatedDate", query = "SELECT r FROM Roles r WHERE r.createdDate = :createdDate"),
    @NamedQuery(name = "Roles.findByCreatedBy", query = "SELECT r FROM Roles r WHERE r.createdBy = :createdBy"),
    @NamedQuery(name = "Roles.findByModifiedDate", query = "SELECT r FROM Roles r WHERE r.modifiedDate = :modifiedDate"),
    @NamedQuery(name=  "Roles.loadForPermission", query="SELECT obj FROM Role obj LEFT JOIN obj.permissions perm WHERE perm.id = :permissionId ORDER BY obj.name"),
    @NamedQuery(name = "Roles.findByModifiedBy", query = "SELECT r FROM Roles r WHERE r.modifiedBy = :modifiedBy")})
@NamedNativeQueries({
	@NamedNativeQuery(name="Roles.removeAllPermissions", query="DELETE FROM Roles_has_Permissions WHERE role_id = ?1", resultClass=Roles.class),
	@NamedNativeQuery(name="Roles.removeAllUsers", query="DELETE FROM Users_has_Roles WHERE role_id = ?1", resultClass=Roles.class)
})
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 100)
    @Column(name = "description")
    private String description;
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 100)
    @Column(name = "createdBy")
    private String createdBy;
    @Column(name = "modifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Size(max = 100)
    @Column(name = "modifiedBy")
    private String modifiedBy;
    @ManyToMany(mappedBy = "rolesCollection")
    private Collection<Permissions> permissionsCollection;
    @JoinTable(name = "Users_has_Roles", joinColumns = {
        @JoinColumn(name = "Roles_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "Users_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Users> usersCollection;

    public Roles() {
        
        
    }

    public Roles(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @XmlTransient
    public Collection<Permissions> getPermissionsCollection() {
        return permissionsCollection;
    }

    public void setPermissionsCollection(Collection<Permissions> permissionsCollection) {
        this.permissionsCollection = permissionsCollection;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rbac.entities.Roles[ id=" + id + " ]";
    }
    
}
