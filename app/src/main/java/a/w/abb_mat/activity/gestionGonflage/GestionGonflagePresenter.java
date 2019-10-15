package a.w.abb_mat.activity.gestionGonflage;

import android.telecom.Call;
import android.util.Log;

import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Bloc;
import a.w.abb_mat.model.Stab;
import androidx.annotation.NonNull;
import retrofit2.Callback;
import retrofit2.Response;

public class GestionGonflagePresenter {

    private  GestionGonflageView view;

    public GestionGonflagePresenter(GestionGonflageView view) {
        this.view = view;
    }


    void insertGonflage(int idbloc, int pressionbloc) {

        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        retrofit2.Call<Bloc> call = apiInterface.updatePression(idbloc, pressionbloc);
        call.enqueue(new Callback<Bloc>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<Bloc> call, @NonNull Response<Bloc> response) {
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
            public void onFailure(@NonNull retrofit2.Call<Bloc> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onAddError(t.getLocalizedMessage());
            }
        });

    }
}
