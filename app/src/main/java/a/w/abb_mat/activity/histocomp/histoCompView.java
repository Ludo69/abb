package a.w.abb_mat.activity.histocomp;

import java.util.List;

import a.w.abb_mat.model.HistoComp;

public interface histoCompView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<HistoComp> histoComps);
    void onErrorLoading(String message);
}
