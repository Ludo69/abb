package a.w.abb_mat.activity.detendeurmaintenance;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.detendeurm.DetendeurMActivity;
import a.w.abb_mat.activity.dialog.DialogSuppD;
import a.w.abb_mat.activity.pressionbloc.PressionBlocActivity;
import a.w.abb_mat.activity.stabm.StabMActivity;
import a.w.abb_mat.model.Detendeur;
import a.w.abb_mat.model.Stab;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class DetendeurMaintenanceActivity extends AppCompatActivity implements DetendeurMaintenanceView{

    private static final String TAG = "DetendeurMaintenanceActivity";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final int INTENT_EDIT = 200;
    TextView et_numdetendeur, et_daterevision;
    EditText et_txtcommentairedetendeurM;
    ImageView et_img_supp;
    private RadioGroup radioGroupD;
    private RadioButton radioButtonDOui;
    private RadioButton radioButtonDNon;
    private Calendar mcalendar;
    private int day,month,year;

    DetendeurMaintenancePresenter presenter;
    List<Detendeur> detendeur;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detendeur_maintenance);

        et_numdetendeur = findViewById(R.id.numdetendeurM);
        et_txtcommentairedetendeurM = findViewById(R.id.txtcommentairedetendeurM);
        et_daterevision = findViewById(R.id.daterevision);
        et_img_supp = findViewById(R.id.suppression);
        radioGroupD = findViewById(R.id.radiogroupD);
        radioButtonDOui = findViewById(R.id.radioboutonDoui);
        radioButtonDNon = findViewById(R.id.radioboutonDnon);

        et_daterevision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        DetendeurMaintenanceActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d("***", "onDateSet: date: " + year + "-" + month + "-" + day);
                String date = year + "-" + month + "-" + day;
                et_daterevision.setText(date);
            }
        };

        int iddetendeur = (int) getIntent().getIntExtra("iddetendeur", 0);
        String numdetendeur = (String) getIntent().getSerializableExtra("numdetendeur");
        String comdetendeur = (String) getIntent().getSerializableExtra("commentairedetendeur");
        int dispodetendeur = (int) getIntent().getIntExtra("dispodetendeur", 0);
        String daterevision = (String) getIntent().getSerializableExtra("daterevision");
        et_numdetendeur.setText(numdetendeur);
        et_txtcommentairedetendeurM.setText(comdetendeur);
        et_daterevision.setText(daterevision);
        if (dispodetendeur == 0) {
            radioButtonDNon.setChecked(true);
        } else {
            radioButtonDOui.setChecked(true);
        }

        et_img_supp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openDialogSupp();
                int iddetendeur = (int) getIntent().getIntExtra("iddetendeur", 0);
                Log.d("CREATION DIALOG ", String.valueOf(iddetendeur));
                DialogSuppD dialog = new DialogSuppD();
                Bundle bundle = new Bundle();
                bundle.putInt("iddetendeur", iddetendeur);
                dialog.setArguments(bundle);
                dialog.show(getSupportFragmentManager(), "DialogSuppD");
            }
        });

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
                String nummat = et_numdetendeur.getText().toString();
                String nommembre = "AA MATOS ENTRETIEN";
                String daterevision = et_daterevision.getText().toString();
                String daterestitution = "14102019";
                String codeunique = (String) getIntent().getSerializableExtra("codeunique");
                int dispodetendeur = 1;
                if(radioButtonDNon.isChecked()){
                    dispodetendeur = 0;
                    codeunique = UUID.randomUUID().toString();
                }
                if(radioButtonDNon.isChecked()){
                    presenter.updateDetendeurM(iddetendeur, commentairedetendeur, dispodetendeur, daterevision, codeunique);
                    presenter.inserthistorique(1, nummat, "14102019", "0", nommembre, codeunique);
                } else {
                    presenter.restitution(codeunique, daterestitution);
                    codeunique = "";
                    presenter.updateDetendeurM(iddetendeur, commentairedetendeur, dispodetendeur, daterevision, codeunique);
                }
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
