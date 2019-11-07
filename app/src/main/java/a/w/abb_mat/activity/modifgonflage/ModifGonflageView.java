package a.w.abb_mat.activity.modifgonflage;

public interface ModifGonflageView {
    void showProgress();
    void hideProgress();
    void onAddSuccess(String message);
    void onAddError(String message);
}
