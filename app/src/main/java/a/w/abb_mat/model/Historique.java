package a.w.abb_mat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Historique {

    @Expose
    @SerializedName("idhistorique") private int idhistorique;
    @Expose
    @SerializedName("typemat") private int typemat;
    @Expose
    @SerializedName("nummat") private String nummat;
    @Expose
    @SerializedName("datepret") private String datepret;
    @Expose
    @SerializedName("daterestitution") private String daterestitution;
    @Expose
    @SerializedName("emprunteur") private String emprunteur;
    @Expose
    @SerializedName("codeunique") private String codeunique;
    @Expose
    @SerializedName("success") private boolean success;
    @Expose
    @SerializedName("message") private String message;


    public int getIdhistorique() {
        return idhistorique;
    }

    public void setIdhistorique(int idhistorique) {
        this.idhistorique = idhistorique;
    }

    public int getTypemat() {
        return typemat;
    }

    public void setTypemat(int typemat) {
        this.typemat = typemat;
    }

    public String getNummat() {
        return nummat;
    }

    public void setNummat(String nummat) {
        this.nummat = nummat;
    }

    public String getDatepret() {
        return datepret;
    }

    public void setDatepret(String datepret) {
        this.datepret = datepret;
    }

    public String getDaterestitution() {
        return daterestitution;
    }

    public void setDaterestitution(String daterestitution) {
        this.daterestitution = daterestitution;
    }

    public String getEmprunteur() {
        return emprunteur;
    }

    public void setEmprunteur(String emprunteur) {
        this.emprunteur = emprunteur;
    }

    public String getCodeunique() {
        return codeunique;
    }

    public void setCodeunique(String codeunique) {
        this.codeunique = codeunique;
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
