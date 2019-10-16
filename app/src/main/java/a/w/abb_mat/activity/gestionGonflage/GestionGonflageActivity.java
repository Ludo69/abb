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
import java.util.Calendar;

public class GestionGonflageActivity extends AppCompatActivity implements GestionGonflageView{

    private static final int INTENT_EDIT = 200;
    TextView et_txtnomgonfleur, et_txtnumbloc, et_txtdategonflage;

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

        String numbloc = (String) getIntent().getSerializableExtra("numbloc");
        String nomglonfleur = (String) getIntent().getSerializableExtra("gonfleur");
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        et_txtnomgonfleur.setText(nomglonfleur);
        et_txtnumbloc.setText(String.valueOf(numbloc));
        et_txtdategonflage.setText(currentDate);

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
