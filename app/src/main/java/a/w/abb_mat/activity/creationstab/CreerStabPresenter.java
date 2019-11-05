package a.w.abb_mat.activity.creationstab;

import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Bloc;
import a.w.abb_mat.model.Stab;
import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class CreerStabPresenter {

    private  CreerStabView view;

    public CreerStabPresenter(CreerStabView view) {
        this.view = view;
    }

    void insertstab(String numstab, String taillstab, String commentairestab, int dispostab) {

        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Stab> call = apiInterface.insertstab(numstab, taillstab, commentairestab, dispostab);
        call.enqueue(new Callback<Stab>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<Stab> call, @NonNull Response<Stab> response) {
                view.hideProgress();
                if(response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if(success){
                        view.onAddSuccess(response.body().getMessage());
                    } else {
                        view.onAddError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull retrofit2.Call<Stab> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onAddError(t.getLocalizedMessage());
            }
        });

    }

}

