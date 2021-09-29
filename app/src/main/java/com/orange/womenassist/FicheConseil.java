package com.orange.womenassist;

public class FicheConseil {

    private String nomFiche;
    private String descriptionFiche;

    public FicheConseil() {
    }

    public FicheConseil(String nomFiche, String descriptionFiche) {
        this.nomFiche = nomFiche;
        this.descriptionFiche = descriptionFiche;
    }

    public String getNomFiche() {
        return nomFiche;
    }

    public void setNomFiche(String nomFiche) {
        this.nomFiche = nomFiche;
    }

    public String getDescriptionFiche() {
        return descriptionFiche;
    }

    public void setDescriptionFiche(String descriptionFiche) {
        this.descriptionFiche = descriptionFiche;
    }
}
