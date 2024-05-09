package ru.selever.models;

import jakarta.persistence.*;

import java.sql.Date;
//TODO: Добавить модель для получения данных снаружи, а эта должна расширять её
@Entity
@Table(name = "message_data")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "message")
    private String message;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "recdate")
    private Date recdate;

    @Column(name = "editdate")
    private Date editdate;

    public Message() {}

    public Message(Long messageId, String message, int userId, Date recdate, Date editdate) {
        this.messageId = messageId;
        this.message = message;
        this.userId = userId;
        this.recdate = recdate;
        this.editdate = editdate;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
