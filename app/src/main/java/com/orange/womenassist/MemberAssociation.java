package com.orange.womenassist;

public class MemberAssociation {

    private String nomMemberAssociation;
    private  int photo;

    public MemberAssociation() {
    }

    public MemberAssociation(String nomMemberAssociation, int photo) {
        this.nomMemberAssociation = nomMemberAssociation;
        this.photo = photo;
    }

    public String getNomMemberAssociation() {
        return nomMemberAssociation;
    }

    public void setNomMemberAssociation(String nomMemberAssociation) {
        this.nomMemberAssociation = nomMemberAssociation;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
