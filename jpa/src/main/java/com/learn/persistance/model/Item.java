package com.learn.persistance.model;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @Column(length = 100)
    protected String title;
    @Column(length = 3000)
    protected String description;
    @Column(name = "unit_cost", length = 50)
    protected Float unitCost;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (!getTitle().equals(item.getTitle())) return false;
        if (getDescription() != null ? !getDescription().equals(item.getDescription()) : item.getDescription() != null)
            return false;
        return getUnitCost() != null ? getUnitCost().equals(item.getUnitCost()) : item.getUnitCost() == null;
    }

    @Override
    public int hashCode() {
        int result = getTitle().hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getUnitCost() != null ? getUnitCost().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", unitCost=" + unitCost +
                '}';
    }
}
