package a.w.abb_mat.activity.bloc;

import android.util.Log;
import android.widget.Toast;

import java.util.List;

import a.w.abb_mat.activity.bloc.BlocView;
import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Historique;
import a.w.abb_mat.model.Bloc;
import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlocPresenter {

    private BlocView view;

    public BlocPresenter(BlocView blocView) { this.view = blocView; }

    void getData() {

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Bloc>> call = apiInterface.getBlocs();
        call.enqueue(new Callback<List<Bloc>>() {
            @Override
            public void onResponse(Call<List<Bloc>> call, Response<List<Bloc>> response) {
                view.hideLoading();
                if(response.isSuccessful() && response.body() != null) {
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Bloc>> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }

    void updateBlocRestitution(int idbloc) {

        view.showLoading();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Bloc> call = apiInterface.updateBlocRestitution(idbloc);
        call.enqueue(new Callback<Bloc>() {
            @Override
            public void onResponse(@NonNull Call<Bloc> call, @NonNull Response<Bloc> response) {
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

    void restitution(String codeunique, String daterestitution) {
        Log.d("*****RESTITUTION", codeunique + " " + daterestitution);
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

