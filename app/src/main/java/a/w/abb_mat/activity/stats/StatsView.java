package a.w.abb_mat.activity.stats;

import java.util.List;
import a.w.abb_mat.model.Stat;

public interface StatsView {

    void showProgress();
    void hideProgress();
    void onSuccess(String message);
    void onAddSuccess(List<Stat> stats);
    void onAddError(String message);
}
