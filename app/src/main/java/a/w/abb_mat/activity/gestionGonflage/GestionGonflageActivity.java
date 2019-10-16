package a.w.abb_mat.activity.gestionGonflage;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.pressionbloc.PressionBlocActivity;
import a.w.abb_mat.activity.stab.StabActivity;
import a.w.abb_mat.api.ApiInterface;
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

import java.text.DateFormat;
import java.time.Year;
import java.util.Calendar;

public class GestionGonflageActivity extends AppCompatActivity implements GestionGonflageView{

    private static final int INTENT_EDIT = 200;
    TextView et_txtnomgonfleur, et_txtnumbloc, et_txtdategonflage, et_litre;
    EditText et_pressionfinale, et_dureegonflage, et_tempgonflage;

    GestionGonflagePresenter presenter;

    ProgressDialog progressDialog;
    ApiInterface apiInterface;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_gonflage);

        et_txtnomgonfleur = findViewById(R.id.nomgonfleur);
        et_txtnumbloc = findViewById(R.id.numbloc);
        et_txtdategonflage = findViewById(R.id.dategonflage);
        et_litre = findViewById(R.id.litre);
        et_pressionfinale = findViewById(R.id.txtpressionfinal);
        et_dureegonflage = findViewById(R.id.dureegonflage);
        et_tempgonflage = findViewById(R.id.temperaturegonflage);


        String numbloc = (String) getIntent().getSerializableExtra("numbloc");
        String nomglonfleur = (String) getIntent().getSerializableExtra("gonfleur");
        String litragebloc = (String) getIntent().getSerializableExtra("litragebloc");
        int pressionbloc = getIntent().getIntExtra("pressionbloc", 0);
        int dureegonflage = 0;
        int tempgonflage = 0;
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        et_txtnomgonfleur.setText(nomglonfleur);
        et_txtnumbloc.setText(String.valueOf(numbloc));
        et_litre.setText(" de " +litragebloc);
        et_txtdategonflage.setText(currentDate);
        et_pressionfinale.setText(String.valueOf(pressionbloc));
        et_dureegonflage.setText(String.valueOf(dureegonflage));
        et_tempgonflage.setText(String.valueOf(tempgonflage));

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Patientez svp...");

        presenter = new GestionGonflagePresenter(this);

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
                int nbloc = getIntent().getIntExtra("numbloc",0);
                String nomg = (String) getIntent().getSerializableExtra("gonfleur");
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int saison = year;
                Integer pressionbloc = Integer.parseInt(et_pressionfinale.getText().toString());
                Integer dureegonflage = Integer.parseInt(et_dureegonflage.getText().toString());
                Integer tempgonflage = Integer.parseInt(et_tempgonflage.getText().toString());
                if (dureegonflage == 0) {
                    et_dureegonflage.setError("Entrez la durée");
                } else if (tempgonflage == 0) {
                    et_tempgonflage.setError("Entrez la température");
                } else if (pressionbloc == 0){
                    et_pressionfinale.setError("Entrez la pression finale");
                } else {
                    //presenter.insertGonflage(nbloc,nomg,dureegonflage,tempgonflage, pressionbloc, year);
                }
                return true;
            default:

                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void onAddSuccess(String message) {
        Toast.makeText(GestionGonflageActivity.this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onAddError(String message) {
        Toast.makeText(GestionGonflageActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
