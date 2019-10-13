package a.w.abb_mat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stab {

    @Expose
    @SerializedName("idstab") private int idstab;
    @Expose
    @SerializedName("numstab") private String numstab;
    @Expose
    @SerializedName("taillestab") private String taillestab;
    @Expose
    @SerializedName("commentairestab") private String commentairestab;
    @Expose
    @SerializedName("dispostab") private int dispostab;
    @Expose
    @SerializedName("emprunteurstab") private String emprunteurstab;
    @Expose
    @SerializedName("codeuniquestab") private String codeuniquestab;
    @Expose
    @SerializedName("success") private boolean success;
    @Expose
    @SerializedName("message") private String message;


    public int getIdstab() {
        return idstab;
    }

    public void setIdstab(int idstab) {
        this.idstab = idstab;
    }

    public String getNumstab() {
        return numstab;
    }

    public void setNumstab(String numstab) {
        this.numstab = numstab;
    }

    public String getTaillestab() {
        return taillestab;
    }

    public void setTaillestab(String taillestab) {
        this.taillestab = taillestab;
    }

    public String getCommentairestab() {
        return commentairestab;
    }

    public void setCommentairestab(String  commentairestab) {
        this.commentairestab = commentairestab;
    }

    public int getDispostab() {
        return dispostab;
    }

    public void setDispostab(int dispostab) {
        this.dispostab = dispostab;
    }

    public String getEmprunteurstab() {
        return emprunteurstab;
    }

    public void setEmprunteurstab(String emprunteurstab) {
        this.emprunteurstab = emprunteurstab;
    }

    public String getCodeuniquestab() {
        return codeuniquestab;
    }

    public void setCodeuniquestab(String codeuniquestab) {
        this.codeuniquestab = codeuniquestab;
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
