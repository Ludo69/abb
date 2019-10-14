package a.w.abb_mat.activity.pressionbloc;

import java.util.List;

import a.w.abb_mat.model.Bloc;

public interface PressionBlocView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Bloc> blocs);
    void onErrorLoading(String message);
}
