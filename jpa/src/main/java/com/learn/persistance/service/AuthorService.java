package com.learn.persistance.service;

import com.learn.persistance.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthorService extends BaseService {

    private static final Logger LOGGER = Logger.getLogger(AuthorService.class.getName());

    public AuthorService(EntityManager entityManager, EntityTransaction transaction) {
        super(entityManager, transaction);
    }

    public Author createAuthor(Author author) {
        try {
            transaction.begin();
            entityManager.persist(author);
            transaction.commit();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            transaction.rollback();
        }
        return author;
    }

    public Author findAuthor(Long id) {
        return entityManager.find(Author.class, id);
    }

}