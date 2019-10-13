package a.w.abb_mat.activity.emprunts;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.choixEmprunt.choixEmpruntActivity;
import a.w.abb_mat.model.Emprunt;
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

public class EmpruntActivity extends AppCompatActivity implements EmpruntView {

    private static final int INTENT_ADD = 100;
    private static final int INTENT_EDIT = 200;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    EmpruntPresenter presenter;
    EmpruntAdapter adapter;
    EmpruntAdapter.ItemClickListener itemClickListener;

    List<Emprunt> emprunt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emprunt);

        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab=findViewById(R.id.add);
        fab.setOnClickListener(view ->
                startActivityForResult(
                        new Intent(this, choixEmpruntActivity.class),
                        INTENT_ADD)
        );

        presenter = new EmpruntPresenter((EmpruntView) this);
        presenter.getData();

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getData()
        );

        itemClickListener = ((view, position) -> {
            int idemprunt = emprunt.get(position).getIdemprunt();
            String nomsortieemprunt = emprunt.get(position).getNomsortieemprunt();
            String typeemprunt = emprunt.get(position).getTypeemprunt();
            String numtype = emprunt.get(position).getNumtype();
            String emprunteur = emprunt.get(position).getEmprunteur();
            String dateemprunt = emprunt.get(position).getDateemprunt();

            Intent intent = new Intent(this, choixEmpruntActivity.class);
            intent.putExtra("idemprunt", idemprunt);
            intent.putExtra("nomsortieemprunt", nomsortieemprunt);
            intent.putExtra("typeemprunt", typeemprunt);
            intent.putExtra("numtype", numtype);
            intent.putExtra("emprunteur", emprunteur);
            intent.putExtra("dateemprunt", dateemprunt);
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
    public void onGetResult(List<Emprunt> emprunts) {
        adapter = new EmpruntAdapter(this, emprunts, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        emprunt = emprunts;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
