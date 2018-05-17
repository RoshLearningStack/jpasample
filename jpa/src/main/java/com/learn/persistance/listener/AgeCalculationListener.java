package com.learn.persistance.listener;

import com.learn.persistance.model.Artist;

import javax.persistence.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgeCalculationListener {

    Logger LOGGER = Logger.getLogger(AgeCalculationListener.class.getName());

    @PostLoad
    @PostPersist
    @PostUpdate
    public void calculateAge(Artist artist) {
        LOGGER.log(Level.INFO, "AgeCalculationListener calculateAge()");
        if (artist.getDateOfBirth() == null) {
            artist.setAge(null);
            return;
        }

        Calendar birth = new GregorianCalendar();
        birth.setTime(artist.getDateOfBirth());
        Calendar now = new GregorianCalendar();
        now.setTime(new java.util.Date());
        int adjust = 0;
        if (now.get(Calendar.DAY_OF_YEAR) - birth.get(Calendar.DAY_OF_YEAR) < 0) {
            adjust = -1;
        }
        artist.setAge(now.get(Calendar.YEAR) - birth.get(Calendar.YEAR) + adjust);
    }
}