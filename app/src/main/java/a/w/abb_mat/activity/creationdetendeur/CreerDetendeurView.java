package a.w.abb_mat.activity.creationdetendeur;

public interface CreerDetendeurView {
    void showProgress();
    void hideProgress();
    void onAddSuccess(String message);
    void onAddError(String message);
}
