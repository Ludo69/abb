package a.w.abb_mat.activity.stats;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.dialog.Dialog;
import a.w.abb_mat.model.Stat;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class StatsActivity extends AppCompatActivity implements StatsView{

    private static final int INTENT_EDIT = 200;
    TextView et_nbrgonflage, et_tpsgonflage, et_tpsmajoree, et_tempmoy ;

    StatsPresenter presenter;

    ProgressDialog progressDialog;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        et_nbrgonflage = findViewById(R.id.nbrgonflage);
        et_tpsgonflage = findViewById(R.id.tpsgonflage);
        et_tpsmajoree = findViewById(R.id.tpsmajoree);
        et_tempmoy = findViewById(R.id.tempmoy);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Patientez svp...");

        presenter = new StatsPresenter(this);
        presenter.recupstats();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.valider:
                openDialog();

                //presenter.mailgonflage();
                //Intent intent = new Intent(this, PressionBlocActivity.class);
                //startActivityForResult(intent, INTENT_EDIT);
                //finish();
                return true;
            default:

                return super.onOptionsItemSelected(item);

        }
    }

    private void openDialog() {
        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(), "Dialog");
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
    public void onSuccess(String nbrgonflage, String tpsgonflage, String tpsmajoree, String moytemp) {
        et_nbrgonflage.setText(nbrgonflage);
        int duree1 = 0;
        try {
            duree1 = Integer.parseInt(tpsgonflage);
        } catch(NumberFormatException nfe) {
            // Handle parse error.
        }
        String tpsgonflageT = formatHoursAndMinutes(duree1);
        et_tpsgonflage.setText(tpsgonflageT);
        int duree2 = 0;
        try {
            duree2 = Integer.parseInt(tpsmajoree);
        } catch(NumberFormatException nfe) {
            // Handle parse error.
        }
        String tpsmajoreeT = formatHoursAndMinutes(duree2);
        et_tpsmajoree.setText(tpsmajoreeT);
        et_tempmoy.setText(moytemp + "°C");
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

    @Override
    public void onMail() {
        Toast.makeText(StatsActivity.this, "Mail envoyé", Toast.LENGTH_SHORT).show();
    }

    public static String formatHoursAndMinutes(int totalMinutes) {
        String minutes = Integer.toString(totalMinutes % 60);
        minutes = minutes.length() == 1 ? "0" + minutes : minutes;
        return (totalMinutes / 60) + "h" + minutes + " min";
    }


}

