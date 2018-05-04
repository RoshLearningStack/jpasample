/*
 *  * This file is subject to the terms and conditions defined in
 *  * file 'LICENSE.txt', which is part of this source code package.
 */

package com.learn.persistance.service;

import com.learn.persistance.model.CD;

public class CDService extends BaseService {

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
        transaction.begin();
        entityManager.persist(cd);
        transaction.commit();
        return cd;
    }

    public CD findCD(Long id) {
        return entityManager.find(CD.class, id);
    }

    public void removeCD(Long id) {
        CD cd = entityManager.find(CD.class, id);
        if (cd != null) {
            transaction.begin();
            entityManager.remove(cd);
            transaction.commit();
        }
    }

    public void removeCD(CD cd) {
        // put the book entity in the managed context by first merging, then remove
        transaction.begin();
        entityManager.merge(cd);
        entityManager.remove(cd);
        transaction.commit();
    }

}