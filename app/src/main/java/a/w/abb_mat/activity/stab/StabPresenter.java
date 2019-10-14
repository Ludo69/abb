package a.w.abb_mat.activity.stab;

import android.util.Log;
import android.widget.Toast;

import java.util.List;

import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Historique;
import a.w.abb_mat.model.Stab;
import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StabPresenter {

    private StabView view;

    public StabPresenter(StabView stabView) { this.view = stabView; }

    void getData() {

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Stab>> call = apiInterface.getStabs();
        call.enqueue(new Callback<List<Stab>>() {
            @Override
            public void onResponse(Call<List<Stab>> call, Response<List<Stab>> response) {
                view.hideLoading();
                if(response.isSuccessful() && response.body() != null) {
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Stab>> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }

    void updateStabRestitution(int idstab) {

        view.showLoading();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Stab> call = apiInterface.updateStabRestitution(idstab);
        call.enqueue(new Callback<Stab>() {
            @Override
            public void onResponse(@NonNull Call<Stab> call, @NonNull Response<Stab> response) {
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

    void restitution(String codeunique, String daterestitution) {
        Log.d("*****", codeunique + " " + daterestitution);
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
