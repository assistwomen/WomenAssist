package com.orange.womenassist.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.Exclude;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class User implements Parcelable {

    //Attribut for credentials
    public static final String USER_ID = "id";
    public static final String USER_PSEUDO = "pseudo";
    public static final String USER_ROLE_ = "role";
    public static final String USER_ROLE_ASSOCIATION_PREFS = "role_association";
    public static final String USER_CREATE_AT = "create_at";
    public static final String USER_NOM_ASSOCIATION = "nom_association";



    private static final String USER_ROLE_ASSOCIATION = "ASSOCIATION";
    private static final String USER_ROLE_ADMINISTRATEUR = "ADMINISTRATEUR";
    private static final String USER_ROLE_MEMBRE = "MEMBRE";


    private String pseudo;
    @Exclude String id;
    private String password;
    private String role;
    private boolean is_exist;
    private String create_at;
    private String role_association;
    private String nom_association;
    private ArrayList<String> membre_association;
    private String avatar;


    protected User(Parcel in) {
        pseudo = in.readString();
        id = in.readString();
        password = in.readString();
        role = in.readString();
        is_exist = in.readByte() != 0;
        create_at = in.readString();
        role_association = in.readString();
        nom_association = in.readString();
        membre_association = in.createStringArrayList();
        avatar = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public static String getUserRoleAssociation() {
        return USER_ROLE_ASSOCIATION;
    }

    public static String getUserRoleAdministrateur() {
        return USER_ROLE_ADMINISTRATEUR;
    }

    public String getRole_association() {
        return role_association;
    }

    public void setRole_association(String role_association) {
        this.role_association = role_association;
    }

    public String getNom_association() {
        return nom_association;
    }

    public void setNom_association(String nom_association) {
        this.nom_association = nom_association;
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getMembre_association() {
        return membre_association;
    }

    public void setMembre_association(ArrayList<String> membre_association) {
        this.membre_association = membre_association;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

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



    public User(User user)
    {
        this.pseudo = user.pseudo;
        this.password = user.password;
        this.role = user.role;
        this.is_exist = true;
        this.create_at = new SimpleDateFormat("ddMMyyyy_HHmmss", Locale.getDefault()).format(new Date());
        this.role_association = user.role_association;
        this.nom_association = user.nom_association;
        this.membre_association = user.membre_association;
        this.avatar = user.avatar;
    }

    public User()
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
        parcel.writeString(pseudo);
        parcel.writeString(id);
        parcel.writeString(password);
        parcel.writeString(role);
        parcel.writeByte((byte) (is_exist ? 1 : 0));
        parcel.writeString(create_at);
        parcel.writeString(role_association);
        parcel.writeString(nom_association);
        parcel.writeStringList(membre_association);
        parcel.writeString(avatar);
    }
}
