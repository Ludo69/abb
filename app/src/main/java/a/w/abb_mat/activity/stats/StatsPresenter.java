package a.w.abb_mat.activity.stats;


import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonParser;

import java.util.List;

import a.w.abb_mat.activity.stab.StabView;
import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import androidx.annotation.NonNull;

import a.w.abb_mat.model.Stat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class StatsPresenter {

    private  StatsView view;

    public StatsPresenter(StatsView view) {
        this.view = view;
    }

    private List<Stat> Stats;

    void recupstats() {

        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Stat>> call = apiInterface.GetNbr();
        call.enqueue(new Callback<List<Stat>>() {
            @Override
            public void onResponse(Call<List<Stat>> call, Response<List<Stat>> response) {
                Log.e(TAG, "onResponse" + response.body());
            }

            @Override
            public void onFailure(Call<List<Stat>> call, Throwable t) {
                Log.e(TAG, "onResponse" + t.getLocalizedMessage());
            }
        });

    }


}

