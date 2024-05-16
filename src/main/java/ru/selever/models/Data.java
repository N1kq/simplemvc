package ru.selever.models;

import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "data")
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "message")
    private String message;

    @Column(name = "recdate")
    private Timestamp recdate;

    @Column(name = "editdate")
    private Timestamp editdate;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "e-mail")
    private String eMail;

    public Data() {
    }

    public Data(Long messageId, String message, Timestamp recdate, Timestamp editdate, String name, String phoneNumber, String eMail) {
        this.messageId = messageId;
        this.message = message;
        this.recdate = recdate;
        this.editdate = editdate;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
}
