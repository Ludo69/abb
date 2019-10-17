package a.w.abb_mat.activity.listegonflage;

import java.util.List;

import a.w.abb_mat.model.Gonflage;

public interface ListeGonflageView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Gonflage> gonflages);
    void onErrorLoading(String message);
}
