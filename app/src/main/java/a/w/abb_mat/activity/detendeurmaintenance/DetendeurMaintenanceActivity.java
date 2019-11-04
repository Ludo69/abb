package a.w.abb_mat.activity.detendeurmaintenance;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.detendeurm.DetendeurMActivity;
import a.w.abb_mat.activity.pressionbloc.PressionBlocActivity;
import a.w.abb_mat.activity.stabm.StabMActivity;
import a.w.abb_mat.model.Stab;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DetendeurMaintenanceActivity extends AppCompatActivity implements DetendeurMaintenanceView{

    private static final int INTENT_EDIT = 200;
    TextView et_numdetendeur;
    EditText et_txtcommentairedetendeurM;
    private RadioGroup radioGroupD;
    private RadioButton radioButtonDOui;
    private RadioButton radioButtonDNon;

    DetendeurMaintenancePresenter presenter;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detendeur_maintenance);

        et_numdetendeur = findViewById(R.id.numdetendeurM);
        et_txtcommentairedetendeurM = findViewById(R.id.txtcommentairedetendeurM);
        radioGroupD = findViewById(R.id.radiogroupD);
        radioButtonDOui = findViewById(R.id.radioboutonDoui);
        radioButtonDNon = findViewById(R.id.radioboutonDnon);

        int iddetendeur = (int) getIntent().getIntExtra("iddetendeur", 0);
        String numdetendeur = (String) getIntent().getSerializableExtra("numdetendeur");
        String comdetendeur = (String) getIntent().getSerializableExtra("commentairedetendeur");
        int dispodetendeur = (int) getIntent().getIntExtra("dispodetendeur", 0);

        et_numdetendeur.setText(numdetendeur);
        et_txtcommentairedetendeurM.setText(comdetendeur);
        if (dispodetendeur == 0) {
            radioButtonDNon.setChecked(true);
        } else {
            radioButtonDOui.setChecked(true);
        }

        presenter = new DetendeurMaintenancePresenter(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_maintenance, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.valider:
                int iddetendeur = (int) getIntent().getIntExtra("iddetendeur", 0);
                String commentairedetendeur = et_txtcommentairedetendeurM.getText().toString();
                int dispodetendeur = 1;
                if(radioButtonDNon.isChecked()){
                    dispodetendeur = 0;
                }
                presenter.updateDetendeurM(iddetendeur, commentairedetendeur, dispodetendeur);
                Intent intent = new Intent(this, DetendeurMActivity.class);
                startActivityForResult(intent, INTENT_EDIT);
                finish();
                return true;
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
