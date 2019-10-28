package a.w.abb_mat.activity.listegonflage;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.gestionGonflage.GestionGonflageActivity;
import a.w.abb_mat.model.Gonflage;
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

public class ListeGonflageActivity extends AppCompatActivity implements ListeGonflageView {

    private static final int INTENT_ADD = 100;
    private static final int INTENT_EDIT = 200;
    public static int type;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    ListeGonflagePresenter presenter;
    ListeGonflageAdapter adapter;
    ListeGonflageAdapter.ItemClickListener itemClickListener;

    List<Gonflage> gonflage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_gonflage);
        type = getIntent().getIntExtra("type", 0);
        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new ListeGonflagePresenter((ListeGonflageView) this);
        presenter.getData(type);

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getData(type)
        );

        itemClickListener = ((view, position) -> {
           // int idhistorique = gonflages.get(position).getIdhistorique();
            Toast.makeText(this, "Ouverture pour modification", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, GestionGonflageActivity.class);
            intent.putExtra("type", 1);
            int id = gonflage.get(position).getId();
            String date = gonflage.get(position).getDate();
            String gonfleur = gonflage.get(position).getGonfleur();
            int numbloc = gonflage.get(position).getNumbloc();
            int nbrbloc = gonflage.get(position).getNbrblocbranche();
            float compteur = gonflage.get(position).getFincompteur();
            int temperature = gonflage.get(position).getTemperature();
            int pression = gonflage.get(position).getPressionfinale();
            intent.putExtra("id", id);
            intent.putExtra("date", date);
            intent.putExtra("gonfleur", gonfleur);
            intent.putExtra("numbloc", numbloc);
            intent.putExtra("nbrbloc", nbrbloc);
            intent.putExtra("compteur", compteur);
            intent.putExtra("temperature", temperature);
            intent.putExtra("pression", pression);
            startActivityForResult(intent, INTENT_EDIT);
            finish();

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
    public void onGetResult(List<Gonflage> gonflages) {
        adapter = new ListeGonflageAdapter(this, gonflages, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        gonflage = gonflages;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
