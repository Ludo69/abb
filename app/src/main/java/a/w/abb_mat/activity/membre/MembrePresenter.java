package a.w.abb_mat.activity.membre;

import java.util.List;

import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Membre;
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

}

