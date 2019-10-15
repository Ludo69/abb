package a.w.abb_mat.activity.pressionbloc;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.gestionPression.GestionPressionActivity;
import a.w.abb_mat.activity.membre.MembreActivity;
import a.w.abb_mat.activity.pressionbloc.PressionBlocAdapter;
import a.w.abb_mat.activity.pressionbloc.PressionBlocPresenter;
import a.w.abb_mat.activity.pressionbloc.PressionBlocView;
import a.w.abb_mat.model.Bloc;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PressionBlocActivity extends AppCompatActivity implements PressionBlocView {

    private static final int INTENT_ADD = 100;
    private static final int INTENT_EDIT = 200;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    PressionBlocPresenter presenter;
    PressionBlocAdapter adapter;
    PressionBlocAdapter.ItemClickListener itemClickListener;

    List<Bloc> bloc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pression_bloc);

        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new PressionBlocPresenter((PressionBlocView) this);
        presenter.getData();

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getData()
        );

        itemClickListener = ((view, position) -> {
            int idbloc = bloc.get(position).getIdbloc();
            String numbloc = bloc.get(position).getNumbloc();
            int pressionbloc = bloc.get(position).getPressionbloc();
            Intent intent = new Intent(this, GestionPressionActivity.class);
            intent.putExtra("idbloc", idbloc);
            intent.putExtra("numbloc", numbloc);
            intent.putExtra("pressionbloc", pressionbloc);
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
        adapter = new PressionBlocAdapter(this, blocs, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        bloc = blocs;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
