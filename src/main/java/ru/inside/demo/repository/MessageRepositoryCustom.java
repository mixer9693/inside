package ru.inside.demo.repository;

import ru.inside.demo.entity.Message;

import java.util.List;

public interface MessageRepositoryCustom {
    List<Message> findLatestLimitTo(Integer limit);
}
