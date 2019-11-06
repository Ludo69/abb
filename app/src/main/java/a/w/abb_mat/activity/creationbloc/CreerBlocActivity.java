package a.w.abb_mat.activity.creationbloc;

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

public class CreerBlocActivity extends AppCompatActivity implements CreerBlocView{

    private static final int INTENT_EDIT = 200;
    EditText et_numblocCR, et_litrageblocCR, et_commentaireblocCR;
    private RadioGroup radiodispoBCR;
    private RadioButton radiodispoBouiCR;
    private RadioButton radiodispoBnonCR;

    CreerBlocPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_bloc);

        et_numblocCR = findViewById(R.id.numblocCR);
        et_commentaireblocCR = findViewById(R.id.commentaireblocCR);
        et_litrageblocCR = findViewById(R.id.litrageblocCR);
        radiodispoBCR = findViewById(R.id.radiodispoBCR);
        radiodispoBouiCR = findViewById(R.id.radiodispoBouiCR);
        radiodispoBnonCR = findViewById(R.id.radiodispoBnonCR);

        presenter = new CreerBlocPresenter(this);
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
                String numbloc = et_numblocCR.getText().toString();
                String commentairebloc = et_commentaireblocCR.getText().toString();
                String litragebloc = et_litrageblocCR.getText().toString();
                int dispobloc;
                if(radiodispoBouiCR.isChecked()){
                    dispobloc = 1;
                } else {
                    dispobloc = 0;
                }
                presenter.insertbloc(numbloc, litragebloc, commentairebloc, dispobloc);
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
