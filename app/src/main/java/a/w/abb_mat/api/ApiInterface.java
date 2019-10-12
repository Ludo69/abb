package a.w.abb_mat.api;

import java.util.List;

import a.w.abb_mat.model.Stab;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("save.php")
    Call<Stab> saveStab(
        @Field("title") String title,
        @Field("note") String note,
        @Field("color") int color
    );

    @GET("notes.php")
    Call<List<Stab>> getStabs();

    @FormUrlEncoded
    @POST("update.php")
    Call<Stab> updateStab(
            @Field("id") int id,
            @Field("title") String title,
            @Field("note") String note,
            @Field("color") int color
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<Stab> deleteStab(
            @Field("id") int id
    );

}
