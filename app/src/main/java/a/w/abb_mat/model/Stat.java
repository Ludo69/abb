package a.w.abb_mat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stat {

    @Expose
    @SerializedName("id") private int id;
    @Expose
    @SerializedName("nbrgonflage") private int nbrgonflage;

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

    @Override
    public String toString() {
        return "Stat{" +
                "id=" + id +
                ", nbrgonflage=" + nbrgonflage +
                '}';
    }
}
