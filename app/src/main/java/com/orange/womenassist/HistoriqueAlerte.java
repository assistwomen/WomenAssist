package com.orange.womenassist;

public class HistoriqueAlerte {

    private String dateAlert;
    private String longitude;
    private String latitude;
    private  int photo;

    public HistoriqueAlerte() {
    }

    public HistoriqueAlerte(String dateAlert, String longitude, String latitude, int photo) {
        this.dateAlert = dateAlert;
        this.longitude = longitude;
        this.latitude = latitude;
        this.photo = photo;
    }

    public String getDateAlert() {
        return dateAlert;
    }

    public void setDateAlert(String dateAlert) {
        this.dateAlert = dateAlert;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
