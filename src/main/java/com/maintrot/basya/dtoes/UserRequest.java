package com.maintrot.basya.dtoes;

public class UserRequest {
    private String name;
    private String password;
    private String phone;
    private String role;
    private String photo;

    public UserRequest() {
    }

    public UserRequest(String name, String password, String phone, String role, String photo) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
