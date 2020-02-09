package a.w.abb_mat.activity.blocmaintenance;

import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Bloc;
import a.w.abb_mat.model.Detendeur;
import a.w.abb_mat.model.Historique;
import a.w.abb_mat.model.Stab;
import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlocMaintenancePresenter {

    private  BlocMaintenanceView view;

    public BlocMaintenancePresenter(BlocMaintenanceView view) {
        this.view = view;
    }

    void updateBlocM(int idbloc, String commentairebloc, int dispobloc, String codeunique) {

        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        retrofit2.Call<Bloc> call = apiInterface.updateBlocM(idbloc, commentairebloc, dispobloc, codeunique);
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

    void inserthistorique(int typemat, String nummat, String datepret, String daterestitution, String emprunteur, String codeunique) {

        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Historique> call = apiInterface.inserthistorique(typemat, nummat, datepret, daterestitution, emprunteur, codeunique);
        call.enqueue(new Callback<Historique>() {
            @Override
            public void onResponse(@NonNull Call<Historique> call, @NonNull Response<Historique> response) {
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
            public void onFailure(@NonNull Call<Historique> call,@NonNull Throwable t) {
                view.hideProgress();
                view.onAddError(t.getLocalizedMessage());
            }
        });

    }

    void restitution(String codeunique, String daterestitution) {
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Historique> call = apiInterface.restitution(codeunique, daterestitution);
        call.enqueue(new Callback<Historique>() {
            @Override
            public void onResponse(@NonNull Call<Historique> call, @NonNull Response<Historique> response) {
                view.hideProgress();
                if(response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if(success){
                        //view.onGetResult(response.body().getMessage());
                    } else {
                        view.onAddError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Historique> call,@NonNull Throwable t) {
                view.hideProgress();
                view.onAddError(t.getLocalizedMessage());
            }
        });

    }

}

