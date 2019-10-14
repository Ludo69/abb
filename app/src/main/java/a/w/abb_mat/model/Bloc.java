package a.w.abb_mat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bloc {

    @Expose
    @SerializedName("idbloc") private int idbloc;
    @Expose
    @SerializedName("numbloc") private String numbloc;
    @Expose
    @SerializedName("commentairebloc") private String commentairebloc;
    @Expose
    @SerializedName("dispobloc") private int dispobloc;
    @Expose
    @SerializedName("emprunteurbloc") private String emprunteurbloc;
    @Expose
    @SerializedName("pressionbloc") private String pressionbloc;
    @Expose
    @SerializedName("codeuniquebloc") private String codeuniquebloc;
    @Expose
    @SerializedName("success") private boolean success;
    @Expose
    @SerializedName("message") private String message;


    public int getIdbloc() {
        return idbloc;
    }

    public void setIdbloc(int idbloc) {
        this.idbloc = idbloc;
    }

    public String getNumbloc() {
        return numbloc;
    }

    public void setNumbloc(String numbloc) {
        this.numbloc = numbloc;
    }

    public String getCommentairebloc() {
        return commentairebloc;
    }

    public void setCommentairebloc(String commentairebloc) {
        this.commentairebloc = commentairebloc;
    }

    public int getDispobloc() {
        return dispobloc;
    }

    public void setDispobloc(int dispobloc) {
        this.dispobloc = dispobloc;
    }

    public String getEmprunteurbloc() {
        return emprunteurbloc;
    }

    public void setEmprunteurbloc(String emprunteurbloc) {
        this.emprunteurbloc = emprunteurbloc;
    }

    public String getPressionbloc() {
        return pressionbloc;
    }

    public void setPressionbloc(String pressionbloc) {
        this.pressionbloc = pressionbloc;
    }

    public String getCodeuniquebloc() {
        return codeuniquebloc;
    }

    public void setCodeuniquebloc(String codeuniquebloc) {
        this.codeuniquebloc = codeuniquebloc;
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
