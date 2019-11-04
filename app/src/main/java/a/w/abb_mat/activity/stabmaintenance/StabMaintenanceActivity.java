package a.w.abb_mat.activity.stabmaintenance;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.pressionbloc.PressionBlocActivity;
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

public class StabMaintenanceActivity extends AppCompatActivity implements StabMaintenanceView{

    TextView et_numstab;
    EditText et_txtcommentairestabM;
    private RadioGroup radioGroup;
    private RadioButton radioButtonOui;
    private RadioButton radioButtonNon;

    StabMaintenancePresenter presenter;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stab_maintenance);

        et_numstab = findViewById(R.id.numstabM);
        et_txtcommentairestabM = findViewById(R.id.txtcommentairestabM);
        radioGroup = findViewById(R.id.radiogroup);
        radioButtonOui = findViewById(R.id.radioboutonoui);
        radioButtonNon = findViewById(R.id.radioboutonnon);

        int idstab = (int) getIntent().getIntExtra("idmat", 0);
        String numstab = (String) getIntent().getSerializableExtra("nummat");
        String comstab = (String) getIntent().getSerializableExtra("commentairestab");
        int dispostab = (int) getIntent().getIntExtra("dispostab", 0);

        et_numstab.setText(numstab);
        et_txtcommentairestabM.setText(comstab);
        if (dispostab == 0) {
            Log.d("****************NON ", String.valueOf(dispostab));
            radioButtonNon.setChecked(true);
        } else {
            Log.d("****************OUI ", String.valueOf(dispostab));
            radioButtonOui.setChecked(true);
        }

        presenter = new StabMaintenancePresenter(this);

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
                Toast.makeText(this, "Validation", Toast.LENGTH_SHORT).show();
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
