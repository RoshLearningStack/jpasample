/*
 *  * This file is subject to the terms and conditions defined in
 *  * file 'LICENSE.txt', which is part of this source code package.
 */

package com.learn.persistance.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public abstract class BaseService {

    protected EntityManager entityManager;
    protected EntityTransaction transaction;

    public BaseService(EntityManager entityManager, EntityTransaction transaction) {
        this.entityManager = entityManager;
        this.transaction = transaction;
    }

}