package com.orange.womenassist.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Association implements Parcelable {

    private String abreviation;
    private String administrateur;
    private String create_at;
    private String description;
    private String email;
    private String emplacement;
    private String objectif;
    private String nom_association;
    private ArrayList<String> responsable;
    private ArrayList<String> membre;
    private String telephone;
    private boolean is_exist;


    protected Association(Parcel in) {
        abreviation = in.readString();
        administrateur = in.readString();
        create_at = in.readString();
        description = in.readString();
        email = in.readString();
        emplacement = in.readString();
        objectif = in.readString();
        nom_association = in.readString();
        responsable = in.createStringArrayList();
        membre = in.createStringArrayList();
        telephone = in.readString();
        is_exist = in.readByte() != 0;
    }

    public static final Creator<Association> CREATOR = new Creator<Association>() {
        @Override
        public Association createFromParcel(Parcel in) {
            return new Association(in);
        }

        @Override
        public Association[] newArray(int size) {
            return new Association[size];
        }
    };

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    public String getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(String administrateur) {
        this.administrateur = administrateur;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public String getNom_association() {
        return nom_association;
    }

    public void setNom_association(String nom_association) {
        this.nom_association = nom_association;
    }

    public ArrayList<String> getResponsable() {
        return responsable;
    }

    public void setResponsable(ArrayList<String> responsable) {
        this.responsable = responsable;
    }

    public ArrayList<String> getMembre() {
        return membre;
    }

    public void setMembre(ArrayList<String> membre) {
        this.membre = membre;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isIs_exist() {
        return is_exist;
    }

    public void setIs_exist(boolean is_exist) {
        this.is_exist = is_exist;
    }

    public Association(Association association)
    {

    }

    public Association()
    {
        this.is_exist = true;
        this.create_at = new SimpleDateFormat("ddMMyyyy_HHmmss", Locale.getDefault()).format(new Date());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(abreviation);
        parcel.writeString(administrateur);
        parcel.writeString(create_at);
        parcel.writeString(description);
        parcel.writeString(email);
        parcel.writeString(emplacement);
        parcel.writeString(nom_association);
        parcel.writeStringList(responsable);
        parcel.writeStringList(membre);
        parcel.writeString(telephone);
        parcel.writeString(objectif);
        parcel.writeByte((byte) (is_exist ? 1 : 0));
    }
}
