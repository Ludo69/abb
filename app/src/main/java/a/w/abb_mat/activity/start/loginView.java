package a.w.abb_mat.activity.start;

import java.util.List;

import a.w.abb_mat.model.Password;

public interface loginView {
    void showProgress();
    void hideProgress();
    void onSuccess(String pass);
    void onAddSuccess(List<Password> passwords);
    void onAddError(String message);
}
