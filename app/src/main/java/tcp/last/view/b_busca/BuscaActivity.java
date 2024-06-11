package tcp.last.view.b_busca;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tcp.last.R;

public class BuscaActivity extends AppCompatActivity  {

    EditText n;
    Button nom, rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_activity_busca);

        nom = findViewById(R.id.btBuscaNom);
        rand = findViewById(R.id.btBuscaRand);

        n = findViewById(R.id.buscaNom);

        nom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = n.getText().toString().trim();
                if (nom != "") {
                    Intent intent = new Intent(getApplicationContext(), BuscaListActivity.class);
                    intent.putExtra("nom", nom);
                    startActivity(intent);
                } else {
                    Toast.makeText(BuscaActivity.this, "Intro unnombre", Toast.LENGTH_SHORT).show();
                }
            }
        });
        rand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BuscaRandomActivity.class);
                startActivity(intent);
            }
        });

    }

}