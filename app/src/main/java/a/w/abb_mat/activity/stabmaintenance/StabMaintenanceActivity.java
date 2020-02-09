package a.w.abb_mat.activity.stabmaintenance;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.dialog.DialogSuppS;
import a.w.abb_mat.activity.stabm.StabMActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.UUID;

public class StabMaintenanceActivity extends AppCompatActivity implements StabMaintenanceView{

    private static final int INTENT_EDIT = 200;
    TextView et_numstab;
    EditText et_txtcommentairestabM;
    ImageView et_img_supp;
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
        et_img_supp = findViewById(R.id.suppression);
        radioGroup = findViewById(R.id.radiogroup);
        radioButtonOui = findViewById(R.id.radioboutonoui);
        radioButtonNon = findViewById(R.id.radioboutonnon);

        String numstab = (String) getIntent().getSerializableExtra("numstab");
        String comstab = (String) getIntent().getSerializableExtra("commentairestab");
        int dispostab = (int) getIntent().getIntExtra("dispostab", 0);

        et_numstab.setText(numstab);
        et_txtcommentairestabM.setText(comstab);
        if (dispostab == 0) {
            radioButtonNon.setChecked(true);
        } else {
            radioButtonOui.setChecked(true);
        }

        et_img_supp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openDialogSupp();
                int idstab = (int) getIntent().getIntExtra("idstab", 0);
                Log.d("CREATION DIALOG ", String.valueOf(idstab));
                DialogSuppS dialog = new DialogSuppS();
                Bundle bundle = new Bundle();
                bundle.putInt("idstab", idstab);
                dialog.setArguments(bundle);
                dialog.show(getSupportFragmentManager(), "DialogSuppS");
            }
        });

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
                int idstab = (int) getIntent().getIntExtra("idstab", 0);
                String commentairestab = et_txtcommentairestabM.getText().toString();
                String nummat = et_numstab.getText().toString();
                String codeunique = (String) getIntent().getSerializableExtra("codeunique");
                String nommembre = "AA MATOS ENTRETIEN";
                String daterestitution = "14102019";
                int dispostab = 1;
                if(radioButtonNon.isChecked()){
                    dispostab = 0;
                    codeunique = UUID.randomUUID().toString();
                }
                if(radioButtonNon.isChecked()){
                    presenter.updateStabM(idstab, commentairestab, dispostab, codeunique);
                    presenter.inserthistorique(0, nummat, "14102019", "0", nommembre, codeunique);
                } else {
                    presenter.restitution(codeunique, daterestitution);
                    codeunique = "";
                    presenter.updateStabM(idstab, commentairestab, dispostab, codeunique);;
                }

                Intent intent = new Intent(this, StabMActivity.class);
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
