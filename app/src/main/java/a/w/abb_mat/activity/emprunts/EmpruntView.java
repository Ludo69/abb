package a.w.abb_mat.activity.emprunts;

import java.util.List;

import a.w.abb_mat.model.Emprunt;

public interface EmpruntView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Emprunt> sorties);
    void onErrorLoading(String message);
}
