/*
 *  * This file is subject to the terms and conditions defined in
 *  * file 'LICENSE.txt', which is part of this source code package.
 */

package com.learn.persistance.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CD extends Item {

    @Column(name = "total_duration")
    private Float totalDuration;

    private String genre;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "cd_fk")
    private Set<Musician> musicians = new HashSet<Musician>();

    public CD() {
    }

    public CD(String title, String description, Float unitCost, Float totalDuration, String genre) {
        super.setTitle(title);
        super.setDescription(description);
        super.setUnitCost(unitCost);
        this.totalDuration = totalDuration;
        this.genre = genre;
        this.musicians = musicians;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Float unitCost) {
        this.unitCost = unitCost;
    }

    public Float getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Float totalDuration) {
        this.totalDuration = totalDuration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Set<Musician> getMusicians() {
        return musicians;
    }

    public void setMusicians(Set<Musician> musicians) {
        this.musicians = musicians;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CD)) return false;
        if (!super.equals(o)) return false;

        CD cd = (CD) o;

        if (getTotalDuration() != null ? !getTotalDuration().equals(cd.getTotalDuration()) : cd.getTotalDuration() != null)
            return false;
        if (getGenre() != null ? !getGenre().equals(cd.getGenre()) : cd.getGenre() != null) return false;
        return getMusicians() != null ? getMusicians().equals(cd.getMusicians()) : cd.getMusicians() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getTotalDuration() != null ? getTotalDuration().hashCode() : 0);
        result = 31 * result + (getGenre() != null ? getGenre().hashCode() : 0);
        result = 31 * result + (getMusicians() != null ? getMusicians().hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "CD{" +
                "totalDuration=" + totalDuration +
                ", genre='" + genre + '\'' +
                ", musicians=" + musicians +
                ", id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", unitCost=" + getUnitCost() +
                "} " + super.toString();
    }
}