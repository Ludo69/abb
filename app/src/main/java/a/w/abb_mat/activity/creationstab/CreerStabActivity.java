package a.w.abb_mat.activity.creationstab;

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

public class CreerStabActivity extends AppCompatActivity implements CreerStabView{

    private static final int INTENT_EDIT = 200;
    EditText et_numstabCR, et_commentairestabCR;
    private RadioGroup radioTaille;
    private RadioButton radioTailleXS;
    private RadioButton radioTailleS;
    private RadioButton radioTailleM;
    private RadioButton radioTailleL;
    private RadioButton radioTailleXL;
    private RadioGroup radiodispoCR;
    private RadioButton radiodispoouiCR;
    private RadioButton radiodispononCR;

    CreerStabPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_stab);

        et_numstabCR = findViewById(R.id.numstabCR);
        et_commentairestabCR = findViewById(R.id.commentairestabCR);
        radioTaille = findViewById(R.id.radiotaille);
        radioTailleXS = findViewById(R.id.radiotailleXS);
        radioTailleS = findViewById(R.id.radiotailleS);
        radioTailleM = findViewById(R.id.radiotailleM);
        radioTailleL = findViewById(R.id.radiotailleL);
        radioTailleXL = findViewById(R.id.radiotailleXL);
        radiodispoCR = findViewById(R.id.radiodispoCR);
        radiodispoouiCR = findViewById(R.id.radiodispoouiCR);
        radiodispononCR = findViewById(R.id.radiodispononCR);

        presenter = new CreerStabPresenter(this);
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
                String numstab = et_numstabCR.getText().toString();
                String commentairestab = et_commentairestabCR.getText().toString();
                String taillestab;
                int dispostab;
                if(radioTailleXS.isChecked()){
                    taillestab = "XS";
                } else if(radioTailleS.isChecked()){
                    taillestab = "S";
                } else if(radioTailleM.isChecked()){
                    taillestab = "M";
                } else if(radioTailleL.isChecked()){
                    taillestab = "L";
                } else if(radioTailleXL.isChecked()){
                    taillestab = "XL";
                } else {
                    taillestab = "XS";
                }
                if(radiodispoouiCR.isChecked()){
                    dispostab = 1;
                } else {
                    dispostab = 0;
                }
                presenter.insertstab(numstab, taillestab, commentairestab, dispostab);
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
