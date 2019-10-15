package a.w.abb_mat.activity.gestionPression;

public interface GestionPressionView {

    void showProgress();
    void hideProgress();
    void onAddSuccess(String message);
    void onAddError(String message);
}
