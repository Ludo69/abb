package a.w.abb_mat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoComp {

    @Expose
    @SerializedName("id") private int id;
    @Expose
    @SerializedName("dateent") private String dateent;
    @Expose
    @SerializedName("nbrgonflage") private int nbrgonflage;
    @Expose
    @SerializedName("tpsgonflage") private int tpsgonflage;
    @Expose
    @SerializedName("tpsmajoree") private int tpsmajoree;
    @Expose
    @SerializedName("moytemp") private int moytemp;
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

    public String getDateent() {
        return dateent;
    }

    public void setDateent(String dateent) {
        this.dateent = dateent;
    }

    public int getNbrgonflage() {
        return nbrgonflage;
    }

    public void setNbrgonflage(int nbrgonflage) {
        this.nbrgonflage = nbrgonflage;
    }

    public int getTpsgonflage() {
        return tpsgonflage;
    }

    public void setTpsgonflage(int tpsgonflage) {
        this.tpsgonflage = tpsgonflage;
    }

    public int getTpsmajoree() {
        return tpsmajoree;
    }

    public void setTpsmajoree(int tpsmajoree) {
        this.tpsmajoree = tpsmajoree;
    }

    public int getMoytemp() {
        return moytemp;
    }

    public void setMoytemp(int moytemp) {
        this.moytemp = moytemp;
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
