package a.w.abb_mat.activity.bloc;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.membre.MembreActivity;
import a.w.abb_mat.activity.bloc.BlocAdapter;
import a.w.abb_mat.activity.bloc.BlocPresenter;
import a.w.abb_mat.activity.bloc.BlocView;
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

public class BlocActivity extends AppCompatActivity implements BlocView {

    private static final int INTENT_ADD = 100;
    private static final int INTENT_EDIT = 200;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    BlocPresenter presenter;
    BlocAdapter adapter;
    BlocAdapter.ItemClickListener itemClickListener;

    List<Bloc> bloc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloc);

        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new BlocPresenter((BlocView) this);
        presenter.getData();

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getData()
        );

        itemClickListener = ((view, position) -> {
            int idbloc = bloc.get(position).getIdbloc();
            String txtnumbloc = bloc.get(position).getNumbloc();
            String txtcommentairebloc = bloc.get(position).getCommentairebloc();
            String codeunique = bloc.get(position).getCodeuniquebloc();
            Toast.makeText(this, codeunique, Toast.LENGTH_SHORT).show();
            String pressionbloc = bloc.get(position).getPressionbloc();
            String daterestitution = "14102019";
            int dispobloc = bloc.get(position).getDispobloc();

            if(dispobloc == 1) {
                Intent intent = new Intent(this, MembreActivity.class);
                intent.putExtra("typemat", 2);
                intent.putExtra("idmat", idbloc);
                intent.putExtra("nummat", txtnumbloc);
                intent.putExtra("commentairestab", txtcommentairebloc);
                startActivityForResult(intent, INTENT_EDIT);
                finish();
            } else {
                ShowAlertDialog(this, idbloc, codeunique, daterestitution);

            }
        });

    }

    public void ShowAlertDialog(BlocActivity v, int idbloc, String codeunique, String daterestitution){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Restitution");
        alert.setMessage("Confirmez-vous la restitution ?");
        alert.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.updateBlocRestitution(idbloc);
                presenter.restitution(codeunique, daterestitution);
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                Toast.makeText(a.w.abb_mat.activity.bloc.BlocActivity.this, "Restitution Confirmée !", Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(a.w.abb_mat.activity.bloc.BlocActivity.this, "Restitution annulée !", Toast.LENGTH_SHORT).show();
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
    public void onGetResult(List<Bloc> blocs) {
        adapter = new BlocAdapter(this, blocs, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        bloc = blocs;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
