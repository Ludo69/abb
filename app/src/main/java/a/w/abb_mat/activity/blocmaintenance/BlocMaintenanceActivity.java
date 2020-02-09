package a.w.abb_mat.activity.blocmaintenance;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.blocm.BlocMActivity;
import a.w.abb_mat.activity.detendeurm.DetendeurMActivity;
import a.w.abb_mat.activity.dialog.DialogSuppB;
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
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

public class BlocMaintenanceActivity extends AppCompatActivity implements BlocMaintenanceView{

    private static final int INTENT_EDIT = 200;
    TextView et_numbloc;
    EditText et_txtcommentaireblocM;
    ImageView et_img_supp;
    private RadioGroup radioGroupB;
    private RadioButton radioButtonBOui;
    private RadioButton radioButtonBNon;

    BlocMaintenancePresenter presenter;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloc_maintenance);

        et_numbloc = findViewById(R.id.numblocM);
        et_txtcommentaireblocM = findViewById(R.id.txtcommentaireblocM);
        et_img_supp = findViewById(R.id.suppression);
        radioGroupB = findViewById(R.id.radiogroupB);
        radioButtonBOui = findViewById(R.id.radioboutonBoui);
        radioButtonBNon = findViewById(R.id.radioboutonBnon);

        int idbloc = (int) getIntent().getIntExtra("idbloc", 0);
        String numbloc = (String) getIntent().getSerializableExtra("numbloc");
        String combloc = (String) getIntent().getSerializableExtra("commentairebloc");
        int dispobloc = (int) getIntent().getIntExtra("dispobloc", 0);

        et_numbloc.setText(numbloc);
        et_txtcommentaireblocM.setText(combloc);
        if (dispobloc == 0) {
            radioButtonBNon.setChecked(true);
        } else {
            radioButtonBOui.setChecked(true);
        }

        et_img_supp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idbloc = (int) getIntent().getIntExtra("idbloc", 0);
                Log.d("CREATION DIALOG ", String.valueOf(idbloc));
                DialogSuppB dialog = new DialogSuppB();
                Bundle bundle = new Bundle();
                bundle.putInt("idbloc", idbloc);
                dialog.setArguments(bundle);
                dialog.show(getSupportFragmentManager(), "DialogSuppB");
            }
        });

        presenter = new BlocMaintenancePresenter(this);

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
                int idbloc = (int) getIntent().getIntExtra("idbloc", 0);
                String nummat = et_numbloc.getText().toString();
                String commentairebloc = et_txtcommentaireblocM.getText().toString();
                String codeunique = (String) getIntent().getSerializableExtra("codeunique");
                String nommembre = "AA MATOS ENTRETIEN";
                String daterestitution = "14102019";
                int dispobloc = 1;
                if(radioButtonBNon.isChecked()){
                    dispobloc = 0;
                    codeunique = UUID.randomUUID().toString();
                }
                if(radioButtonBNon.isChecked()){
                    presenter.updateBlocM(idbloc, commentairebloc, dispobloc, codeunique);
                    presenter.inserthistorique(2, nummat, "14102019", "0", nommembre, codeunique);
                } else {
                    presenter.restitution(codeunique, daterestitution);
                    codeunique = "";
                    presenter.updateBlocM(idbloc, commentairebloc, dispobloc, codeunique);;
                }
                Intent intent = new Intent(this, BlocMActivity.class);
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
