package a.w.abb_mat.activity.editor;

import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Stab;
import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorPresenter {

    private EditorView view;

    public EditorPresenter(EditorView view) {
        this.view = view;
    }

    void saveStab(final String title, final String note, final int color) {
        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        retrofit2.Call<Stab> call = apiInterface.saveStab(title, note, color);

        call.enqueue(new Callback<Stab>() {
            @Override
            public void onResponse(@NonNull Call<Stab> call, @NonNull Response<Stab> response) {
                view.hideProgress();

                if(response.isSuccessful() && response.body() != null){

                    Boolean success = response.body().getSuccess();
                    if(success) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }


                }

            }

            public void onFailure(@NonNull Call<Stab> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    void updateStab(int id, String title, String note, int color) {

        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Stab> call = apiInterface.updateStab(id, title, note, color);
        call.enqueue(new Callback<Stab>() {
            @Override
            public void onResponse(@NonNull Call<Stab> call,@NonNull Response<Stab> response) {
                view.hideProgress();
                if(response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if(success){
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Stab> call,@NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });

    }

    void deleteStab(int id) {
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Stab> call = apiInterface.deleteStab(id);
        call.enqueue(new Callback<Stab>() {
            @Override
            public void onResponse(@NonNull Call<Stab> call,@NonNull Response<Stab> response) {
                view.hideProgress();
                if(response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if(success){
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Stab> call,@NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

}
