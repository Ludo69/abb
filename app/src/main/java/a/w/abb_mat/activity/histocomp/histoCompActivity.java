package a.w.abb_mat.activity.histocomp;

import a.w.abb_mat.R;
import a.w.abb_mat.model.HistoComp;
import a.w.abb_mat.model.Historique;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class histoCompActivity extends AppCompatActivity implements histoCompView {

    private static final int INTENT_ADD = 100;
    private static final int INTENT_EDIT = 200;
    public static int type;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    histoCompPresenter presenter;
    histoCompAdapter adapter;
    histoCompAdapter.ItemClickListener itemClickListener;

    List<HistoComp> histoComp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histo_comp);
        type = getIntent().getIntExtra("type", 0);
        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new histoCompPresenter((histoCompView) this);
        presenter.getData(type);

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getData(type)
        );

        itemClickListener = ((view, position) -> {
            int id = histoComp.get(position).getId();

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
    public void onGetResult(List<HistoComp> histoComps) {
        adapter = new histoCompAdapter(this, histoComps, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        histoComp = histoComps;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}



