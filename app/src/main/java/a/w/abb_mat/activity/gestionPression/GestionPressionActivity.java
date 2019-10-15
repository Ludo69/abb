package a.w.abb_mat.activity.gestionPression;

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

public class GestionPressionActivity extends AppCompatActivity implements GestionPressionView{

    private static final int INTENT_EDIT = 200;
    EditText et_txtpression;
    TextView et_txtbloc;

    GestionPressionPresenter presenter;

    ProgressDialog progressDialog;
    ApiInterface apiInterface;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_pression);

        et_txtbloc = findViewById(R.id.txtnumbloc);
        et_txtpression = findViewById(R.id.txtpression);

        String numbloc = (String) getIntent().getSerializableExtra("numbloc");
        int pressionbloc = (int) getIntent().getIntExtra("pressionbloc", 0);
        et_txtbloc.setText("Bloc n° " + numbloc);
        et_txtpression.setText(String.valueOf(pressionbloc));

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Patientez svp...");

        presenter = new GestionPressionPresenter(this);

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
                int idbloc = (int) getIntent().getIntExtra("idbloc", 0);
                Integer pressionbloc = Integer.parseInt(et_txtpression.getText().toString());
                presenter.updatePression(idbloc, pressionbloc);
                Intent intent = new Intent(this, PressionBlocActivity.class);
                startActivityForResult(intent, INTENT_EDIT);
                finish();
                return true;
            default:

                return super.onOptionsItemSelected(item);

        }
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
    public void onAddSuccess(String message) {
        Toast.makeText(GestionPressionActivity.this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onAddError(String message) {
        Toast.makeText(GestionPressionActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
