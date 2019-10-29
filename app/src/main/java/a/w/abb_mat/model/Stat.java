package a.w.abb_mat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stat {

    @Expose
    @SerializedName("nbrgonflage") private String nbrgonflage;
    @Expose
    @SerializedName("success") private boolean success;
    @Expose
    @SerializedName("message") private String message;

    public String getNbrgonflage() {
        return nbrgonflage;
    }

    public void setNbrgonflage(String nbrgonflage) {
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
}
