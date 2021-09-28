package com.orange.womenassist;

public class ContactConfiance {

    private String nom;
    private String telephone;
    private  int photo;

    public ContactConfiance() {
    }

    public ContactConfiance(String nom, String telephone, int photo) {
        this.nom = nom;
        this.telephone = telephone;
        this.photo = photo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}