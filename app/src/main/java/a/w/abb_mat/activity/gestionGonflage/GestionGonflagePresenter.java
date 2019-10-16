package a.w.abb_mat.activity.gestionGonflage;

import android.telecom.Call;
import android.util.Log;

import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Bloc;
import a.w.abb_mat.model.Gonflage;
import a.w.abb_mat.model.Stab;
import androidx.annotation.NonNull;
import retrofit2.Callback;
import retrofit2.Response;

public class GestionGonflagePresenter {

    private  GestionGonflageView view;

    public GestionGonflagePresenter(GestionGonflageView view) {
        this.view = view;
    }


    void insertGonflage(int numbloc, String gonfleur, int duree, int temperature, int pressionfinale, int saison) {
        int dureemajoree = 0;
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        retrofit2.Call<Gonflage> call = apiInterface.insertgonflage(numbloc, gonfleur, duree, temperature, pressionfinale, saison);
        call.enqueue(new Callback<Gonflage>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<Gonflage> call, @NonNull Response<Gonflage> response) {
                view.hideProgress();
                if(response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if(success){
                        view.onAddSuccess(response.body().getMessage());
                    } else {
                        view.onAddError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull retrofit2.Call<Gonflage> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onAddError(t.getLocalizedMessage());
            }
        });

    }

}
