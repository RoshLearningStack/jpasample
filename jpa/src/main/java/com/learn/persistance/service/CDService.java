/*
 *  * This file is subject to the terms and conditions defined in
 *  * file 'LICENSE.txt', which is part of this source code package.
 */

package com.learn.persistance.service;

import com.learn.persistance.model.CD;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CDService extends BaseService {
    private static final Logger LOGGER = Logger.getLogger(CDService.class.getName());

    public CDService(EntityManager entityManager, EntityTransaction transaction) {
        super(entityManager, transaction);
    }

    public CD createCD(String description, String genre, String title, Float totalDuration, Float unitCost) {
        CD cd = new CD();
        cd.setDescription(description);
        cd.setGenre(genre);
        cd.setTitle(title);
        cd.setTotalDuration(totalDuration);
        cd.setUnitCost(unitCost);
        return createCD(cd);
    }

    public CD createCD(CD cd) {
        try {
            transaction.begin();
            entityManager.persist(cd);
//        for(Musician musician : cd.getMusicians()) {
//            entityManager.persist(musician);
//        }
            transaction.commit();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            transaction.rollback();
        }
        return cd;
    }

    public CD findCD(Long id) {
        CD cd = null;
        try {
            cd = entityManager.find(CD.class, id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            transaction.rollback();
        }
        return cd;
    }

    public void removeCD(Long id) {
        try {
            CD cd = entityManager.find(CD.class, id);
            if (cd != null) {
                transaction.begin();
                entityManager.remove(cd);
                transaction.commit();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            transaction.rollback();
        }

    }

    public void removeCD(CD cd) {
        try {
            // put the book entity in the managed context by first merging, then remove
            transaction.begin();
            entityManager.merge(cd);
            entityManager.remove(cd);
            transaction.commit();
        } catch (Exception e){
            LOGGER.log(Level.SEVERE, e.getMessage());
            transaction.rollback();
        }
    }

}