package a.w.abb_mat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Emprunt {

        @Expose
        @SerializedName("idemprunt") private int idemprunt;
        @Expose
        @SerializedName("nomsortieemprunt") private String nomsortieemprunt;
        @Expose
        @SerializedName("typeemprunt") private String typeemprunt;
        @Expose
        @SerializedName("emprunteur") private String emprunteur;
        @Expose
        @SerializedName("numtype") private String numtype;
        @Expose
        @SerializedName("dateemprunt") private String dateemprunt;
        @Expose
        @SerializedName("success") private boolean success;
        @Expose
        @SerializedName("message") private String message;


    public int getIdemprunt() {
        return idemprunt;
    }

    public void setIdemprunt(int idemprunt) {
        this.idemprunt = idemprunt;
    }

    public String getNomsortieemprunt() {
        return nomsortieemprunt;
    }

    public void setNomsortieemprunt(String nomsortieemprunt) {
        this.nomsortieemprunt = nomsortieemprunt;
    }

    public String getTypeemprunt() {
        return typeemprunt;
    }

    public void setTypeemprunt(String typeemprunt) {
        this.typeemprunt = typeemprunt;
    }

    public String getEmprunteur() {
        return emprunteur;
    }

    public void setEmprunteur(String emprunteur) {
        this.emprunteur = emprunteur;
    }

    public String getNumtype() {
        return numtype;
    }

    public void setNumtype(String numtype) {
        this.numtype = numtype;
    }

    public String getDateemprunt() {
        return dateemprunt;
    }

    public void setDateemprunt(String dateemprunt) {
        this.dateemprunt = dateemprunt;
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

