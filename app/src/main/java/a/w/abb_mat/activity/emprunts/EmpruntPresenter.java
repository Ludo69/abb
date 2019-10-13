package a.w.abb_mat.activity.emprunts;

import java.util.List;

import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Emprunt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmpruntPresenter {

    private EmpruntView view;

    public EmpruntPresenter(EmpruntView empruntView) {
        this.view = empruntView;
    }

    void getData() {

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Emprunt>> call = apiInterface.getEmprunts();
        call.enqueue(new Callback<List<Emprunt>>() {
            @Override
            public void onResponse(Call<List<Emprunt>> call, Response<List<Emprunt>> response) {
                view.hideLoading();
                if(response.isSuccessful() && response.body() != null) {
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Emprunt>> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }

}
