package a.w.abb_mat.activity.choix;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.choixEmprunt.choixEmpruntActivity;
import a.w.abb_mat.activity.pressionbloc.PressionBlocActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class choixActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix);

        CardView cardviewEmprunt = findViewById(R.id.emprunts);
        CardView cardviewPression = findViewById(R.id.pression);
        CardView cardviewGonflage = findViewById(R.id.gonflage);
        CardView cardviewHistorique = findViewById(R.id.historique);
        CardView cardviewMaintenance = findViewById(R.id.maintenance);

        cardviewEmprunt.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), choixEmpruntActivity.class);
            startActivity(intent);
        });

        cardviewPression.setOnClickListener(view -> {
            //Toast.makeText(getApplicationContext(), "En cours de développement...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), PressionBlocActivity.class);
            startActivity(intent);
        });

        cardviewGonflage.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "En cours de développement...", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(getApplicationContext(), choixEmpruntActivity.class);
            //startActivity(intent);
        });

        cardviewHistorique.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "En cours de développement...", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(getApplicationContext(), choixEmpruntActivity.class);
            //startActivity(intent);
        });

        cardviewMaintenance.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "En cours de développement...", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(getApplicationContext(), choixEmpruntActivity.class);
            //startActivity(intent);
        });
    }
}
