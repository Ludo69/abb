package a.w.abb_mat.activity.gonflagebloc;

import java.util.List;
import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Bloc;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GonflageBlocPresenter {

    private GonflageBlocView view;

    public GonflageBlocPresenter(GonflageBlocView gonflageBlocView) { this.view = gonflageBlocView; }

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

}
