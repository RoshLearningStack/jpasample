/*
 *  * This file is subject to the terms and conditions defined in
 *  * file 'LICENSE.txt', which is part of this source code package.
 */

package com.learn.persistance.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CD implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100)
    private String title;

    @Column(length = 3000)
    private String description;

    @Column(name = "unit_cost")
    private Float unitCost;

    @Column(name = "total_duration")
    private Float totalDuration;

    private String genre;

    @OneToMany
    @JoinColumn(name = "cd_fk")
    private Set<Musician> musicians = new HashSet<Musician>();

    public CD() {
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

        CD cd = (CD) o;

        return new EqualsBuilder()
                .append(getId(), cd.getId())
                .append(getTitle(), cd.getTitle())
                .append(getDescription(), cd.getDescription())
                .append(getUnitCost(), cd.getUnitCost())
                .append(getTotalDuration(), cd.getTotalDuration())
                .append(getGenre(), cd.getGenre())
                .append(getMusicians(), cd.getMusicians())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getTitle())
                .append(getDescription())
                .append(getUnitCost())
                .append(getTotalDuration())
                .append(getGenre())
                .append(getMusicians())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "CD{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", unitCost=" + unitCost +
                ", totalDuration=" + totalDuration +
                ", genre='" + genre + '\'' +
                ", musicians=" + musicians +
                '}';
    }
}