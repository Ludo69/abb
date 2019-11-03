package a.w.abb_mat.activity.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.choixmaintenance.choixMaintenanceActivity;
import a.w.abb_mat.activity.stats.StatsPresenter;
import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Stat;
import androidx.appcompat.app.AppCompatDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogPass extends AppCompatDialogFragment {

    private EditText txtpass;

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_maintenance, null);

        builder.setView(view);
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String pass = txtpass.getText().toString();
                if(pass.equals("ludoabb")){
                    Intent intent = new Intent(getActivity(), choixMaintenanceActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Erreur mot de passe", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtpass = view.findViewById(R.id.txtpass);

        return builder.create();
    }
}
