package a.w.abb_mat.activity.creationdetendeur;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.choixmaintenance.choixMaintenanceActivity;
import androidx.appcompat.app.AppCompatActivity;

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

public class CreerDetendeurActivity extends AppCompatActivity implements CreerDetendeurView{

    private static final int INTENT_EDIT = 200;
    EditText et_numdetendeurCR, et_commentairedetendeurCR;
    private RadioGroup radiodispoDCR;
    private RadioButton radiodispoDouiCR;
    private RadioButton radiodispoDnonCR;

    CreerDetendeurPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_detenteur);

        et_numdetendeurCR = findViewById(R.id.numdetendeurCR);
        et_commentairedetendeurCR = findViewById(R.id.commentairedetendeurCR);
        radiodispoDCR = findViewById(R.id.radiodispoDCR);
        radiodispoDouiCR = findViewById(R.id.radiodispoDouiCR);
        radiodispoDnonCR = findViewById(R.id.radiodispoDnonCR);

        presenter = new CreerDetendeurPresenter(this);
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
                String numdetendeur = et_numdetendeurCR.getText().toString();
                String commentairedetendeur = et_commentairedetendeurCR.getText().toString();
                int dispodetendeur;
                if(radiodispoDouiCR.isChecked()){
                    dispodetendeur = 1;
                } else {
                    dispodetendeur = 0;
                }
                presenter.insertdetendeur(numdetendeur, commentairedetendeur, dispodetendeur);
                Intent intent = new Intent(this, choixMaintenanceActivity.class);
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
        Log.d("******Success", message);
    }

    @Override
    public void onAddError(String message) {
        Log.d("******Error", message);
    }
}
