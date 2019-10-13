package a.w.abb_mat.activity.sortie;

import java.util.List;

import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Sortie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SortiePresenter {

    private SortieView view;

    public SortiePresenter(SortieView sortieView) {
        this.view = sortieView;
    }

    void getData() {

        view.showLoading2();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Sortie>> call = apiInterface.getSorties();
        call.enqueue(new Callback<List<Sortie>>() {
            @Override
            public void onResponse(Call<List<Sortie>> call, Response<List<Sortie>> response) {
                view.hideLoading2();
                if(response.isSuccessful() && response.body() != null) {
                    view.onGetResult2(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Sortie>> call, Throwable t) {
                view.hideLoading2();
                view.onErrorLoading2(t.getLocalizedMessage());
            }
        });

    }

}
