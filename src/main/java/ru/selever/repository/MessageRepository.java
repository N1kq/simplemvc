package ru.selever.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.selever.models.Message;

//TODO: 1 warning с бд исправить
public interface MessageRepository extends JpaRepository<Message, Long> {
}
