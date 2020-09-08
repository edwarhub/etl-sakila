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
@Table(name = "bi_date")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BiDate.findAll", query = "SELECT b FROM BiDate b")
    , @NamedQuery(name = "BiDate.findByDateId", query = "SELECT b FROM BiDate b WHERE b.dateId = :dateId")
    , @NamedQuery(name = "BiDate.findByYear", query = "SELECT b FROM BiDate b WHERE b.year = :year")
    , @NamedQuery(name = "BiDate.findByMonth", query = "SELECT b FROM BiDate b WHERE b.month = :month")
    , @NamedQuery(name = "BiDate.findByDay", query = "SELECT b FROM BiDate b WHERE b.day = :day")})
public class BiDate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "date_id")
    private Integer dateId;
    @Column(name = "year")
    private Integer year;
    @Column(name = "month")
    private Integer month;
    @Column(name = "day")
    private Integer day;
    @OneToMany(mappedBy = "dateId")
    private Collection<BiRental> biRentalCollection;

    public BiDate() {
    }

    public BiDate(Integer dateId) {
        this.dateId = dateId;
    }

    public Integer getDateId() {
        return dateId;
    }

    public void setDateId(Integer dateId) {
        this.dateId = dateId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
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
        hash += (dateId != null ? dateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BiDate)) {
            return false;
        }
        BiDate other = (BiDate) object;
        if ((this.dateId == null && other.dateId != null) || (this.dateId != null && !this.dateId.equals(other.dateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unal.etl.sakila.jpa.pojo.BiDate[ dateId=" + dateId + " ]";
    }
    
}
