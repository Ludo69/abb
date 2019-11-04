package a.w.abb_mat.activity.detendeurm;

import java.util.List;

import a.w.abb_mat.model.Detendeur;

public interface DetendeurMView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Detendeur> detendeurs);
    void onErrorLoading(String message);
}
