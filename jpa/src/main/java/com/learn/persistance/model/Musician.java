/*
 *  * This file is subject to the terms and conditions defined in
 *  * file 'LICENSE.txt', which is part of this source code package.
 */

package com.learn.persistance.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Musician implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name", length = 50)
    protected String firstName;

    @Column(name = "last_name", length = 50)
    protected String lastName;

    @Column(length = 5000)
    protected String bio;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    protected Date dateOfBirth;

    @Transient
    protected Integer age;

    @Column(name = "preferred_instrument")
    private String preferredInstrument;

    public Musician() {
    }

    public Musician(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPreferredInstrument() {
        return preferredInstrument;
    }

    public void setPreferredInstrument(String preferredInstrument) {
        this.preferredInstrument = preferredInstrument;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Musician)) return false;

        Musician musician = (Musician) o;

        return new EqualsBuilder()
                .append(getId(), musician.getId())
                .append(getFirstName(), musician.getFirstName())
                .append(getLastName(), musician.getLastName())
                .append(getBio(), musician.getBio())
                .append(getDateOfBirth(), musician.getDateOfBirth())
                .append(getAge(), musician.getAge())
                .append(getPreferredInstrument(), musician.getPreferredInstrument())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getFirstName())
                .append(getLastName())
                .append(getBio())
                .append(getDateOfBirth())
                .append(getAge())
                .append(getPreferredInstrument())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Musician{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bio='" + bio + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                ", preferredInstrument='" + preferredInstrument + '\'' +
                '}';
    }

}