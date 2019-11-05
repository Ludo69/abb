package a.w.abb_mat.activity.blocm;

import java.util.List;

import a.w.abb_mat.model.Bloc;

public interface BlocMView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Bloc> blocs);
    void onErrorLoading(String message);
}
