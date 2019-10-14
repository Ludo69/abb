package a.w.abb_mat.activity.bloc;

import java.util.List;

import a.w.abb_mat.model.Bloc;

public interface BlocView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Bloc> blocs);
    void onErrorLoading(String message);
}
