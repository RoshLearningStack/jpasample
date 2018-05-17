/*
 *  * This file is subject to the terms and conditions defined in
 *  * file 'LICENSE.txt', which is part of this source code package.
 */

package com.learn.persistance.service;

import com.learn.persistance.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookService extends BaseService {
    private static final Logger LOGGER  = Logger.getLogger(BookService.class.getName());

    public BookService(EntityManager entityManager, EntityTransaction trasaction) {
       super(entityManager, trasaction);
    }

    public Book createBook(Book book) {
        try {
            transaction.begin();
            entityManager.persist(book);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return book;
    }

    public Book findBook(Long id) {
        Book book = null;
        try {
            book = entityManager.find(Book.class, id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            transaction.rollback();
        }
        return book;
    }

    public void removeBook(Long id) {
        try {
            Book book = entityManager.find(Book.class, id);
            if (book != null) {
                transaction.begin();
                entityManager.remove(book);
                transaction.commit();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            transaction.rollback();
        }
    }

    public void removeBook(Book book) {
        try {
            // put the book entity in the managed context by first merging, then remove
            transaction.begin();
            entityManager.merge(book);
            entityManager.remove(book);
            transaction.commit();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            transaction.rollback();
        }

    }

    public Book raiseUnitCost(Long id, Float raise) {
        Book book = null;
        try {
            book = entityManager.find(Book.class, id);
            if (book != null) {
                transaction.begin();
                book.setUnitCost(book.getUnitCost() + raise);
                transaction.commit();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            transaction.rollback();
        }
        return book;
    }

    public Book raiseUnitCost(Book book, Float raise) {
        try {
            Book mergedBook = entityManager.merge(book);
            transaction.begin();
            mergedBook.setUnitCost(mergedBook.getUnitCost() + raise);
            transaction.commit();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            transaction.rollback();
        }
        return book;
    }

}