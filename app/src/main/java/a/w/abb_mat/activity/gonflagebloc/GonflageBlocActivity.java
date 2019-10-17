package a.w.abb_mat.activity.gonflagebloc;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.gestionGonflage.GestionGonflageActivity;
import a.w.abb_mat.model.Bloc;
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

public class GonflageBlocActivity extends AppCompatActivity implements GonflageBlocView {

    private static final int INTENT_ADD = 100;
    private static final int INTENT_EDIT = 200;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    GonflageBlocPresenter presenter;
    GonflageBlocAdapter adapter;
    GonflageBlocAdapter.ItemClickListener itemClickListener;

    List<Bloc> bloc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gonflage_bloc);

        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new GonflageBlocPresenter((GonflageBlocView) this);
        presenter.getData();

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getData()
        );

        itemClickListener = ((view, position) -> {
            int idbloc = bloc.get(position).getIdbloc();
            String numbloc = bloc.get(position).getNumbloc();
            int pressionbloc = bloc.get(position).getPressionbloc();
            String litragebloc = bloc.get(position).getLitragebloc();
            String gonfleur = (String) getIntent().getSerializableExtra("gonfleur");
            Intent intent = new Intent(this, GestionGonflageActivity.class);
            intent.putExtra("idbloc", idbloc);
            intent.putExtra("numbloc", numbloc);
            intent.putExtra("litragebloc", litragebloc);
            intent.putExtra("pressionbloc", pressionbloc);
            intent.putExtra("gonfleur", gonfleur);
            startActivityForResult(intent, INTENT_EDIT);
            finish();

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
    public void onGetResult(List<Bloc> blocs) {
        adapter = new GonflageBlocAdapter(this, blocs, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        bloc = blocs;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

