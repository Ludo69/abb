package a.w.abb_mat.activity.choixEmprunt;

import a.w.abb_mat.R;
import a.w.abb_mat.activity.main.MainActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

public class choixEmpruntActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_emprunt);

        CardView cardviewStabs = findViewById(R.id.stabs);

        cardviewStabs.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }
}
