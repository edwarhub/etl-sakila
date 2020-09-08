/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unal.etl.sakila.jpa.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author edwar
 */
@Entity
@Table(name = "bi_rental")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BiRental.findAll", query = "SELECT b FROM BiRental b")
    , @NamedQuery(name = "BiRental.findById", query = "SELECT b FROM BiRental b WHERE b.id = :id")
    , @NamedQuery(name = "BiRental.findByQuantity", query = "SELECT b FROM BiRental b WHERE b.quantity = :quantity")})
public class BiRental implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "quantity")
    private Integer quantity;
    @JoinColumn(name = "city_id", referencedColumnName = "city_id")
    @ManyToOne
    private BiCity cityId;
    @JoinColumn(name = "date_id", referencedColumnName = "date_id")
    @ManyToOne
    private BiDate dateId;
    @JoinColumn(name = "film_id", referencedColumnName = "film_id")
    @ManyToOne
    private BiFilm filmId;
    @JoinColumn(name = "language_id", referencedColumnName = "language_id")
    @ManyToOne
    private BiLanguage languageId;
    @JoinColumn(name = "store_id", referencedColumnName = "store_id")
    @ManyToOne
    private BiStore storeId;

    public BiRental() {
    }

    public BiRental(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BiCity getCityId() {
        return cityId;
    }

    public void setCityId(BiCity cityId) {
        this.cityId = cityId;
    }

    public BiDate getDateId() {
        return dateId;
    }

    public void setDateId(BiDate dateId) {
        this.dateId = dateId;
    }

    public BiFilm getFilmId() {
        return filmId;
    }

    public void setFilmId(BiFilm filmId) {
        this.filmId = filmId;
    }

    public BiLanguage getLanguageId() {
        return languageId;
    }

    public void setLanguageId(BiLanguage languageId) {
        this.languageId = languageId;
    }

    public BiStore getStoreId() {
        return storeId;
    }

    public void setStoreId(BiStore storeId) {
        this.storeId = storeId;
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
        if (!(object instanceof BiRental)) {
            return false;
        }
        BiRental other = (BiRental) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unal.etl.sakila.jpa.pojo.BiRental[ id=" + id + " ]";
    }
    
}
