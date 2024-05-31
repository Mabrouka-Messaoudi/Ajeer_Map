package com.example.ajeer_map.model;

import java.io.Serializable;

public class User implements Serializable {
    private String phoneNumber;
    private String password;
    private String name;
    private String job;
    private byte[] identityCardPhoto;

    public User(String phoneNumber, String password, String name, String job, byte[] identityCardPhoto) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.name = name;
        this.job = job;
        this.identityCardPhoto = identityCardPhoto;
    }

    public String getPhoneNumber() { return phoneNumber; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getJob() { return job; }
    public byte[] getIdentityCardPhoto() { return identityCardPhoto; }
}
