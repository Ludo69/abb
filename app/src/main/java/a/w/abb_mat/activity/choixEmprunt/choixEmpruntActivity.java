package a.w.abb_mat.activity.choixEmprunt;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.detendeur.DetendeurActivity;
import a.w.abb_mat.activity.stab.StabActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class choixEmpruntActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_emprunt);
        Intent intent = getIntent();
        if(intent != null){
            String nomsortie = "";
            if (intent.hasExtra("nomsortie")){ // vérifie qu'une valeur est associée à la clé “edittext”
                nomsortie = intent.getStringExtra("nomsortie"); // on récupère la valeur associée à la clé
                Toast.makeText(this, nomsortie, Toast.LENGTH_SHORT).show();
            }
        }

        CardView cardviewStabs = findViewById(R.id.stabs);

        cardviewStabs.setOnClickListener(view -> {
            Intent intent2 = new Intent(getApplicationContext(), StabActivity.class);
            startActivity(intent2);
        });

        CardView cardviewDetendeurs = findViewById(R.id.detendeurs);

        cardviewDetendeurs.setOnClickListener(view -> {
            Intent intent2 = new Intent(getApplicationContext(), DetendeurActivity.class);
            startActivity(intent2);
        });
    }
}
