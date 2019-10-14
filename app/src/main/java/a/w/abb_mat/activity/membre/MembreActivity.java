package a.w.abb_mat.activity.membre;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.detendeur.DetendeurActivity;
import a.w.abb_mat.activity.stab.StabActivity;
import a.w.abb_mat.model.Membre;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.UUID;

public class MembreActivity extends AppCompatActivity implements MembreView {

    private static final int INTENT_ADD = 100;
    private static final int INTENT_EDIT = 200;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    MembrePresenter presenter;
    MembreAdapter adapter;
    MembreAdapter.ItemClickListener itemClickListener;

    List<Membre> membre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membre);

        //Récupération variables
        Intent intentrécup = getIntent();
        if (intentrécup != null){
            String nummat = "";
            if (intentrécup.hasExtra("nummat")){
                nummat = intentrécup.getStringExtra("nummat");
            }
            Toast.makeText(this, nummat, Toast.LENGTH_SHORT).show();
        }

        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new MembrePresenter(this);
        presenter.getData();

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getData()
        );

        itemClickListener = ((view, position) -> {
            String nommembre = membre.get(position).getNommembres();
            int typemat = (int) getIntent().getSerializableExtra("typemat");
            int idmat = (int)   getIntent().getSerializableExtra("idmat");
            String nummat = (String) getIntent().getSerializableExtra("nummat");
            String codeunique = UUID.randomUUID().toString();

            if(typemat == 0) {
                Log.d("*******stab", String.valueOf(typemat));
                presenter.updateStab(idmat, nommembre, codeunique);
                //Historisation
                presenter.inserthistorique("Stab", nummat, "14102019", "0", nommembre, codeunique);
                Toast.makeText(this, "Stab n° : " + nummat + " attribué à : " + nommembre, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, StabActivity.class);
                //intent.putExtra("nommembre", nommembre);
                startActivityForResult(intent, INTENT_EDIT);
                finish();
            } else if(typemat == 1) {
                Log.d("*******deten", String.valueOf(typemat));
                presenter.updateDetendeur(idmat, nommembre, codeunique);
                //Historisation
                presenter.inserthistorique("Detendeur", nummat, "14102019", "0", nommembre, codeunique);
                Toast.makeText(this, "Detendeur n° : " + nummat + " attribué à : " + nommembre, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, DetendeurActivity.class);
                startActivityForResult(intent, INTENT_EDIT);
                finish();
            } else {
                Log.d("*******rien", String.valueOf(typemat));
            }

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
    public void onGetResult(List<Membre> membres) {
        adapter = new MembreAdapter(this, membres, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        membre = membres;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

