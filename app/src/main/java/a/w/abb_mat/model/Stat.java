package a.w.abb_mat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stat {

    @Expose
    @SerializedName("id") private int id;
    //@Expose
    //@SerializedName("nbrgonflage") private int nbrgonflage;
    @Expose
    @SerializedName("success") private boolean success;
    @Expose
    @SerializedName("nbrgonflage") private String nbrgonflage;
    @Expose
    @SerializedName("dureegonflage") private String dureegonflage;

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

    public String getDureegonflage() {
        return dureegonflage;
    }

    public void setDureegonflage(String dureegonflage) {
        this.dureegonflage = dureegonflage;
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
