package com.orange.womenassist;

public class ListeAssociation {

    private  int photo;
    private String nomAssociation;
    private String descriptionAssociation;

    public ListeAssociation() {
    }

    public ListeAssociation(int photo, String nomAssociation, String descriptionAssociation) {
        this.photo = photo;
        this.nomAssociation = nomAssociation;
        this.descriptionAssociation = descriptionAssociation;

    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getNomAssociation() {
        return nomAssociation;
    }

    public void setNomAssociation(String nomAssociation) {
        this.nomAssociation = nomAssociation;
    }

    public String getDescriptionAssociation() {
        return descriptionAssociation;
    }

    public void setDescriptionAssociation(String descriptionAssociation) {
        this.descriptionAssociation = descriptionAssociation;
    }


}
