package a.w.abb_mat.activity.gonflagebloc;

import java.util.List;

import a.w.abb_mat.model.Bloc;

public interface GonflageBlocView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Bloc> blocs);
    void onErrorLoading(String message);
}
