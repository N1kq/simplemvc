package ru.selever.models;

import jakarta.persistence.*;

import java.sql.Timestamp;

//TODO: Добавить модель для получения данных снаружи, а эта должна расширять её
@Entity
@Table(name = "message_data")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "message")
    private String message;

    @Column(name = "sender_id")
    private Long senderId;

    @Column(name = "recdate")
    private Timestamp recdate;

    @Column(name = "editdate")
    private Timestamp editdate;

    @Column(name = "sender_tg_id")
    private Long senderTgId;

    public Message() {}

    public Message(Long messageId, String message, Long senderId, Timestamp recdate, Timestamp editdate, Long senderTgId) {
        this.messageId = messageId;
        this.message = message;
        this.senderId = senderId;
        this.recdate = recdate;
        this.editdate = editdate;
        this.senderTgId = senderTgId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Timestamp getRecdate() {
        return recdate;
    }

    public void setRecdate(Timestamp recdate) {
        this.recdate = recdate;
    }

    public Timestamp getEditdate() {
        return editdate;
    }

    public void setEditdate(Timestamp editdate) {
        this.editdate = editdate;
    }

    public Long getSenderTgId() {
        return senderTgId;
    }

    public void setSenderTgId(Long senderTgId) {
        this.senderTgId = senderTgId;
    }
}
