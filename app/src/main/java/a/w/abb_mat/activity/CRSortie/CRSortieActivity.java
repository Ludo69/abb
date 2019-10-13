package a.w.abb_mat.activity.CRSortie;

import a.w.abb_mat.R;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.thebluealliance.spectrum.SpectrumPalette;

public class CRSortieActivity extends AppCompatActivity implements CRSortieView {

    EditText et_nomsortie, et_datesortie;
    ProgressDialog progressDialog;

    CRSortiePresenter presenter;

    int idsortie;
    String nomsortie, datesortie;

    Menu actionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crsortie);

        et_nomsortie = findViewById(R.id.nomsortie);
        et_datesortie = findViewById(R.id.datesortie);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        presenter = new CRSortiePresenter(this);

        Intent intent = getIntent();
        idsortie = intent.getIntExtra("idsortie", 0);
        nomsortie = intent.getStringExtra("nomsortie");
        datesortie = intent.getStringExtra("datesortie");

        setDataFromIntentExtra();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor, menu);
        actionMenu = menu;

        if(idsortie != 0) {
            actionMenu.findItem(R.id.edit).setVisible(true);
            actionMenu.findItem(R.id.delete).setVisible(true);
            actionMenu.findItem(R.id.save).setVisible(false);
            actionMenu.findItem(R.id.update).setVisible(false);
        } else {
            actionMenu.findItem(R.id.edit).setVisible(false);
            actionMenu.findItem(R.id.delete).setVisible(false);
            actionMenu.findItem(R.id.save).setVisible(true);
            actionMenu.findItem(R.id.update).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String nomsortie = et_nomsortie.getText().toString().trim();
        String datesortie = et_datesortie.getText().toString().trim();

        switch (item.getItemId()) {
            case R.id.save:
                //Save
                if (nomsortie.isEmpty()) {
                    et_nomsortie.setError("Entrez le nom de la sortie");
                } else if (datesortie.isEmpty()) {
                    et_datesortie.setError("Entrez la date de la sortie");
                } else {
                    presenter.saveSortie(nomsortie, datesortie);
                }
                return true;

            case R.id.edit:
                editMode();
                actionMenu.findItem(R.id.edit).setVisible(false);
                actionMenu.findItem(R.id.delete).setVisible(false);
                actionMenu.findItem(R.id.save).setVisible(false);
                actionMenu.findItem(R.id.update).setVisible(true);
                return true;

            case R.id.update:
                //Update
                if (nomsortie.isEmpty()) {
                    et_nomsortie.setError("Entrez le nom de la sortie");
                } else if (datesortie.isEmpty()) {
                    et_datesortie.setError("Entrez la date de la sortie");
                } else {
                    presenter.updateSortie(idsortie, nomsortie, datesortie);
                }
                return true;

            case R.id.delete:

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Confirm !");
                alertDialog.setMessage("Are you sure?");
                alertDialog.setNegativeButton("Yes ",
                        (dialog, which) -> presenter.deleteSortie(idsortie));
                alertDialog.setPositiveButton("Cancel", ((dialog, which) -> {
                    dialog.dismiss();
                }));

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void showProgress() {
        //progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(a.w.abb_mat.activity.CRSortie.CRSortieActivity.this,
                message,
                Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(a.w.abb_mat.activity.CRSortie.CRSortieActivity.this,
                message,
                Toast.LENGTH_SHORT).show();
    }

    private void setDataFromIntentExtra() {

        if (idsortie != 0) {
            et_nomsortie.setText(nomsortie);
            et_datesortie.setText(datesortie);

            getSupportActionBar().setTitle("Update Note");
            readMode();

        } else {
            editMode();
        }

    }

    private void editMode() {
        et_nomsortie.setFocusableInTouchMode(true);
        et_datesortie.setFocusableInTouchMode(true);
    }

    private void readMode() {
        et_nomsortie.setFocusableInTouchMode(false);
        et_datesortie.setFocusableInTouchMode(false);
        et_nomsortie.setFocusable(false);
        et_datesortie.setFocusable(false);
    }
}
