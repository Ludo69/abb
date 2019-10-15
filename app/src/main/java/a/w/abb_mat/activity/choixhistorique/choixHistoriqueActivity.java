package a.w.abb_mat.activity.choixhistorique;

import a.w.abb_mat.R;

import a.w.abb_mat.activity.historique.HistoriqueActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;


public class choixHistoriqueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_historique);
        Intent intent = getIntent();

        CardView cardviewEnsemble = findViewById(R.id.suiviensemble);
        CardView cardviewStabs = findViewById(R.id.suivistabs);
        CardView cardviewDetendeurs = findViewById(R.id.suividetendeurs);
        CardView cardviewBlocs = findViewById(R.id.suiviblocs);

        cardviewEnsemble.setOnClickListener(view -> {
            Intent intent2 = new Intent(getApplicationContext(), HistoriqueActivity.class);
            startActivity(intent2);
        });

        cardviewStabs.setOnClickListener(view -> {
            Intent intent2 = new Intent(getApplicationContext(), HistoriqueActivity.class);
            startActivity(intent2);
        });


        cardviewDetendeurs.setOnClickListener(view -> {
            Intent intent2 = new Intent(getApplicationContext(), HistoriqueActivity.class);
            startActivity(intent2);
        });


        cardviewBlocs.setOnClickListener(view -> {
            Intent intent2 = new Intent(getApplicationContext(), HistoriqueActivity.class);
            startActivity(intent2);
        });
    }
}

