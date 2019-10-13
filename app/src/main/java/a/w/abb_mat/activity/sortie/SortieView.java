package a.w.abb_mat.activity.sortie;

import java.util.List;

import a.w.abb_mat.model.Sortie;

public interface SortieView {
    void showLoading2();
    void hideLoading2();
    void onGetResult2(List<Sortie> sorties);
    void onErrorLoading2(String message);
}
