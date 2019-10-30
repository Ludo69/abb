package a.w.abb_mat.activity.choixgonflage;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.bloc.BlocActivity;
import a.w.abb_mat.activity.detendeur.DetendeurActivity;
import a.w.abb_mat.activity.gestionGonflage.GestionGonflageActivity;
import a.w.abb_mat.activity.gonfleur.GonfleurActivity;
import a.w.abb_mat.activity.listegonflage.ListeGonflageActivity;
import a.w.abb_mat.activity.stab.StabActivity;
import a.w.abb_mat.activity.stats.StatsActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ChoixGonflageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_gonflage);
        Intent intent = getIntent();

        CardView cardviewAjoutgonflage = findViewById(R.id.ajoutgonflage);

        cardviewAjoutgonflage.setOnClickListener(view -> {
            Intent intent2 = new Intent(getApplicationContext(), GonfleurActivity.class);
            startActivity(intent2);
        });

        CardView cardviewListegonflage = findViewById(R.id.listegonflage);

        cardviewListegonflage.setOnClickListener(view -> {
            Intent intent2 = new Intent(getApplicationContext(), ListeGonflageActivity.class);
            startActivity(intent2);
        });

        CardView cardviewStatistiques = findViewById(R.id.statistiques);

        cardviewStatistiques.setOnClickListener(view -> {
            Intent intent2 = new Intent(getApplicationContext(), StatsActivity.class);
            startActivity(intent2);
        });

    }
}
