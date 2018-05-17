package com.learn.persistance.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class Artist implements Serializable {

    @Id
    @GeneratedValue
    protected Long id;
    @Column(name = "first_name", length = 50)
    protected String firstName;
    @Column(name = "last_name", length = 50)
    protected String lastName;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    protected Date dateOfBirth;
    @Transient
    protected Integer age;

    public Artist() {
    }

    public Artist(String firstName, String lastName, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;

        Artist artist = (Artist) o;

        if (!getFirstName().equals(artist.getFirstName())) return false;
        if (!getLastName().equals(artist.getLastName())) return false;
        if (!getDateOfBirth().equals(artist.getDateOfBirth())) return false;
        return getAge() != null ? getAge().equals(artist.getAge()) : artist.getAge() == null;
    }

    @Override
    public int hashCode() {
        int result = getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getDateOfBirth().hashCode();
        result = 31 * result + (getAge() != null ? getAge().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                '}';
    }
}