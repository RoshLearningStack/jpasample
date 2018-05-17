package com.learn.persistance.listener;

import javax.persistence.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LifecycleListener {

    Logger LOGGER = Logger.getLogger(LifecycleListener.class.getName());

    @PrePersist
    void perPersist(Object object) {
        LOGGER.log(Level.INFO, "LifecycleListener prePersist()");
    }

    @PostPersist
    void postPersist(Object object) {
        LOGGER.log(Level.INFO, "LifecycleListener postPersist()");
    }

    @PreUpdate
    void preUpdate(Object object) {
        LOGGER.log(Level.INFO, "LifecycleListener preUpdate()");
    }

    @PostUpdate
    void postUpdate(Object object) {
        LOGGER.log(Level.INFO, "LifecycleListener postUpdate()");
    }

    @PreRemove
    void preRemove(Object object) {
        LOGGER.log(Level.INFO, "LifecycleListener preRemove()");
    }

    @PostRemove
    void postRemove(Object object) {
        LOGGER.log(Level.INFO, "LifecycleListener postRemove()");
    }

    @PostLoad
    void postLoad(Object object) {
        LOGGER.log(Level.INFO, "LifecycleListener postLoad()");
    }
}