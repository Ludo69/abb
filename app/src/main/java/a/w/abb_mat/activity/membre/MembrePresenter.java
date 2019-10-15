package a.w.abb_mat.activity.membre;

import android.util.Log;
import android.widget.Toast;

import java.util.List;

import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Bloc;
import a.w.abb_mat.model.Detendeur;
import a.w.abb_mat.model.Historique;
import a.w.abb_mat.model.Membre;
import a.w.abb_mat.model.Stab;
import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MembrePresenter {

    private MembreView view;

    public MembrePresenter(MembreView membreView) { this.view = membreView; }

    void getData() {

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Membre>> call = apiInterface.getMembres();
        call.enqueue(new Callback<List<Membre>>() {
            @Override
            public void onResponse(Call<List<Membre>> call, Response<List<Membre>> response) {
                view.hideLoading();
                if(response.isSuccessful() && response.body() != null) {
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Membre>> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }


    void updateStab(int idstab, String emprunteurstab, String codeuniquestab) {

        view.showLoading();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Stab> call = apiInterface.updateStab(idstab, emprunteurstab, codeuniquestab);
        call.enqueue(new Callback<Stab>() {
            @Override
            public void onResponse(@NonNull Call<Stab> call,@NonNull Response<Stab> response) {
                view.hideLoading();
                if(response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if(success){
                        //view.onGetResult(response.body().getMessage());
                    } else {
                        view.onErrorLoading(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Stab> call,@NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }

    void updateDetendeur(int iddetendeur, String emprunteurdetendeur, String codeuniquedetendeur) {

        view.showLoading();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Detendeur> call = apiInterface.updateDetendeur(iddetendeur, emprunteurdetendeur, codeuniquedetendeur);
        call.enqueue(new Callback<Detendeur>() {
            @Override
            public void onResponse(@NonNull Call<Detendeur> call,@NonNull Response<Detendeur> response) {
                view.hideLoading();
                if(response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if(success){
                        //view.onGetResult(response.body().getMessage());
                    } else {
                        view.onErrorLoading(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Detendeur> call,@NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }

    void updateBloc(int idbloc, String emprunteurbloc, String codeuniquebloc) {

        view.showLoading();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Bloc> call = apiInterface.updateBloc(idbloc, emprunteurbloc, codeuniquebloc);
        call.enqueue(new Callback<Bloc>() {
            @Override
            public void onResponse(@NonNull Call<Bloc> call,@NonNull Response<Bloc> response) {
                view.hideLoading();
                if(response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if(success){
                        //view.onGetResult(response.body().getMessage());
                    } else {
                        view.onErrorLoading(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Bloc> call,@NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }

    void inserthistorique(int typemat, String nummat, String datepret, String daterestitution, String emprunteur, String codeunique) {

        view.showLoading();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Historique> call = apiInterface.inserthistorique(typemat, nummat, datepret, daterestitution, emprunteur, codeunique);
        call.enqueue(new Callback<Historique>() {
            @Override
            public void onResponse(@NonNull Call<Historique> call, @NonNull Response<Historique> response) {
                view.hideLoading();
                if(response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if(success){
                        //view.onGetResult(response.body().getMessage());
                    } else {
                        view.onErrorLoading(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Historique> call,@NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }

}

