package a.w.abb_mat.activity.gestionGonflage;

public interface GestionGonflageView {
    void showProgress();
    void hideProgress();
    void onAddSuccess(String message);
    void onAddError(String message);
}
