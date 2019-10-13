package a.w.abb_mat.activity.membre;

import java.util.List;

import a.w.abb_mat.model.Membre;

public interface MembreView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Membre> membres);
    void onErrorLoading(String message);
}
