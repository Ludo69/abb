package a.w.abb_mat.activity.gestionPression;

import a.w.abb_mat.R;
import a.w.abb_mat.api.ApiInterface;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class GestionPressionActivity extends AppCompatActivity {

    EditText et_txtpression;
    TextView et_txtbloc;

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
                updatePression();
                return true;
            default:

                return super.onOptionsItemSelected(item);

        }
    }

    private void updatePression() {

        progressDialog.show();

    }
}
