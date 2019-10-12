package a.w.abb_mat.activity.main;

import java.util.List;

import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Stab;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {

    private MainView view;

    public MainPresenter(MainView mainView) {
        this.view = mainView;
    }

    void getData() {

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Stab>> call = apiInterface.getStabs();
        call.enqueue(new Callback<List<Stab>>() {
            @Override
            public void onResponse(Call<List<Stab>> call, Response<List<Stab>> response) {
                view.hideLoading();
                if(response.isSuccessful() && response.body() != null) {
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Stab>> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }

}
