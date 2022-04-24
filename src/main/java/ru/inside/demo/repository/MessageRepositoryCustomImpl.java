package ru.inside.demo.repository;

import ru.inside.demo.entity.Message;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class MessageRepositoryCustomImpl implements MessageRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Message> findLatestLimitTo(Integer limit) {
        return entityManager.createQuery("SELECT m FROM Message m ORDER BY m.id DESC", Message.class)
                .setMaxResults(limit).getResultList();
    }
}
