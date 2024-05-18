package ru.selever.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.selever.models.User;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserTgId(Long UserTgId);
    List<User> findByRole(Long roleId);
}
