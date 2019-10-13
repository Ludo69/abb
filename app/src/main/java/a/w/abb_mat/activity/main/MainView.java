package a.w.abb_mat.activity.main;

import java.util.List;

import a.w.abb_mat.model.Note;

public interface MainView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Note> notes);
    void onErrorLoading(String message);
}
