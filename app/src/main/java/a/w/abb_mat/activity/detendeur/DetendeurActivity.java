package a.w.abb_mat.activity.detendeur;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.membre.MembreActivity;
import a.w.abb_mat.model.Detendeur;
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

public class DetendeurActivity extends AppCompatActivity implements DetendeurView {

    private static final int INTENT_ADD = 100;
    private static final int INTENT_EDIT = 200;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    DetendeurPresenter presenter;
    DetendeurAdapter adapter;
    DetendeurAdapter.ItemClickListener itemClickListener;

    List<Detendeur> detendeur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detendeur);

        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new DetendeurPresenter((DetendeurView) this);
        presenter.getData();

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getData()
        );

        itemClickListener = ((view, position) -> {
            int iddetendeur = detendeur.get(position).getIddetendeur();
            String txtnumdetendeur = detendeur.get(position).getNumdetendeur();
            String txtcommentairedetendeur = detendeur.get(position).getCommentairedetendeur();
            String codeunique = detendeur.get(position).getCodeuniquedetendeur();
            String daterestitution = "14102019";
            int dispodetendeur = detendeur.get(position).getDispodetendeur();

            if(dispodetendeur == 1) {
                Intent intent = new Intent(this, MembreActivity.class);
                intent.putExtra("typemat", 1);
                intent.putExtra("idmat", iddetendeur);
                intent.putExtra("nummat", txtnumdetendeur);
                intent.putExtra("commentairedetendeur", txtcommentairedetendeur);
                startActivityForResult(intent, INTENT_EDIT);
                finish();
            } else {
                ShowAlertDialog(this, iddetendeur, codeunique, daterestitution);

            }
        });

    }

    public void ShowAlertDialog(DetendeurActivity v, int iddetendeur, String codeunique, String daterestitution){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Restitution");
        alert.setMessage("Confirmez-vous la restitution ?");
        alert.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.updateDetendeurRestitution(iddetendeur);
                presenter.restitution(codeunique, daterestitution);
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                Toast.makeText(a.w.abb_mat.activity.detendeur.DetendeurActivity.this, "Restitution Confirmée !", Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(a.w.abb_mat.activity.detendeur.DetendeurActivity.this, "Restitution annulée !", Toast.LENGTH_SHORT).show();
            }
        });
        alert.create().show();
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
        adapter = new DetendeurAdapter(this, detendeurs, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        detendeur = detendeurs;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
