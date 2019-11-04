package a.w.abb_mat.activity.detendeurmaintenance;

import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Bloc;
import a.w.abb_mat.model.Detendeur;
import a.w.abb_mat.model.Stab;
import androidx.annotation.NonNull;
import retrofit2.Callback;
import retrofit2.Response;
public class DetendeurMaintenancePresenter {

    private  DetendeurMaintenanceView view;

    public DetendeurMaintenancePresenter(DetendeurMaintenanceView view) {
        this.view = view;
    }

    void updateDetendeurM(int iddetendeur, String commentairedetendeur, int dispodetendeur) {

        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        retrofit2.Call<Detendeur> call = apiInterface.updateDetendeurM(iddetendeur, commentairedetendeur, dispodetendeur);
        call.enqueue(new Callback<Detendeur>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<Detendeur> call, @NonNull Response<Detendeur> response) {
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
            public void onFailure(@NonNull retrofit2.Call<Detendeur> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onAddError(t.getLocalizedMessage());
            }
        });

    }

}

