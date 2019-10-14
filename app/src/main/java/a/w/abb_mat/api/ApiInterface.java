package a.w.abb_mat.api;

import java.util.List;

import a.w.abb_mat.model.Membre;
import a.w.abb_mat.model.Note;
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

    @GET("notes.php")
    Call<List<Note>> getNotes();


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

}
