package a.w.abb_mat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stat {

    @Expose
    @SerializedName("id") private int id;
    @Expose
    @SerializedName("nbrgonflage") private int nbrgonflage;
    @Expose
    @SerializedName("success") private boolean success;
    @Expose
    @SerializedName("message") private String message;

    public Stat(int id, int nbrgonflage) {
        this.id = id;
        this.nbrgonflage = nbrgonflage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbrgonflage() {
        return nbrgonflage;
    }

    public void setNbrgonflage(int nbrgonflage) {
        this.nbrgonflage = nbrgonflage;
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

    @Override
    public String toString() {
        return "Stat{" +
                "id=" + id +
                ", nbrgonflage=" + nbrgonflage +
                '}';
    }
}
