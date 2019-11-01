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
        Call<Stat> call = apiInterface.GetNbr();
        call.enqueue(new Callback<Stat>() {
            @Override
            public void onResponse(Call<Stat> call, Response<Stat> response) {
                    view.onSuccess(response.body().getNbrgonflage(), response.body().getTpsgonflage(), response.body().getTpsmajoree(), response.body().getMoytemp());
                    Log.d("retour OK : ", response.body().getNbrgonflage());
            }

            @Override
            public void onFailure(Call<Stat> call, Throwable t) {
                view.onAddError(t.getLocalizedMessage());
                Log.d("retour KO : ", "ERREUR");
            }
        });

    }

    void mailgonflage() {

        view.showProgress();

        String email = "ludo36@orange.fr";

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Stat> call = apiInterface.GetMail(email);
        call.enqueue(new Callback<Stat>() {
            @Override
            public void onResponse(Call<Stat> call, Response<Stat> response) {
                view.onMail();
                Log.d("retour MAIL OK : ", " OK");
            }

            @Override
            public void onFailure(Call<Stat> call, Throwable t) {
                view.onAddError(t.getLocalizedMessage());
                Log.d("retour MAIL KO : ", " KO");
            }
        });

    }


}

