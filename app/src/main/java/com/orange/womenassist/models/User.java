package com.orange.womenassist.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class User {

    private static final String USER_ROLE_MEMBRE = "MEMBRE";

    private String pseudo;
    private String password;
    private String role;
    private boolean is_exist;

    public static String getUserRoleMembre() {
        return USER_ROLE_MEMBRE;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isIs_exist() {
        return is_exist;
    }

    public void setIs_exist(boolean is_exist) {
        this.is_exist = is_exist;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    private String create_at;

    public User(String pseudo, String password)
    {
        this.create_at = new SimpleDateFormat("ddMMyyyy_HHmmss", Locale.getDefault()).format(new Date());
        this.is_exist = true;
        this.role = USER_ROLE_MEMBRE;
        this.password = password;
        this.pseudo = pseudo;
    }
}
