package ru.selever.models;

import jakarta.persistence.*;

import java.sql.Date;
@Entity
@Table(name = "data")
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "message")
    private String message;

    @Column(name = "recdate")
    private Date recdate;

    @Column(name = "editdate")
    private Date editdate;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "e-mail")
    private String eMail;

    public Data() {
    }

    public Data(Long messageId, String message, Date recdate, Date editdate, String name, String phoneNumber, String eMail) {
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
