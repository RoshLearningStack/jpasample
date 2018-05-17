package com.learn.persistance.model;

import com.learn.persistance.listener.AgeCalculationListener;
import com.learn.persistance.listener.ValidationListener;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity
@EntityListeners({
        AgeCalculationListener.class,
        ValidationListener.class
})
public class Author extends Artist {

    @Column(name = "preferred_language")
    private String preferredLanguage;

    public Author() {
    }

    public Author(String firstName, String lastName, Date dateOfBirth, String preferredLanguage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.preferredLanguage = preferredLanguage;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    //    @PrePersist
//    @PreUpdate
    private void validate() {
        System.out.println("DataValidationListener validateData()");
        if (StringUtils.isBlank(firstName)) {
            throw new IllegalArgumentException("Invalid first name : " + firstName);
        }
        if (StringUtils.isBlank(lastName)) {
            throw new IllegalArgumentException("Invalid last name : " + lastName);
        }
    }

//    @PostLoad
//    @PostPersist
//    @PostUpdate
    public void calculateAge() {
        if (dateOfBirth == null) {
            age = null;
            return;
        }

        Calendar birth = new GregorianCalendar();
        birth.setTime(dateOfBirth);
        Calendar now = new GregorianCalendar();
        now.setTime(new java.util.Date());
        int adjust = 0;
        if (now.get(Calendar.DAY_OF_YEAR) - birth.get(Calendar.DAY_OF_YEAR) < 0) {
            adjust = -1;
        }
        age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR) + adjust;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        if (!super.equals(o)) return false;

        Author author = (Author) o;

        return getPreferredLanguage() != null ? getPreferredLanguage().equals(author.getPreferredLanguage()) : author.getPreferredLanguage() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getPreferredLanguage() != null ? getPreferredLanguage().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Author{" +
                "preferredLanguage='" + preferredLanguage + '\'' +
                "} " + super.toString();
    }

}