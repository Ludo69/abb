package a.w.abb_mat.activity.creationstab;

public interface CreerStabView {
    void showProgress();
    void hideProgress();
    void onAddSuccess(String message);
    void onAddError(String message);
}
