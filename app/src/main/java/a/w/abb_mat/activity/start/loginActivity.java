package a.w.abb_mat.activity.start;

import a.w.abb_mat.activity.choix.choixActivity;
import androidx.appcompat.app.AppCompatActivity;
import a.w.abb_mat.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    //private Button btn_Send;
    private ImageView btnGo;
    private Handler handler;
    private ProgressBar pb_loader;
    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pb_loader = (ProgressBar) findViewById(R.id.pb_loader);
        pass = (EditText) findViewById(R.id.txtAccueilPass);
        handler = new Handler();
        pb_loader.setVisibility(View.INVISIBLE);

        btnGo = (ImageView) findViewById(R.id.btnGo);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ConnexionInternet.isConnectedInternet(loginActivity.this))
                {
                    String password = pass.getText().toString();
                    if(password.equals("")){
                        Toast.makeText(getApplicationContext(), "Mot de passe obligatoire", Toast.LENGTH_SHORT).show();
                    }else{
                        if(password.equals("mdp")){
                            pb_loader.setVisibility(View.VISIBLE);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    pb_loader.setVisibility(View.GONE);
                                    Intent intent = new Intent(getApplicationContext(), choixActivity.class);
                                    startActivity(intent);
                                }
                            },500);
                        }else{
                            Toast.makeText(getApplicationContext(), "Mauvais mot de passe", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Vérifiez votre connexion internet", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public static class ConnexionInternet
    {
        public static boolean isConnectedInternet(Activity activity)
        {
            ConnectivityManager connectivityManager = (ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null)
            {
                NetworkInfo.State networkState = networkInfo.getState();
                if (networkState.compareTo(NetworkInfo.State.CONNECTED) == 0)
                {
                    return true;
                }
                else return false;
            }
            else return false;
        }
    }
}
