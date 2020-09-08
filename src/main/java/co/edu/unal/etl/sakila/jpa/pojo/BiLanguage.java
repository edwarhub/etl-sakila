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
@Table(name = "bi_language")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BiLanguage.findAll", query = "SELECT b FROM BiLanguage b")
    , @NamedQuery(name = "BiLanguage.findByLanguageId", query = "SELECT b FROM BiLanguage b WHERE b.languageId = :languageId")
    , @NamedQuery(name = "BiLanguage.findByName", query = "SELECT b FROM BiLanguage b WHERE b.name = :name")})
public class BiLanguage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "language_id")
    private Integer languageId;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "languageId")
    private Collection<BiRental> biRentalCollection;

    public BiLanguage() {
    }

    public BiLanguage(Integer languageId) {
        this.languageId = languageId;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
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
        hash += (languageId != null ? languageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BiLanguage)) {
            return false;
        }
        BiLanguage other = (BiLanguage) object;
        if ((this.languageId == null && other.languageId != null) || (this.languageId != null && !this.languageId.equals(other.languageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unal.etl.sakila.jpa.pojo.BiLanguage[ languageId=" + languageId + " ]";
    }
    
}
