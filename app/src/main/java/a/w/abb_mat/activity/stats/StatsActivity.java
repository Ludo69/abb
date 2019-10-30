package a.w.abb_mat.activity.stats;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.pressionbloc.PressionBlocActivity;
import a.w.abb_mat.activity.stab.StabActivity;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Stat;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class StatsActivity extends AppCompatActivity implements StatsView{

    private static final int INTENT_EDIT = 200;
    TextView et_nbrgonflage, et_dureegonflage;

    StatsPresenter presenter;

    ProgressDialog progressDialog;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        et_nbrgonflage = findViewById(R.id.nbrgonflage);
        et_dureegonflage = findViewById(R.id.dureegonflage);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Patientez svp...");

        presenter = new StatsPresenter(this);
        presenter.recupstats();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_gestionpressionbloc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.valider:
                //Intent intent = new Intent(this, PressionBlocActivity.class);
                //startActivityForResult(intent, INTENT_EDIT);
                //finish();
                return true;
            default:

                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void showProgress() {
        // progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void onSuccess(String nbrgonflage, String dureegonflage) {
        et_nbrgonflage.setText(nbrgonflage);
        int duree = 0;
        try {
            duree = Integer.parseInt(dureegonflage);
        } catch(NumberFormatException nfe) {
            // Handle parse error.
        }
        String dureeTotale = formatHoursAndMinutes(duree);
        et_dureegonflage.setText(dureeTotale);
    }

    @Override
    public void onAddSuccess(List<Stat> stats) {
        //Toast.makeText(StatsActivity.this, stats, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onAddError(String message) {
        Toast.makeText(StatsActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    public static String formatHoursAndMinutes(int totalMinutes) {
        String minutes = Integer.toString(totalMinutes % 60);
        minutes = minutes.length() == 1 ? "0" + minutes : minutes;
        return (totalMinutes / 60) + "h" + minutes + " min";
    }


}

