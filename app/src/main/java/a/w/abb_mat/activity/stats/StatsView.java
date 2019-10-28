package a.w.abb_mat.activity.stats;

public interface StatsView {

    void showProgress();
    void hideProgress();
    void onAddSuccess(String message);
    void onAddError(String message);
}
