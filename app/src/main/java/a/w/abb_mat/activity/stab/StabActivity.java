package a.w.abb_mat.activity.stab;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.membre.MembreActivity;
import a.w.abb_mat.model.Stab;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class StabActivity extends AppCompatActivity implements StabView {

    private static final int INTENT_ADD = 100;
    private static final int INTENT_EDIT = 200;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    StabPresenter presenter;
    StabAdapter adapter;
    StabAdapter.ItemClickListener itemClickListener;

    List<Stab> stab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stab);

        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new StabPresenter((StabView) this);
        presenter.getData();

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getData()
        );

        itemClickListener = ((view, position) -> {
            int idstab = stab.get(position).getIdstab();
            String txtnumstab = stab.get(position).getNumstab();
            String txtcommentairestab = stab.get(position).getCommentairestab();
            int dispostab = stab.get(position).getDispostab();

            if(dispostab == 1) {
                Intent intent = new Intent(this, MembreActivity.class);
                intent.putExtra("idstab", idstab);
                intent.putExtra("numstab", txtnumstab);
                intent.putExtra("commentairestab", txtcommentairestab);
                startActivityForResult(intent, INTENT_EDIT);
                finish();
            } else {
                ShowAlertDialog(this, idstab);

            }
        });

    }

    public void ShowAlertDialog(StabActivity v, int idstab){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Restitution");
            alert.setMessage("Confirmez-vous la restitution ?");
            alert.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    presenter.updateStabRestitution(idstab);
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                    Toast.makeText(StabActivity.this, "Restitution Confirmée !", Toast.LENGTH_SHORT).show();
                }
            });
            alert.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(StabActivity.this, "Restitution annulée !", Toast.LENGTH_SHORT).show();
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
    public void onGetResult(List<Stab> stabs) {
        adapter = new StabAdapter(this, stabs, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        stab = stabs;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
