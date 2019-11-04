package a.w.abb_mat.activity.detendeurm;

import java.util.List;

import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Detendeur;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetendeurMPresenter {

    private DetendeurMView view;

    public DetendeurMPresenter(DetendeurMView detendeurMView) { this.view = detendeurMView; }

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

}
