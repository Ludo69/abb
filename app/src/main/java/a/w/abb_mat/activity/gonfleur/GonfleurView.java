package a.w.abb_mat.activity.gonfleur;

import java.util.List;

import a.w.abb_mat.model.Gonfleur;

public interface GonfleurView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Gonfleur> gonfleurs);
    void onErrorLoading(String message);
}
