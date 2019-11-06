package a.w.abb_mat.activity.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.stabm.StabMActivity;
import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Stab;
import a.w.abb_mat.model.Stat;
import androidx.appcompat.app.AppCompatDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogSuppS extends AppCompatDialogFragment {


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
                int idstab = arg.getInt("idstab");
                Toast.makeText(getActivity(), "Stab supprimée !", Toast.LENGTH_SHORT).show();
                suppstab(idstab);
                Intent intent = new Intent(getActivity(), StabMActivity.class);
                startActivity(intent);
            }
        });

        return builder.create();
    }

    void suppstab(int idstab) {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Stab> call = apiInterface.suppstab(idstab);
        call.enqueue(new Callback<Stab>() {
            @Override
            public void onResponse(Call<Stab> call, Response<Stab> response) {

            }

            @Override
            public void onFailure(Call<Stab> call, Throwable t) {

            }
        });

    }
}
