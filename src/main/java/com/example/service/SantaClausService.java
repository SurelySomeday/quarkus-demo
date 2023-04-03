package com.example.service;

import com.example.entity.Gift;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author yanxin
 * @Description:
 */
@ApplicationScoped
public class SantaClausService {
    @Inject
    EntityManager em;


    public List<Gift> query() {
        return em.createQuery("from Gift ", Gift.class).getResultList();
    }

    @Transactional
    public void createGift(String giftDescription) {
        Gift gift = new Gift();
        gift.setName(giftDescription);
        em.persist(gift);
    }
}
