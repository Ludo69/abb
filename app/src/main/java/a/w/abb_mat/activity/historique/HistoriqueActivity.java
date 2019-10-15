package a.w.abb_mat.activity.historique;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.historique.HistoriqueAdapter;
import a.w.abb_mat.activity.historique.HistoriquePresenter;
import a.w.abb_mat.activity.historique.HistoriqueView;
import a.w.abb_mat.model.Historique;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HistoriqueActivity extends AppCompatActivity implements HistoriqueView {

    private static final int INTENT_ADD = 100;
    private static final int INTENT_EDIT = 200;
    public static int type;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    HistoriquePresenter presenter;
    HistoriqueAdapter adapter;
    HistoriqueAdapter.ItemClickListener itemClickListener;

    List<Historique> historique;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);
        type = getIntent().getIntExtra("type", 0);
        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new HistoriquePresenter((HistoriqueView) this);
        presenter.getData(type);

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getData(type)
        );

        itemClickListener = ((view, position) -> {
            int idhistorique = historique.get(position).getIdhistorique();

        });

    }

        @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == INTENT_ADD && resultCode == RESULT_OK){
            presenter.getData(type);
        } else if(requestCode == INTENT_EDIT && resultCode == RESULT_OK){
            presenter.getData(type);
        }

    }

    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onGetResult(List<Historique> historiques) {
        adapter = new HistoriqueAdapter(this, historiques, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        historique = historiques;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}



