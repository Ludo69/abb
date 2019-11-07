package a.w.abb_mat.activity.modifgonflage;

import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Bloc;
import a.w.abb_mat.model.Compteur;
import a.w.abb_mat.model.Gonflage;
import a.w.abb_mat.model.Stab;
import androidx.annotation.NonNull;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifGonflagePresenter {

    private  ModifGonflageView view;

    public ModifGonflagePresenter(ModifGonflageView view) {
        this.view = view;
    }

    void updateGonflageM(int id, int numbloc, int nbrbloc, float compteurdep, float compteurfinal, int temperature, int pressionfinale) {
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        retrofit2.Call<Gonflage> call = apiInterface.updategonflageM(id, numbloc, nbrbloc, compteurdep, compteurfinal, temperature, pressionfinale);
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

    void updatePression(int idbloc, int pressionbloc) {

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
                        //view.onAddSuccess(response.body().getMessage());
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

