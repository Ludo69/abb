package a.w.abb_mat.activity.historique;

import java.util.List;

import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Historique;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoriquePresenter {

    private HistoriqueView view;

    public HistoriquePresenter(HistoriqueView historiqueView) { this.view = historiqueView; }

    void getData(int type) {

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        if(type == 3) {
            Call<List<Historique>> call = apiInterface.gethistoriques();
            call.enqueue(new Callback<List<Historique>>() {
                @Override
                public void onResponse(Call<List<Historique>> call, Response<List<Historique>> response) {
                    view.hideLoading();
                    if (response.isSuccessful() && response.body() != null) {
                        view.onGetResult(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<Historique>> call, Throwable t) {
                    view.hideLoading();
                    view.onErrorLoading(t.getLocalizedMessage());
                }
            });
        } else if(type == 0) {
            Call<List<Historique>> call = apiInterface.gethistoriquesstabs();
            call.enqueue(new Callback<List<Historique>>() {
                @Override
                public void onResponse(Call<List<Historique>> call, Response<List<Historique>> response) {
                    view.hideLoading();
                    if (response.isSuccessful() && response.body() != null) {
                        view.onGetResult(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<Historique>> call, Throwable t) {
                    view.hideLoading();
                    view.onErrorLoading(t.getLocalizedMessage());
                }
            });
        } else if(type == 1) {
            Call<List<Historique>> call = apiInterface.gethistoriquesdetendeurs();
            call.enqueue(new Callback<List<Historique>>() {
                @Override
                public void onResponse(Call<List<Historique>> call, Response<List<Historique>> response) {
                    view.hideLoading();
                    if (response.isSuccessful() && response.body() != null) {
                        view.onGetResult(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<Historique>> call, Throwable t) {
                    view.hideLoading();
                    view.onErrorLoading(t.getLocalizedMessage());
                }
            });
        } else if(type == 2) {
            Call<List<Historique>> call = apiInterface.gethistoriquesblocs();
            call.enqueue(new Callback<List<Historique>>() {
                @Override
                public void onResponse(Call<List<Historique>> call, Response<List<Historique>> response) {
                    view.hideLoading();
                    if (response.isSuccessful() && response.body() != null) {
                        view.onGetResult(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<Historique>> call, Throwable t) {
                    view.hideLoading();
                    view.onErrorLoading(t.getLocalizedMessage());
                }
            });
        }

    }

}
