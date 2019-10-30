package a.w.abb_mat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stat {

    @Expose
    @SerializedName("id") private int id;
    @Expose
    @SerializedName("success") private boolean success;
    @Expose
    @SerializedName("nbrgonflage") private String nbrgonflage;
    @Expose
    @SerializedName("tpsgonflage") private String tpsgonflage;
    @Expose
    @SerializedName("tpsmajoree") private String tpsmajoree;
    @Expose
    @SerializedName("moytemp") private String moytemp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getNbrgonflage() {
        return nbrgonflage;
    }

    public void setNbrgonflage(String nbrgonflage) {
        this.nbrgonflage = nbrgonflage;
    }

    public String getTpsgonflage() {
        return tpsgonflage;
    }

    public void setTpsgonflage(String tpsgonflage) {
        this.tpsgonflage = tpsgonflage;
    }

    public String getTpsmajoree() {
        return tpsmajoree;
    }

    public void setTpsmajoree(String tpsmajoree) {
        this.tpsmajoree = tpsmajoree;
    }

    public String getMoytemp() {
        return moytemp;
    }

    public void setMoytemp(String moytemp) {
        this.moytemp = moytemp;
    }

    public Boolean getSuccess() {
        return success;
    }

    @Override
    public String toString() {
        return "Stat{" +
                "id=" + id +
                ", nbrgonflage=" + nbrgonflage +
                '}';
    }
}
