package a.w.abb_mat.api;

import java.util.List;

import a.w.abb_mat.model.Historique;
import a.w.abb_mat.model.Membre;
import a.w.abb_mat.model.Stab;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("membres.php")
    Call<List<Membre>> getMembres();

    @GET("stabs.php")
    Call<List<Stab>> getStabs();

    @FormUrlEncoded
    @POST("updatestab.php")
    Call<Stab> updateStab(
            @Field("idstab") int idstab,
            @Field("emprunteurstab") String emprunteurstab,
            @Field("codeuniquestab") String codeuniquestab
    );

    @FormUrlEncoded
    @POST("updatestabrestitution.php")
    Call<Stab> updateStabRestitution(
            @Field("idstab") int idstab
    );

    @FormUrlEncoded
    @POST("inserthistorique.php")
    Call<Historique> inserthistorique(
            @Field("typemat") String typemat,
            @Field("nummat") String nummat,
            @Field("datepret") String datepret,
            @Field("daterestitution") String daterestitution,
            @Field("emprunteur") String emprunteur,
            @Field("codeunique") String codeunique
    );

    @FormUrlEncoded
    @POST("restitution.php")
    Call<Historique> restitution(
            @Field("codeunique") String codeunique,
            @Field("daterestitution") String daterestitution
    );

}
