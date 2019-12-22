package a.w.abb_mat.activity.histocomp;

import java.util.List;

import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.HistoComp;
import a.w.abb_mat.model.Historique;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class histoCompPresenter {
    private histoCompView view;

    public histoCompPresenter(histoCompView histocompView) { this.view = histocompView; }

    void getData(int type) {

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
            Call<List<HistoComp>> call = apiInterface.gethistocomp();
            call.enqueue(new Callback<List<HistoComp>>() {
                @Override
                public void onResponse(Call<List<HistoComp>> call, Response<List<HistoComp>> response) {
                    view.hideLoading();
                    if (response.isSuccessful() && response.body() != null) {
                        view.onGetResult(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<HistoComp>> call, Throwable t) {
                    view.hideLoading();
                    view.onErrorLoading(t.getLocalizedMessage());
                }
            });

    }
}
