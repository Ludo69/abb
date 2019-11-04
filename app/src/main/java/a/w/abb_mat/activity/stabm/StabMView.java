package a.w.abb_mat.activity.stabm;

import java.util.List;

import a.w.abb_mat.model.Stab;

public interface StabMView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Stab> stabs);
    void onErrorLoading(String message);
}
