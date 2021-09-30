package com.orange.womenassist;

import androidx.appcompat.app.AppCompatActivity;

public class ListeArticle extends AppCompatActivity {

    private String nomArticle;
    private String descriptionArticle;

    public ListeArticle() {
    }

    public ListeArticle(String nomArticle, String descriptionArticle) {
        this.nomArticle = nomArticle;
        this.descriptionArticle = descriptionArticle;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public String getDescriptionArticle() {
        return descriptionArticle;
    }

    public void setDescriptionArticle(String descriptionArticle) {
        this.descriptionArticle = descriptionArticle;
    }
}
