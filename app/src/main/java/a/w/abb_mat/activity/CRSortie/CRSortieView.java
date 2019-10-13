package a.w.abb_mat.activity.CRSortie;

public interface CRSortieView {

    void showProgress();
    void hideProgress();
    void onRequestSuccess(String message);
    void onRequestError(String  message);

}
