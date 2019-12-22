package a.w.abb_mat.activity.choixcompresseur;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.histocomp.histoCompActivity;
import a.w.abb_mat.activity.stabm.StabMActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

public class choixCompresseurActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_compresseur);
        Intent intent = getIntent();

        CardView cardviewHistoComp = findViewById(R.id.histocomp);

        cardviewHistoComp.setOnClickListener(view -> {
            //Toast.makeText(this, "En cours de Dev", Toast.LENGTH_SHORT).show();
            Intent intent2 = new Intent(getApplicationContext(), histoCompActivity.class);
            startActivity(intent2);
            //finish();
        });
    }
}
