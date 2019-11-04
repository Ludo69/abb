package a.w.abb_mat.activity.stabmaintenance;

public interface StabMaintenanceView {
    void showProgress();
    void hideProgress();
    void onAddSuccess(String message);
    void onAddError(String message);
}
