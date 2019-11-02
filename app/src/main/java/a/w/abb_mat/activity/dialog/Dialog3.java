package a.w.abb_mat.activity.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import a.w.abb_mat.R;
import a.w.abb_mat.api.ApiClient;
import a.w.abb_mat.api.ApiInterface;
import a.w.abb_mat.model.Stat;
import androidx.appcompat.app.AppCompatDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dialog3 extends AppCompatDialogFragment {

    private EditText txtmail;

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view);
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton("Envoi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String email = txtmail.getText().toString();
                mailgonflage(email);
            }
        });

        txtmail = view.findViewById(R.id.txtmail);

        return builder.create();
    }

    void mailgonflage(String email) {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Stat> call = apiInterface.Getmail3(email);
        call.enqueue(new Callback<Stat>() {
            @Override
            public void onResponse(Call<Stat> call, Response<Stat> response) {

            }

            @Override
            public void onFailure(Call<Stat> call, Throwable t) {
            }
        });

    }


    public interface dialogListener {
        void envoimail(String email);
    }
}
