package com.orange.womenassist;

public class ResponsableAssociation {

    private String nomRespAssociation;
    private  int photo;

    public ResponsableAssociation() {
    }

    public ResponsableAssociation(String nomRespAssociation, int photo) {
        this.nomRespAssociation = nomRespAssociation;
        this.photo = photo;
    }

    public String getNomRespAssociation() {
        return nomRespAssociation;
    }

    public void setNomRespAssociation(String nomRespAssociation) {
        this.nomRespAssociation = nomRespAssociation;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
