package a.w.abb_mat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Compteur {
    @Expose
    @SerializedName("compteur") private float compteur;
    @Expose
    @SerializedName("success") private boolean success;
    @Expose
    @SerializedName("message") private String message;

    public float getCompteur() {
        return compteur;
    }

    public void setCompteur(float compteur) {
        this.compteur = compteur;
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

