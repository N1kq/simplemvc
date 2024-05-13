package ru.selever.models;

import jakarta.persistence.*;

@Entity
@Table(name = "user_data")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private String chatId;

    public User(){}

    public User(Long userId, String userTgname, String name,
                String surname, String phoneNumber, String eMail, Long role, String chatId) {
        this.userId = userId;
        this.userTgname = userTgname;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
        this.role = role;
        this.chatId = chatId;
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

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
}
