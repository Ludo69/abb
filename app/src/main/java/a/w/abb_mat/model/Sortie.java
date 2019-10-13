package a.w.abb_mat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sortie {
    @Expose
    @SerializedName("idsortie") private int idsortie;
    @Expose
    @SerializedName("nomsortie") private String nomsortie;
    @Expose
    @SerializedName("datesortie") private String datesortie;
    @Expose
    @SerializedName("success") private boolean success;
    @Expose
    @SerializedName("message") private String message;


    public int getIdsortie() {
        return idsortie;
    }

    public void setIdsortie(int idsortie) {
        this.idsortie = idsortie;
    }

    public String getNomsortie() {
        return nomsortie;
    }

    public void setNomsortie(String nomsortie) {
        this.nomsortie = nomsortie;
    }

    public String getDatesortie() {
        return datesortie;
    }

    public void setDatesortie(String datesortie) {
        this.datesortie = datesortie;
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

