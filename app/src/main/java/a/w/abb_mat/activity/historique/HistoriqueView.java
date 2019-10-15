package a.w.abb_mat.activity.historique;

import java.util.List;

import a.w.abb_mat.model.Historique;

public interface HistoriqueView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Historique> historiques);
    void onErrorLoading(String message);
}
