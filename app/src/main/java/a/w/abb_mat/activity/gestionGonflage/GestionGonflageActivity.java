package a.w.abb_mat.activity.gestionGonflage;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.gonflagebloc.GonflageBlocActivity;
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
    EditText et_pressionfinale, et_compteurfinal, et_tempgonflage, et_nbrbloc;

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
        et_compteurfinal = findViewById(R.id.compteurfin);
        et_tempgonflage = findViewById(R.id.temperaturegonflage);
        et_nbrbloc = findViewById(R.id.nbrbloc);


        String numbloc = (String) getIntent().getSerializableExtra("numbloc");
        String nomglonfleur = (String) getIntent().getSerializableExtra("gonfleur");
        String litragebloc = (String) getIntent().getSerializableExtra("litragebloc");
        int pressionbloc = getIntent().getIntExtra("pressionbloc", 0);
        float compteurfin = 0;
        int tempgonflage = 0;
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        et_txtnomgonfleur.setText(nomglonfleur);
        et_txtnumbloc.setText(String.valueOf(numbloc));
        et_litre.setText(" de " +litragebloc);
        et_txtdategonflage.setText(currentDate);
        et_pressionfinale.setText(String.valueOf(pressionbloc));
        et_compteurfinal.setText(String.valueOf(compteurfin));
        et_tempgonflage.setText(String.valueOf(tempgonflage));
        et_nbrbloc.setText("2");

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
                int idbloc = getIntent().getIntExtra("idbloc", 0);
                int nbloc = getIntent().getIntExtra("numbloc",0);
                String nomg = (String) getIntent().getSerializableExtra("gonfleur");
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int saison = year;
                Integer pressionblocf = Integer.parseInt(et_pressionfinale.getText().toString());
                Float compteurfinal = Float.parseFloat(et_compteurfinal.getText().toString());
                Integer tempgonflage = Integer.parseInt(et_tempgonflage.getText().toString());
                Integer nbrbloc = Integer.parseInt(et_nbrbloc.getText().toString());
                Log.d("TEMP : ", String.valueOf(tempgonflage));
                if (compteurfinal == 0) {
                    et_compteurfinal.setError("Entrez le compteur");
                } else if (tempgonflage == 0) {
                    et_tempgonflage.setError("Entrez la température");
                } else if (pressionblocf == 0){
                    et_pressionfinale.setError("Entrez la pression finale");
                } else if (nbrbloc == 0) {
                    et_nbrbloc.setError("Entrez le nombre de bloc");
                }else {
                    presenter.insertGonflage(nbloc,nomg,compteurfinal,nbrbloc, tempgonflage, pressionblocf, year);
                    presenter.updatePression(idbloc, pressionblocf);
                    Intent intent = new Intent(this, GonflageBlocActivity.class);
                    startActivityForResult(intent, INTENT_EDIT);
                    finish();
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
