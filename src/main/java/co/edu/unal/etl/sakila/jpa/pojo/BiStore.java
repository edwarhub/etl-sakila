/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unal.etl.sakila.jpa.pojo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author edwar
 */
@Entity
@Table(name = "bi_store")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BiStore.findAll", query = "SELECT b FROM BiStore b")
    , @NamedQuery(name = "BiStore.findByStoreId", query = "SELECT b FROM BiStore b WHERE b.storeId = :storeId")
    , @NamedQuery(name = "BiStore.findByName", query = "SELECT b FROM BiStore b WHERE b.name = :name")})
public class BiStore implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "store_id")
    private Integer storeId;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "storeId")
    private Collection<BiRental> biRentalCollection;

    public BiStore() {
    }

    public BiStore(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<BiRental> getBiRentalCollection() {
        return biRentalCollection;
    }

    public void setBiRentalCollection(Collection<BiRental> biRentalCollection) {
        this.biRentalCollection = biRentalCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storeId != null ? storeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BiStore)) {
            return false;
        }
        BiStore other = (BiStore) object;
        if ((this.storeId == null && other.storeId != null) || (this.storeId != null && !this.storeId.equals(other.storeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unal.etl.sakila.jpa.pojo.BiStore[ storeId=" + storeId + " ]";
    }
    
}
