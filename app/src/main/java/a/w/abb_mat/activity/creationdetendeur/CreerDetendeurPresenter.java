package a.w.abb_mat.activity.creationdetendeur;

import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Bloc;
import a.w.abb_mat.model.Stab;
import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class CreerDetendeurPresenter {

    private  CreerDetendeurView view;

    public CreerDetendeurPresenter(CreerDetendeurView view) {
        this.view = view;
    }

    void insertdetendeur(String numDetendeur, String commentaireDetendeur, int dispoDetendeur) {

        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Stab> call = apiInterface.insertdetendeur(numDetendeur, commentaireDetendeur, dispoDetendeur);
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


