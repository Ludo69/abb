package a.w.abb_mat.activity.start;

import android.util.Log;
import java.util.List;
import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Password;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginPresenter {

    private  loginView view;

    public loginPresenter(loginView view) {
        this.view = view;
    }

    private List<Password> passwords;

    void recuppass() {

        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Password> call = apiInterface.getPass();
        call.enqueue(new Callback<Password>() {
            @Override
            public void onResponse(Call<Password> call, Response<Password> response) {
                view.onSuccess(response.body().getPass());
                //Log.d("retour OK : ", response.body().getPass());
            }

            @Override
            public void onFailure(Call<Password> call, Throwable t) {
                view.onAddError(t.getLocalizedMessage());
                //Log.d("retour KO : ", "ERREUR");
            }
        });

    }
}


