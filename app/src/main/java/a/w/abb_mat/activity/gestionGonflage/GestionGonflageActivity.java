package a.w.abb_mat.activity.gestionGonflage;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.gonflagebloc.GonflageBlocActivity;
import a.w.abb_mat.activity.pressionbloc.PressionBlocActivity;
import a.w.abb_mat.activity.stab.StabActivity;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Gonflage;

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
import java.util.List;

public class GestionGonflageActivity extends AppCompatActivity implements GestionGonflageView{

    private static final int INTENT_EDIT = 200;
    private List<Gonflage> gonflages;
    TextView et_txtnomgonfleur, et_txtnumbloc, et_txtdategonflage;
    EditText et_pressionfinale, et_compteurfinal, et_tempgonflage, et_nbrbloc;

    GestionGonflagePresenter presenter;

    ProgressDialog progressDialog;
    ApiInterface apiInterface;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_gonflage);

        presenter = new GestionGonflagePresenter(this);

        et_txtnomgonfleur = findViewById(R.id.nomgonfleur);
        et_txtnumbloc = findViewById(R.id.numbloc);
        et_txtdategonflage = findViewById(R.id.dategonflage);
        et_pressionfinale = findViewById(R.id.txtpressionfinal);
        et_compteurfinal = findViewById(R.id.compteurfin);
        et_tempgonflage = findViewById(R.id.temperaturegonflage);
        et_nbrbloc = findViewById(R.id.nbrbloc);
        int type = getIntent().getIntExtra("type", 0);
        if(type == 0) {
            String numbloc = (String) getIntent().getSerializableExtra("numbloc");
            String nomglonfleur = (String) getIntent().getSerializableExtra("gonfleur");
            int pressionbloc = getIntent().getIntExtra("pressionbloc", 0);
            float compteurfin = 0;
            int tempgonflage = 0;
            Calendar calendar = Calendar.getInstance();
            String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
            et_txtnomgonfleur.setText(nomglonfleur);
            et_txtnumbloc.setText(String.valueOf(numbloc));
            et_txtdategonflage.setText(currentDate);
            et_pressionfinale.setText(String.valueOf(pressionbloc));
            et_compteurfinal.setText(String.valueOf(compteurfin));
            et_tempgonflage.setText(String.valueOf(tempgonflage));
            et_nbrbloc.setText("2");
        } else if(type == 1) {
            int id = getIntent().getIntExtra("id", 0);
            String dategonflage = (String) getIntent().getSerializableExtra("date");
            String gonfleur = (String) getIntent().getSerializableExtra("gonfleur");
            int numbloc = getIntent().getIntExtra("numbloc", 0);
            int nbrbloc = getIntent().getIntExtra("nbrbloc", 0);
            float compteur = getIntent().getFloatExtra("compteur", 0);
            int temperature = getIntent().getIntExtra("temperature", 0);
            int pression = getIntent().getIntExtra("pression", 0);
            et_txtnomgonfleur.setText(gonfleur);
            et_txtdategonflage.setText(dategonflage);
            et_txtnumbloc.setText(String.valueOf(numbloc));
            et_nbrbloc.setText(String.valueOf(nbrbloc));
            et_compteurfinal.setText(String.valueOf(compteur));
            et_tempgonflage.setText(String.valueOf(temperature));
            et_pressionfinale.setText(String.valueOf(pression));
        }



        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Patientez svp...");



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
                int type = getIntent().getIntExtra("type", 0);
                if(type == 0) {
                    int idbloc = getIntent().getIntExtra("idbloc", 0);
                    String nomg = (String) getIntent().getSerializableExtra("gonfleur");
                    Calendar c = Calendar.getInstance();
                    int year = c.get(Calendar.YEAR);
                    Integer nbloc = Integer.parseInt(et_txtnumbloc.getText().toString());
                    Integer pressionblocf = Integer.parseInt(et_pressionfinale.getText().toString());
                    Float compteurfinal = Float.parseFloat(et_compteurfinal.getText().toString());
                    Integer tempgonflage = Integer.parseInt(et_tempgonflage.getText().toString());
                    Integer nbrbloc = Integer.parseInt(et_nbrbloc.getText().toString());
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
                        presenter.updateCompteur(compteurfinal);
                        Intent intent = new Intent(this, GonflageBlocActivity.class);
                        startActivityForResult(intent, INTENT_EDIT);
                        finish();
                    }
                    return true;
                } else if(type == 1) {
                    int id = getIntent().getIntExtra("id", 0);
                    int idbloc = getIntent().getIntExtra("idbloc", 0);
                    String nomg = (String) getIntent().getSerializableExtra("gonfleur");
                    Calendar c = Calendar.getInstance();
                    int year = c.get(Calendar.YEAR);
                    Integer nbloc = Integer.parseInt(et_txtnumbloc.getText().toString());
                    Integer pressionblocf = Integer.parseInt(et_pressionfinale.getText().toString());
                    Float compteurfinal = Float.parseFloat(et_compteurfinal.getText().toString());
                    Integer tempgonflage = Integer.parseInt(et_tempgonflage.getText().toString());
                    Integer nbrbloc = Integer.parseInt(et_nbrbloc.getText().toString());
                    if (compteurfinal == 0) {
                        et_compteurfinal.setError("Entrez le compteur");
                    } else if (tempgonflage == 0) {
                        et_tempgonflage.setError("Entrez la température");
                    } else if (pressionblocf == 0){
                        et_pressionfinale.setError("Entrez la pression finale");
                    } else if (nbrbloc == 0) {
                        et_nbrbloc.setError("Entrez le nombre de bloc");
                    }else {
                        presenter.updateGonflage(id, nbloc,nomg,compteurfinal,nbrbloc, tempgonflage, pressionblocf, year);
                        //presenter.updatePression(idbloc, pressionblocf);
                        //presenter.updateCompteur(compteurfinal);
                        //Intent intent = new Intent(this, GonflageBlocActivity.class);
                        //startActivityForResult(intent, INTENT_EDIT);
                        //finish();
                    }
                    return true;
                }

            default:

                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void showProgress() {
//        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void onAddSuccess(String message) {
        //Toast.makeText(GestionGonflageActivity.this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onAddError(String message) {
        //Toast.makeText(GestionGonflageActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
