package ru.selever.models;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "message_data")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    private int messageId;

    @Column(name = "message")
    private String message;

    @Column(name = "sender_id")
    private int senderId;

    @Column(name = "recdate")
    private Date recdate;

    @Column(name = "editdate")
    private Date editdate;

    public Message(int messageId, String message, int senderId, Date recdate, Date editdate) {
        this.messageId = messageId;
        this.message = message;
        this.senderId = senderId;
        this.recdate = recdate;
        this.editdate = editdate;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public Date getRecdate() {
        return recdate;
    }

    public void setRecdate(Date recdate) {
        this.recdate = recdate;
    }

    public Date getEditdate() {
        return editdate;
    }

    public void setEditdate(Date editdate) {
        this.editdate = editdate;
    }
}
