package a.w.abb_mat.activity.sortie;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.CRSortie.CRSortieActivity;
import a.w.abb_mat.model.Sortie;
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

public class SortieActivity extends AppCompatActivity implements SortieView{

    private static final int INTENT_ADD = 100;
    private static final int INTENT_EDIT = 200;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    SortiePresenter presenter;
    SortieAdapter adapter;
    SortieAdapter.ItemClickListener itemClickListener;

    List<Sortie> sortie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sortie);

        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab=findViewById(R.id.add);
        fab.setOnClickListener(view ->
                startActivityForResult(
                        new Intent(this, CRSortieActivity.class),
                        INTENT_ADD)
        );

        presenter = new SortiePresenter((SortieView) this);
        presenter.getData();

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getData()
        );

        itemClickListener = ((view, position) -> {
            int idsortie = sortie.get(position).getIdsortie();
            String nomsortie = sortie.get(position).getNomsortie();
            String datesortie = sortie.get(position).getDatesortie();

            Intent intent = new Intent(this, CRSortieActivity.class);
            intent.putExtra("id", idsortie);
            intent.putExtra("nomsortie", nomsortie);
            intent.putExtra("datesortie", datesortie);
            startActivityForResult(intent, INTENT_EDIT);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == INTENT_ADD && resultCode == RESULT_OK){
            presenter.getData();
        } else if(requestCode == INTENT_EDIT && resultCode == RESULT_OK){
            presenter.getData();
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
    public void onGetResult(List<Sortie> sorties) {
        adapter = new SortieAdapter(this, sorties, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        sortie = sorties;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
