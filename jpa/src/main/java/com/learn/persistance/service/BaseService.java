/*
 *  * This file is subject to the terms and conditions defined in
 *  * file 'LICENSE.txt', which is part of this source code package.
 */

package com.learn.persistance.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BaseService {

    protected EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;
    protected EntityTransaction transaction;

    public void start() {
        entityManagerFactory = Persistence.createEntityManagerFactory("myPU");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
    }


    public void stop() {
        if(entityManager.isOpen()) {
            entityManager.close();
        }
        if(entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}