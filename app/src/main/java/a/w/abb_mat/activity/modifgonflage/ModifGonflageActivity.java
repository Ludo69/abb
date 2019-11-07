package a.w.abb_mat.activity.modifgonflage;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.choixgonflage.ChoixGonflageActivity;
import a.w.abb_mat.activity.listegonflage.ListeGonflageActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class ModifGonflageActivity extends AppCompatActivity implements ModifGonflageView {

    private static final int INTENT_EDIT = 200;
    TextView et_nomgonfleur, et_numbloc, et_dategonflage, et_compteurdep, et_nbrbloc ;
    EditText et_compteurfin, et_temp, et_pression;

    ModifGonflagePresenter presenter;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_gonflage);

        presenter = new ModifGonflagePresenter(this);

        et_nomgonfleur = findViewById(R.id.nomgonfleurM);
        et_dategonflage = findViewById(R.id.dategonflageM);
        et_numbloc = findViewById(R.id.numblocM);
        et_nbrbloc = findViewById(R.id.nbrblocM);
        et_compteurdep = findViewById(R.id.compteurdepartM);
        et_compteurfin = findViewById(R.id.compteurfinM);
        et_temp = findViewById(R.id.temperaturegonflageM);
        et_pression = findViewById(R.id.pressionfinalM);

        int id = getIntent().getIntExtra("id", 0);
        String dategonflage = (String) getIntent().getSerializableExtra("date");
        String gonfleur = (String) getIntent().getSerializableExtra("gonfleur");
        float compteurdep = (float) getIntent().getFloatExtra("compteurdep", 0);
        float compteurfin = (float) getIntent().getFloatExtra("compteurfin", 0);
        int numbloc = getIntent().getIntExtra("numbloc", 0);
        int nbrbloc = getIntent().getIntExtra("nbrbloc", 0);
        int temperature = getIntent().getIntExtra("temperature", 0);
        int pression = getIntent().getIntExtra("pression", 0);
        et_nomgonfleur.setText(gonfleur);
        et_dategonflage.setText(dategonflage);
        et_numbloc.setText(String.valueOf(numbloc));
        et_nbrbloc.setText(String.valueOf(nbrbloc));
        et_compteurdep.setText(String.valueOf(compteurdep));
        et_compteurfin.setText(String.valueOf(compteurfin));
        et_temp.setText(String.valueOf(temperature));
        et_pression.setText(String.valueOf(pression));
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
                int id = getIntent().getIntExtra("id", 0);
                Integer nbloc = Integer.parseInt(et_numbloc.getText().toString());
                int nbrbloc = Integer.parseInt(et_nbrbloc.getText().toString());
                Float compteurdep = Float.parseFloat(et_compteurdep.getText().toString());
                Float compteurfinal = Float.parseFloat(et_compteurfin.getText().toString());
                Integer tempgonflage = Integer.parseInt(et_temp.getText().toString());
                Integer pressionblocf = Integer.parseInt(et_pression.getText().toString());

                if (compteurfinal == 0) {
                    et_compteurfin.setError("Entrez le compteur");
                } else if (tempgonflage == 0) {
                    et_temp.setError("Entrez la température");
                } else if (pressionblocf == 0){
                    et_pression.setError("Entrez la pression finale");
                } else {
                    presenter.updateGonflageM(id, nbloc, nbrbloc, compteurdep,compteurfinal, tempgonflage, pressionblocf);
                    presenter.updatePression(id, pressionblocf);
                    Intent intent = new Intent(this, ListeGonflageActivity.class);
                    startActivityForResult(intent, INTENT_EDIT);
                    finish();
                }
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onAddSuccess(String message) {

    }

    @Override
    public void onAddError(String message) {

    }
}
