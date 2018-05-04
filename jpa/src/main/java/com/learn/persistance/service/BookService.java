/*
 *  * This file is subject to the terms and conditions defined in
 *  * file 'LICENSE.txt', which is part of this source code package.
 */

package com.learn.persistance.service;

import com.learn.persistance.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

public class BookService extends BaseService {

    public Book createBook(String title, String description, Float unitCost, String isbn) {
        Book book = new Book();
        book.setTitle(title);
        book.setDescription(description);
        book.setUnitCost(unitCost);
        book.setIsbn(isbn);
        return createBook(book);
    }

    public Book createBook(Book book) {
        // transaction
        transaction.begin();
        entityManager.persist(book);
        transaction.commit();
        return book;
    }

    public Book findBook(Long id) {
       return entityManager.find(Book.class, id);
    }

    public void removeBook(Long id) {
        Book book = entityManager.find(Book.class, id);
        if(book != null) {
            transaction.begin();
            entityManager.remove(book);
            transaction.commit();
        }
    }

    public void removeBook(Book book) {
        // put the book entity in the managed context by first merging, then remove
        transaction.begin();
        entityManager.merge(book);
        entityManager.remove(book);
        transaction.commit();
    }

    public Book raiseUnitCost(Long id, Float raise) {
        Book book = entityManager.find(Book.class, id);
        if(book != null) {
            transaction.begin();
            book.setUnitCost(book.getUnitCost() + raise);
            transaction.commit();
        }
        return book;
    }

    public Book raiseUnitCost(Book book, Float raise) {
        Book mergedBook = entityManager.merge(book);
        transaction.begin();
        mergedBook.setUnitCost(mergedBook.getUnitCost() + raise);
        transaction.commit();
        return book;
    }



}