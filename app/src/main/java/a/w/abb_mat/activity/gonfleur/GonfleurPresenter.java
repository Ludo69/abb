package a.w.abb_mat.activity.gonfleur;

import java.util.List;

import a.w.abb_mat.activity.gonfleur.GonfleurView;
import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Bloc;
import a.w.abb_mat.model.Gonfleur;
import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GonfleurPresenter {

    private GonfleurView view;

    public GonfleurPresenter(GonfleurView gonfleurView) { this.view = gonfleurView; }

    void getData() {

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Gonfleur>> call = apiInterface.getGonfleurs();
        call.enqueue(new Callback<List<Gonfleur>>() {
            @Override
            public void onResponse(Call<List<Gonfleur>> call, Response<List<Gonfleur>> response) {
                view.hideLoading();
                if(response.isSuccessful() && response.body() != null) {
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Gonfleur>> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }
}

