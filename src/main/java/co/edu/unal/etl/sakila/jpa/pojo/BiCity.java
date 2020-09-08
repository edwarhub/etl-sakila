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
@Table(name = "bi_city")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BiCity.findAll", query = "SELECT b FROM BiCity b")
    , @NamedQuery(name = "BiCity.findByCityId", query = "SELECT b FROM BiCity b WHERE b.cityId = :cityId")
    , @NamedQuery(name = "BiCity.findByName", query = "SELECT b FROM BiCity b WHERE b.name = :name")})
public class BiCity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "city_id")
    private Integer cityId;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "cityId")
    private Collection<BiRental> biRentalCollection;

    public BiCity() {
    }

    public BiCity(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
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
        hash += (cityId != null ? cityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BiCity)) {
            return false;
        }
        BiCity other = (BiCity) object;
        if ((this.cityId == null && other.cityId != null) || (this.cityId != null && !this.cityId.equals(other.cityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unal.etl.sakila.jpa.pojo.BiCity[ cityId=" + cityId + " ]";
    }
    
}
