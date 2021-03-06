package a.w.abb_mat.activity.stabm;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.creationstab.CreerStabActivity;
import a.w.abb_mat.activity.stabmaintenance.StabMaintenanceActivity;
import a.w.abb_mat.model.Stab;
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

public class StabMActivity extends AppCompatActivity implements StabMView {

    private static final int INTENT_ADD = 100;
    private static final int INTENT_EDIT = 200;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    StabMPresenter presenter;
    StabMAdapter adapter;
    StabMAdapter.ItemClickListener itemClickListener;

    List<Stab> stab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stab);

        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new StabMPresenter((StabMView) this);
        presenter.getData();

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getData()
        );

        itemClickListener = ((view, position) -> {
            int idstab = stab.get(position).getIdstab();
            String txtnumstab = stab.get(position).getNumstab();
            String txtcommentairestab = stab.get(position).getCommentairestab();
            String codeunique = stab.get(position).getCodeuniquestab();
            int dispostab = stab.get(position).getDispostab();
            Intent intent = new Intent(this, StabMaintenanceActivity.class);
            intent.putExtra("idstab", idstab);
            intent.putExtra("numstab", txtnumstab);
            intent.putExtra("commentairestab", txtcommentairestab);
            intent.putExtra("dispostab", dispostab);
            intent.putExtra("codeunique", codeunique);
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
                Intent intent = new Intent(this, CreerStabActivity.class);
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
    public void onGetResult(List<Stab> stabs) {
        adapter = new StabMAdapter(this, stabs, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        stab = stabs;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

