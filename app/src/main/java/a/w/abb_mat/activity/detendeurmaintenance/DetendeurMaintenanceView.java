package a.w.abb_mat.activity.detendeurmaintenance;

public interface DetendeurMaintenanceView {
    void showProgress();
    void hideProgress();
    void onAddSuccess(String message);
    void onAddError(String message);
}
