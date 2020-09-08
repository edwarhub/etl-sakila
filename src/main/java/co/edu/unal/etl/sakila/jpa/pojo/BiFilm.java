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
@Table(name = "bi_film")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BiFilm.findAll", query = "SELECT b FROM BiFilm b")
    , @NamedQuery(name = "BiFilm.findByFilmId", query = "SELECT b FROM BiFilm b WHERE b.filmId = :filmId")
    , @NamedQuery(name = "BiFilm.findByName", query = "SELECT b FROM BiFilm b WHERE b.name = :name")})
public class BiFilm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "film_id")
    private Integer filmId;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "filmId")
    private Collection<BiRental> biRentalCollection;

    public BiFilm() {
    }

    public BiFilm(Integer filmId) {
        this.filmId = filmId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
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
        hash += (filmId != null ? filmId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BiFilm)) {
            return false;
        }
        BiFilm other = (BiFilm) object;
        if ((this.filmId == null && other.filmId != null) || (this.filmId != null && !this.filmId.equals(other.filmId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unal.etl.sakila.jpa.pojo.BiFilm[ filmId=" + filmId + " ]";
    }
    
}
