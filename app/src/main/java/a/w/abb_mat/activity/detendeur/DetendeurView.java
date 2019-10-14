package a.w.abb_mat.activity.detendeur;

import java.util.List;

import a.w.abb_mat.model.Detendeur;

public interface DetendeurView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Detendeur> detendeurs);
    void onErrorLoading(String message);
}
