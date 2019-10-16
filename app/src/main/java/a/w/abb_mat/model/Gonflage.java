package a.w.abb_mat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gonflage {

    @Expose
    @SerializedName("id") private int id;
    @Expose
    @SerializedName("numbloc") private int numbloc;
    @Expose
    @SerializedName("gonfleur") private String gonfleur;
    @Expose
    @SerializedName("duree") private int duree;
    @Expose
    @SerializedName("temperature") private int temperature;
    @Expose
    @SerializedName("coeff") private float coeff;
    @Expose
    @SerializedName("dureemajoree") private int dureemajoree;
    @Expose
    @SerializedName("pressionfinale") private int pressionfinale;
    @Expose
    @SerializedName("saison") private int saison;
    @Expose
    @SerializedName("success") private boolean success;
    @Expose
    @SerializedName("message") private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumbloc() {
        return numbloc;
    }

    public void setNumbloc(int numbloc) {
        this.numbloc = numbloc;
    }

    public String getGonfleur() {
        return gonfleur;
    }

    public void setGonfleur(String gonfleur) {
        this.gonfleur = gonfleur;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public float getCoeff() {
        return coeff;
    }

    public void setCoeff(float coeff) {
        this.coeff = coeff;
    }

    public int getDureemajoree() {
        return dureemajoree;
    }

    public void setDureemajoree(int dureemajoree) {
        this.dureemajoree = dureemajoree;
    }

    public int getPressionfinale() {
        return pressionfinale;
    }

    public void setPressionfinale(int pressionfinale) {
        this.pressionfinale = pressionfinale;
    }

    public int getSaison() {
        return saison;
    }

    public void setSaison(int saison) {
        this.saison = saison;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }
}
