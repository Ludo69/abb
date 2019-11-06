package a.w.abb_mat.activity.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.blocm.BlocMActivity;
import a.w.abb_mat.activity.detendeurm.DetendeurMActivity;
import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Bloc;
import a.w.abb_mat.model.Detendeur;
import androidx.appcompat.app.AppCompatDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogSuppB extends AppCompatDialogFragment {


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
                int idbloc = arg.getInt("idbloc");
                Toast.makeText(getActivity(), "Bloc supprimé !", Toast.LENGTH_SHORT).show();
                suppbloc(idbloc);
                Intent intent = new Intent(getActivity(), BlocMActivity.class);
                startActivity(intent);
            }
        });

        return builder.create();
    }

    void suppbloc(int idbloc) {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Bloc> call = apiInterface.suppbloc(idbloc);
        call.enqueue(new Callback<Bloc>() {
            @Override
            public void onResponse(Call<Bloc> call, Response<Bloc> response) {

            }

            @Override
            public void onFailure(Call<Bloc> call, Throwable t) {

            }
        });

    }
}
