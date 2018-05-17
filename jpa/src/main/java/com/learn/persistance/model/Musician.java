/*
 *  * This file is subject to the terms and conditions defined in
 *  * file 'LICENSE.txt', which is part of this source code package.
 */

package com.learn.persistance.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Musician extends Artist {

    @Column(length = 5000)
    protected String bio;

    @Column(name = "preferred_instrument")
    private String preferredInstrument;

    public Musician() {}

    public Musician(String firstName, String lastName, String bio, Date dateOfBirth, String preferredInstrument) {
        super(firstName, lastName, dateOfBirth);
        this.bio = bio;
        this.preferredInstrument = preferredInstrument;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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
        if (!super.equals(o)) return false;

        Musician musician = (Musician) o;

        if (getBio() != null ? !getBio().equals(musician.getBio()) : musician.getBio() != null) return false;
        return getPreferredInstrument() != null ? getPreferredInstrument().equals(musician.getPreferredInstrument()) : musician.getPreferredInstrument() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getBio() != null ? getBio().hashCode() : 0);
        result = 31 * result + (getPreferredInstrument() != null ? getPreferredInstrument().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Musician{" +
                "bio='" + bio + '\'' +
                ", preferredInstrument='" + preferredInstrument + '\'' +
                "} " + super.toString();
    }

}