package ru.inside.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.inside.demo.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByName(String name);
}
