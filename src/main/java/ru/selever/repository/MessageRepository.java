package ru.selever.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.selever.models.Message;


public interface MessageRepository extends JpaRepository<Message, Long> {

    Message findByMessageId(Long messageId);
}
