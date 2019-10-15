package a.w.abb_mat.activity.detendeur;

import android.util.Log;
import android.widget.Toast;

import java.util.List;

import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Historique;
import a.w.abb_mat.model.Detendeur;
import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetendeurPresenter {

    private DetendeurView view;

    public DetendeurPresenter(DetendeurView detendeurView) { this.view = detendeurView; }

    void getData() {

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Detendeur>> call = apiInterface.getDetendeurs();
        call.enqueue(new Callback<List<Detendeur>>() {
            @Override
            public void onResponse(Call<List<Detendeur>> call, Response<List<Detendeur>> response) {
                view.hideLoading();
                if(response.isSuccessful() && response.body() != null) {
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Detendeur>> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }

    void updateDetendeurRestitution(int iddetendeur) {

        view.showLoading();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Detendeur> call = apiInterface.updateDetendeurRestitution(iddetendeur);
        call.enqueue(new Callback<Detendeur>() {
            @Override
            public void onResponse(@NonNull Call<Detendeur> call, @NonNull Response<Detendeur> response) {
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

    void restitution(String codeunique, String daterestitution) {
        view.showLoading();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Historique> call = apiInterface.restitution(codeunique, daterestitution);
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
