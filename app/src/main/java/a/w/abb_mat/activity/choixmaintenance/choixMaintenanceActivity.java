package a.w.abb_mat.activity.choixmaintenance;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.bloc.BlocActivity;
import a.w.abb_mat.activity.detendeur.DetendeurActivity;
import a.w.abb_mat.activity.stab.StabActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class choixMaintenanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_maintenance);
        Intent intent = getIntent();
        if(intent != null){
            String nomsortie = "";
            if (intent.hasExtra("nomsortie")){ // vérifie qu'une valeur est associée à la clé “edittext”
                nomsortie = intent.getStringExtra("nomsortie"); // on récupère la valeur associée à la clé
                Toast.makeText(this, nomsortie, Toast.LENGTH_SHORT).show();
            }
        }

        CardView cardviewStabsMaintenance = findViewById(R.id.stabsmaintenance);

        cardviewStabsMaintenance.setOnClickListener(view -> {
            Toast.makeText(this, "En cours de Dev", Toast.LENGTH_SHORT).show();
            //Intent intent2 = new Intent(getApplicationContext(), StabActivity.class);
            //startActivity(intent2);
        });

        CardView cardviewDetendeursMaintenance = findViewById(R.id.detendeursmaintenance);

        cardviewDetendeursMaintenance.setOnClickListener(view -> {
            Toast.makeText(this, "En cours de Dev", Toast.LENGTH_SHORT).show();
            //Intent intent2 = new Intent(getApplicationContext(), DetendeurActivity.class);
            //startActivity(intent2);
        });

        CardView cardviewBlocsMaintenance = findViewById(R.id.blocsmaintenance);

        cardviewBlocsMaintenance.setOnClickListener(view -> {
            Toast.makeText(this, "En cours de Dev", Toast.LENGTH_SHORT).show();
            //Intent intent2 = new Intent(getApplicationContext(), BlocActivity.class);
            //startActivity(intent2);
        });
    }
}