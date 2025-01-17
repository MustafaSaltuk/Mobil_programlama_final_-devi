package com.toyotaproje.arizatakipsistemi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Personel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;

    //Username , passwordu ,ID yi tanımlıyoruz (Id ye ıdentıty veriyoruz)
    //Aşagıdaki methotlar sayesinde yukarıda tanımlanan değişkenleri kullanıp işlem yapabileceğiz.
    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}