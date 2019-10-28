package a.w.abb_mat.activity.stats;


import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.widget.Toast;

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
        Call call = apiInterface.GetCount();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                view.hideProgress();
                Log.d("TEST","TEST");                //if(response.body() != null) {
                    Stat stat = (Stat) response.body();
                    Log.d("RETOUR COUNT : ", String.valueOf(stat));
                //}
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                view.hideProgress();
                view.onAddError(t.getLocalizedMessage());
            }
        });

    }


}

