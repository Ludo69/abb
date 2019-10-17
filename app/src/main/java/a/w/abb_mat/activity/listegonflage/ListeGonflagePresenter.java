package a.w.abb_mat.activity.listegonflage;

import java.util.List;

import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Gonflage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListeGonflagePresenter {

    private ListeGonflageView view;

    public ListeGonflagePresenter(ListeGonflageView listeGonflageView) { this.view = listeGonflageView; }

    void getData(int type) {

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
            Call<List<Gonflage>> call = apiInterface.getgonflages();
            call.enqueue(new Callback<List<Gonflage>>() {
                @Override
                public void onResponse(Call<List<Gonflage>> call, Response<List<Gonflage>> response) {
                    view.hideLoading();
                    if (response.isSuccessful() && response.body() != null) {
                        view.onGetResult(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<Gonflage>> call, Throwable t) {
                    view.hideLoading();
                    view.onErrorLoading(t.getLocalizedMessage());
                }
            });

    }

}
