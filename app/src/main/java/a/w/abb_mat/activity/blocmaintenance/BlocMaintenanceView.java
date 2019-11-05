package a.w.abb_mat.activity.blocmaintenance;

public interface BlocMaintenanceView {
    void showProgress();
    void hideProgress();
    void onAddSuccess(String message);
    void onAddError(String message);
}
