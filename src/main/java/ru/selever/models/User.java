package ru.selever.models;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "user_data")
public class User{

    public Dialogs status;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_tgname")
    private String userTgname;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "e-mail")
    private String eMail;

    @Column(name = "role")
    private Long role;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "user_tg_id", unique = true)
    private Long userTgId;

    @Column(name = "recdate")
    private Timestamp recdate;

    @Column(name = "editdate")
    private Timestamp editdate;


    public User(){}

    public User(Long userId, String userTgname, String name, String surname, String phoneNumber, String eMail,
                Long role, Long chatId, Long userTgId, Timestamp recdate, Timestamp editdate) {
        this.userId = userId;
        this.userTgname = userTgname;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
        this.role = role;
        this.chatId = chatId;
        this.userTgId = userTgId;
        this.recdate = recdate;
        this.editdate = editdate;
    }

//Getters and Setters


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserTgname() {
        return userTgname;
    }

    public void setUserTgname(String userTgname) {
        this.userTgname = userTgname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
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

    public Long getUserTgId() {
        return userTgId;
    }

    public void setUserTgId(Long userTgId) {
        this.userTgId = userTgId;
    }
}
