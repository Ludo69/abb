package a.w.abb_mat.activity.blocm;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.blocmaintenance.BlocMaintenanceActivity;
import a.w.abb_mat.activity.creationbloc.CreerBlocActivity;
import a.w.abb_mat.model.Bloc;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class BlocMActivity extends AppCompatActivity implements BlocMView {

    private static final int INTENT_ADD = 100;
    private static final int INTENT_EDIT = 200;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    BlocMPresenter presenter;
    BlocMAdapter adapter;
    BlocMAdapter.ItemClickListener itemClickListener;

    List<Bloc> bloc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloc);

        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new BlocMPresenter((BlocMView) this);
        presenter.getData();

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getData()
        );

        itemClickListener = ((view, position) -> {
            int idbloc = bloc.get(position).getIdbloc();
            String txtnumbloc = bloc.get(position).getNumbloc();
            String txtcommentairebloc = bloc.get(position).getCommentairebloc();
            int dispobloc = bloc.get(position).getDispobloc();
            Intent intent = new Intent(this, BlocMaintenanceActivity.class);
            intent.putExtra("idbloc", idbloc);
            intent.putExtra("numbloc", txtnumbloc);
            intent.putExtra("commentairebloc", txtcommentairebloc);
            intent.putExtra("dispobloc", dispobloc);
            startActivityForResult(intent, INTENT_EDIT);
            finish();
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ajouter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ajouter:
                Intent intent = new Intent(this, CreerBlocActivity.class);
                startActivityForResult(intent, INTENT_EDIT);
                finish();
                return true;
            default:

                return super.onOptionsItemSelected(item);

        }
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
        adapter = new BlocMAdapter(this, blocs, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        bloc = blocs;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
