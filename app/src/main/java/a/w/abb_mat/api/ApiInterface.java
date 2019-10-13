package a.w.abb_mat.api;

import java.util.List;

import a.w.abb_mat.model.Emprunt;
import a.w.abb_mat.model.Note;
import a.w.abb_mat.model.Sortie;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("listeemprunts.php")
    Call<List<Emprunt>> getEmprunts();

    @GET("sorties.php")
    Call<List<Sortie>> getSorties();

    @FormUrlEncoded
    @POST("savesortie.php")
    Call<Sortie> saveSortie(
            @Field("nomsortie") String nomsortie,
            @Field("datesortie") String datesortie
    );

    @FormUrlEncoded
    @POST("updatesortie.php")
    Call<Sortie> updateSortie(
            @Field("idsortie") int idsortie,
            @Field("nomsortie") String nomsortie,
            @Field("datesortie") String datesortie
    );

    @FormUrlEncoded
    @POST("deletesortie.php")
    Call<Sortie> deleteSortie(
            @Field("idsortie") int idsortie
    );

    @FormUrlEncoded
    @POST("save.php")
    Call<Note> saveNote(
        @Field("title") String title,
        @Field("note") String note,
        @Field("color") int color
    );

    @GET("notes.php")
    Call<List<Note>> getNotes();


    @FormUrlEncoded
    @POST("update.php")
    Call<Note> updateNote(
            @Field("id") int id,
            @Field("title") String title,
            @Field("note") String note,
            @Field("color") int color
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<Note> deleteNote(
            @Field("id") int id
    );

}
