package com.learn.persistance.listener;

import com.learn.persistance.model.Artist;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ValidationListener {

    private static final Logger LOGGER = Logger.getLogger(ValidationListener.class.getName());

    @PrePersist
    @PreUpdate
    private void validate(Artist artist) {
        LOGGER.log(Level.INFO, "DataValidation Listener validate()");
        if (StringUtils.isBlank(artist.getFirstName())) {
            throw new IllegalArgumentException("Invalid first name : " + artist.getFirstName());
        }
        if (StringUtils.isBlank(artist.getLastName())) {
            throw new IllegalArgumentException("Invalid last name : " + artist.getLastName());
        }

    }

}