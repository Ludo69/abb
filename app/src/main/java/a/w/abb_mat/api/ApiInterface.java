package a.w.abb_mat.api;

import java.util.List;

import a.w.abb_mat.model.Bloc;
import a.w.abb_mat.model.Detendeur;
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

    @GET("detendeurs.php")
    Call<List<Detendeur>> getDetendeurs();

    @GET("blocs.php")
    Call<List<Bloc>> getBlocs();

    @GET("historiques.php")
    Call<List<Historique>> gethistoriques();

    @FormUrlEncoded
    @POST("updatestab.php")
    Call<Stab> updateStab(
            @Field("idstab") int idstab,
            @Field("emprunteurstab") String emprunteurstab,
            @Field("codeuniquestab") String codeuniquestab
    );

    @FormUrlEncoded
    @POST("updatedetendeur.php")
    Call<Detendeur> updateDetendeur(
            @Field("iddetendeur") int iddetendeur,
            @Field("emprunteurdetendeur") String emprunteurdetendeur,
            @Field("codeuniquedetendeur") String codeuniquedetendeur
    );

    @FormUrlEncoded
    @POST("updatebloc.php")
    Call<Bloc> updateBloc(
            @Field("idbloc") int idbloc,
            @Field("emprunteurbloc") String emprunteurbloc,
            @Field("codeuniquebloc") String codeuniquebloc
    );

    @FormUrlEncoded
    @POST("updatestabrestitution.php")
    Call<Stab> updateStabRestitution(
            @Field("idstab") int idstab
    );

    @FormUrlEncoded
    @POST("updatedetendeurrestitution.php")
    Call<Detendeur> updateDetendeurRestitution(
            @Field("iddetendeur") int iddetendeur
    );

    @FormUrlEncoded
    @POST("updateblocrestitution.php")
    Call<Bloc> updateBlocRestitution(
            @Field("idbloc") int idbloc
    );

    @FormUrlEncoded
    @POST("updatepression.php")
    Call<Bloc> updatePression(
        @Field("idbloc") int idbloc,
        @Field("pressionbloc") int pressionbloc
    );

    @FormUrlEncoded
    @POST("inserthistorique.php")
    Call<Historique> inserthistorique(
            @Field("typemat") int typemat,
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
