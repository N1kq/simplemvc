package ru.selever.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.selever.models.User;


public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserTgId(Long UserTgId);
}
