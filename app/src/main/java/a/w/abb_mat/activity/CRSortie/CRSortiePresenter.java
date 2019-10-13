package a.w.abb_mat.activity.CRSortie;

import a.w.abb_mat.activity.CRSortie.CRSortieView;
import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Sortie;
import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CRSortiePresenter {
    private CRSortieView view;

    public CRSortiePresenter(CRSortieView view) {
        this.view = view;
    }

    void saveSortie(final String nomsortie, final String datesortie) {
        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        retrofit2.Call<Sortie> call = apiInterface.saveSortie(nomsortie, datesortie);

        call.enqueue(new Callback<Sortie>() {
            @Override
            public void onResponse(@NonNull Call<Sortie> call, @NonNull Response<Sortie> response) {
                view.hideProgress();

                if(response.isSuccessful() && response.body() != null){

                    Boolean success = response.body().getSuccess();
                    if(success) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }


                }

            }

            public void onFailure(@NonNull Call<Sortie> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    void updateSortie(int id, String nomsortie, String datesortie) {

        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Sortie> call = apiInterface.updateSortie(id, nomsortie, datesortie);
        call.enqueue(new Callback<Sortie>() {
            @Override
            public void onResponse(@NonNull Call<Sortie> call,@NonNull Response<Sortie> response) {
                view.hideProgress();
                if(response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if(success){
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Sortie> call,@NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });

    }

    void deleteSortie(int idsortie) {
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Sortie> call = apiInterface.deleteSortie(idsortie);
        call.enqueue(new Callback<Sortie>() {
            @Override
            public void onResponse(@NonNull Call<Sortie> call,@NonNull Response<Sortie> response) {
                view.hideProgress();
                if(response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if(success){
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Sortie> call,@NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

}

