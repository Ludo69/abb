package a.w.abb_mat.activity.sortie;

import java.util.List;

import a.w.abb_mat.model.Sortie;

public interface SortieView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Sortie> sorties);
    void onErrorLoading(String message);
}
