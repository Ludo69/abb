package a.w.abb_mat.activity.stats;


import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import a.w.abb_mat.activity.stab.StabView;
import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import androidx.annotation.NonNull;

import a.w.abb_mat.model.Stat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatsPresenter {

    private  StatsView view;

    public StatsPresenter(StatsView view) {
        this.view = view;
    }


    void recupstats() {

        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Stat> call = apiInterface.GetCount();
        call.enqueue(new Callback<Stat>() {
            @Override
            public void onResponse(Call<Stat> call, Response<Stat> response) {
                view.hideProgress();
                if(response.isSuccessful() && response.body() != null) {
                   // view.onAddSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<Stat> call, Throwable t) {
                view.hideProgress();
                view.onAddError(t.getLocalizedMessage());
            }
        });

    }


}

