package com.orange.womenassist;

public class ListeForum {

    private String nomForum;
    private String descriptionForum;

    public ListeForum() {
    }

    public ListeForum(String nomForum, String descriptionForum) {
        this.nomForum = nomForum;
        this.descriptionForum = descriptionForum;
    }

    public String getNomForum() {
        return nomForum;
    }

    public void setNomForum(String nomForum) {
        this.nomForum = nomForum;
    }

    public String getDescriptionForum() {
        return descriptionForum;
    }

    public void setDescriptionForum(String descriptionForum) {
        this.descriptionForum = descriptionForum;
    }
}
