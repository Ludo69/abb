package a.w.abb_mat.api;

import java.util.List;

import a.w.abb_mat.model.Bloc;
import a.w.abb_mat.model.Compteur;
import a.w.abb_mat.model.Detendeur;
import a.w.abb_mat.model.Gonflage;
import a.w.abb_mat.model.Gonfleur;
import a.w.abb_mat.model.Historique;
import a.w.abb_mat.model.Membre;
import a.w.abb_mat.model.Stab;
import a.w.abb_mat.model.Stat;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    @GET("gonflages.php")
    Call<List<Gonflage>> getgonflages();

    @GET("historiquesstabs.php")
    Call<List<Historique>> gethistoriquesstabs();

    @GET("historiquesdetendeurs.php")
    Call<List<Historique>> gethistoriquesdetendeurs();

    @GET("historiquesblocs.php")
    Call<List<Historique>> gethistoriquesblocs();

    @GET("gonfleurs.php")
    Call<List<Gonfleur>> getGonfleurs();

    @GET("stats.php")
    Call<Stat> GetNbr();

    @GET("mailgonflage.php")
    Call<Stat> GetMail(@Query("mail") String mail);

    @GET("fichier.php")
    Call<Stat> Getmail2(@Query("mail") String mail2);

    @GET("histogonflage.php")
    Call<Stat> Getmail3(@Query("mail") String mail3);

    @FormUrlEncoded
    @POST("updatestab.php")
    Call<Stab> updateStab(
            @Field("idstab") int idstab,
            @Field("emprunteurstab") String emprunteurstab,
            @Field("codeuniquestab") String codeuniquestab
    );

    @FormUrlEncoded
    @POST("updatestabM.php")
    Call<Stab> updateStabM(
            @Field("idstab") int idstab,
            @Field("commentairestab") String commentairestab,
            @Field("dispostab") int dispostab
    );

    @FormUrlEncoded
    @POST("updatedetendeur.php")
    Call<Detendeur> updateDetendeur(
            @Field("iddetendeur") int iddetendeur,
            @Field("emprunteurdetendeur") String emprunteurdetendeur,
            @Field("codeuniquedetendeur") String codeuniquedetendeur
    );

    @FormUrlEncoded
    @POST("updatedetendeurM.php")
    Call<Detendeur> updateDetendeurM(
            @Field("iddetendeur") int iddetendeur,
            @Field("commentairedetendeur") String commentairedetendeur,
            @Field("dispodetendeur") int dispodetendeur,
            @Field("daterevision") String daterevision
    );

    @FormUrlEncoded
    @POST("updatebloc.php")
    Call<Bloc> updateBloc(
            @Field("idbloc") int idbloc,
            @Field("emprunteurbloc") String emprunteurbloc,
            @Field("codeuniquebloc") String codeuniquebloc
    );

    @FormUrlEncoded
    @POST("updateblocM.php")
    Call<Bloc> updateBlocM(
            @Field("idbloc") int idbloc,
            @Field("commentairebloc") String commentairebloc,
            @Field("dispobloc") int dispobloc
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
    @POST("selectgonflage.php")
    Call<Gonflage> selectgonflage(
            @Field("id") int id
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
    @POST("insertstab.php")
    Call<Stab> insertstab(
            @Field("numstab") String numstab,
            @Field("taillestab") String taillestab,
            @Field("commentairestab") String commentairestab,
            @Field("dispostab") int dispostab
    );

    @FormUrlEncoded
    @POST("insertdetendeur.php")
    Call<Stab> insertdetendeur(
            @Field("numdetendeur") String numdetendeur,
            @Field("commentairedetendeur") String commentairedetendeur,
            @Field("dispodetendeur") int dispodetendeur
    );

    @FormUrlEncoded
    @POST("insertbloc.php")
    Call<Bloc> insertbloc(
            @Field("numbloc") String numbloc,
            @Field("litragebloc") String litragebloc,
            @Field("commentairebloc") String commentairebloc,
            @Field("dispobloc") int dispobloc
    );

    @FormUrlEncoded
    @POST("restitution.php")
    Call<Historique> restitution(
            @Field("codeunique") String codeunique,
            @Field("daterestitution") String daterestitution
    );

    @FormUrlEncoded
    @POST("suppstab.php")
    Call<Stab> suppstab(
            @Field("idstab") int idstab
    );

    @FormUrlEncoded
    @POST("suppdetendeur.php")
    Call<Detendeur> suppdetendeur(
            @Field("iddetendeur") int iddetendeur
    );

    @FormUrlEncoded
    @POST("suppbloc.php")
    Call<Bloc> suppbloc(
            @Field("idbloc") int idbloc
    );

    @FormUrlEncoded
    @POST("insertgonflage.php")
    Call<Gonflage> insertgonflage(
            @Field("numbloc") int numbloc,
            @Field("numbloc2") int numbloc2,
            @Field("gonfleur") String gonfleur,
            @Field("compteurfinal") float compteurfinal,
            @Field("nbrbloc") int nbrbloc,
            @Field("temperature") int temperature,
            @Field("pressionfinale") int pressionfinale,
            @Field("saison") int saison
    );

    @FormUrlEncoded
    @POST("updategonflage.php")
    Call<Gonflage> updategonflage(
            @Field("id") int id,
            @Field("numbloc") int numbloc,
            @Field("gonfleur") String gonfleur,
            @Field("compteurfinal") float compteurfinal,
            @Field("nbrbloc") int nbrbloc,
            @Field("temperature") int temperature,
            @Field("pressionfinale") int pressionfinale,
            @Field("saison") int saison
    );

    @FormUrlEncoded
    @POST("updatecompteur.php")
    Call<Compteur> updatecompteur(
            @Field("compteur") float compteur
    );
}
