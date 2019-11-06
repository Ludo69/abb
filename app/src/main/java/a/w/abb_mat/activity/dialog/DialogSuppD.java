package a.w.abb_mat.activity.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.detendeurm.DetendeurMActivity;
import a.w.abb_mat.activity.stabm.StabMActivity;
import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Detendeur;
import a.w.abb_mat.model.Stab;
import androidx.appcompat.app.AppCompatDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogSuppD extends AppCompatDialogFragment {


    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_suppression, null);


        builder.setView(view);
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Bundle arg = getArguments();
                int iddetendeur = arg.getInt("iddetendeur");
                Toast.makeText(getActivity(), "Détendeur supprimé !", Toast.LENGTH_SHORT).show();
                suppdetendeur(iddetendeur);
                Intent intent = new Intent(getActivity(), DetendeurMActivity.class);
                startActivity(intent);
            }
        });

        return builder.create();
    }

    void suppdetendeur(int iddetendeur) {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Detendeur> call = apiInterface.suppdetendeur(iddetendeur);
        call.enqueue(new Callback<Detendeur>() {
            @Override
            public void onResponse(Call<Detendeur> call, Response<Detendeur> response) {

            }

            @Override
            public void onFailure(Call<Detendeur> call, Throwable t) {

            }
        });

    }
}
