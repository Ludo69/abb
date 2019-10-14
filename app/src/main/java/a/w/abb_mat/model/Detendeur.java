package a.w.abb_mat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detendeur {

    @Expose
    @SerializedName("iddetendeur") private int iddetendeur;
    @Expose
    @SerializedName("numdetendeur") private String numdetendeur;
    @Expose
    @SerializedName("commentairedetendeur") private String commentairedetendeur;
    @Expose
    @SerializedName("dispodetendeur") private int dispodetendeur;
    @Expose
    @SerializedName("emprunteurdetendeur") private String emprunteurdetendeur;
    @Expose
    @SerializedName("revisiondetendeur") private String revisiondetendeur;
    @Expose
    @SerializedName("codeuniquedetendeur") private String codeuniquedetendeur;
    @Expose
    @SerializedName("success") private boolean success;
    @Expose
    @SerializedName("message") private String message;


    public int getIddetendeur() {
        return iddetendeur;
    }

    public void setIddetendeur(int iddetendeur) {
        this.iddetendeur = iddetendeur;
    }

    public String getNumdetendeur() {
        return numdetendeur;
    }

    public void setNumdetendeur(String numdetendeur) {
        this.numdetendeur = numdetendeur;
    }

    public String getCommentairedetendeur() {
        return commentairedetendeur;
    }

    public void setCommentairedetendeur(String commentairedetendeur) {
        this.commentairedetendeur = commentairedetendeur;
    }

    public int getDispodetendeur() {
        return dispodetendeur;
    }

    public void setDispodetendeur(int dispodetendeur) {
        this.dispodetendeur = dispodetendeur;
    }

    public String getEmprunteurdetendeur() {
        return emprunteurdetendeur;
    }

    public void setEmprunteurdetendeur(String emprunteurdetendeur) {
        this.emprunteurdetendeur = emprunteurdetendeur;
    }

    public String getRevisiondetendeur() {
        return revisiondetendeur;
    }

    public void setRevisiondetendeur(String revisiondetendeur) {
        this.revisiondetendeur = revisiondetendeur;
    }

    public String getCodeuniquedetendeur() {
        return codeuniquedetendeur;
    }

    public void setCodeuniquedetendeur(String codeuniquedetendeur) {
        this.codeuniquedetendeur = codeuniquedetendeur;
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
