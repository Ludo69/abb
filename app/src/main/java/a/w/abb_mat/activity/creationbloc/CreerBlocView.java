package a.w.abb_mat.activity.creationbloc;

public interface CreerBlocView {
    void showProgress();
    void hideProgress();
    void onAddSuccess(String message);
    void onAddError(String message);
}
