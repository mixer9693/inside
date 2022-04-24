package ru.inside.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.inside.demo.entity.Message;

public interface MessageRepository extends CrudRepository<Message, Integer>, MessageRepositoryCustom {
}
