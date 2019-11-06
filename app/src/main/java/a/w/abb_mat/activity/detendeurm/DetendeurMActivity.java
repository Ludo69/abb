package a.w.abb_mat.activity.detendeurm;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.creationdetendeur.CreerDetendeurActivity;
import a.w.abb_mat.activity.detendeurmaintenance.DetendeurMaintenanceActivity;
import a.w.abb_mat.model.Detendeur;
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

public class DetendeurMActivity extends AppCompatActivity implements DetendeurMView {

    private static final int INTENT_ADD = 100;
    private static final int INTENT_EDIT = 200;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    DetendeurMPresenter presenter;
    DetendeurMAdapter adapter;
    DetendeurMAdapter.ItemClickListener itemClickListener;

    List<Detendeur> detendeur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detendeur);

        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new DetendeurMPresenter((DetendeurMView) this);
        presenter.getData();

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getData()
        );

        itemClickListener = ((view, position) -> {
            int iddetendeur = detendeur.get(position).getIddetendeur();
            String txtnumdetendeur = detendeur.get(position).getNumdetendeur();
            String txtcommentairedetendeur = detendeur.get(position).getCommentairedetendeur();
            int dispodetendeur = detendeur.get(position).getDispodetendeur();
            String daterevision = detendeur.get(position).getRevisiondetendeur();
            Intent intent = new Intent(this, DetendeurMaintenanceActivity.class);
            intent.putExtra("iddetendeur", iddetendeur);
            intent.putExtra("numdetendeur", txtnumdetendeur);
            intent.putExtra("commentairedetendeur", txtcommentairedetendeur);
            intent.putExtra("dispodetendeur", dispodetendeur);
            intent.putExtra("daterevision", daterevision);
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
                Intent intent = new Intent(this, CreerDetendeurActivity.class);
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
    public void onGetResult(List<Detendeur> detendeurs) {
        adapter = new DetendeurMAdapter(this, detendeurs, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        detendeur = detendeurs;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

