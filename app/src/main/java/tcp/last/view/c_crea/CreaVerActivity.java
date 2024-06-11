package tcp.last.view.c_crea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import tcp.last.R;
import tcp.last.model.Recipe;
import tcp.last.view.b_busca.BuscaActivity;
import tcp.last.view.b_busca.BuscaRandomActivity;
import tcp.last.view.m_menu.MenuActivity;

public class CreaVerActivity extends AppCompatActivity {
    TextView tit, rac, tiem, source, ins, sum, ing;
    ImageView imageView;
    Recipe r = new Recipe();
    Button back, save;
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_activity_crea_ver);

        tit = findViewById(R.id.titCrea2);
        rac = findViewById(R.id.racCrea2);
        tiem = findViewById(R.id.minCrea2);
        source = findViewById(R.id.sourceCrea2);
        ins = findViewById(R.id.pairCrea2);
        sum = findViewById(R.id.sumCrea2);
        ing = findViewById(R.id.ingCrea2);

        imageView = findViewById(R.id.imgCrea2);

        back = findViewById(R.id.buttonBackCrea2);
        save = findViewById(R.id.buttonSave);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreaActivity.class);
                startActivity(intent);
            }
        });


        Intent intent = getIntent();
        if (intent.hasExtra("tit")) {
            String ingredientes = getIntent().getStringExtra("ing");
            String instrucciones = getIntent().getStringExtra("ins");
            String raciones = getIntent().getStringExtra("rac");
            String minutos = getIntent().getStringExtra("min");
            String titulo = getIntent().getStringExtra("tit");
            String resumen = getIntent().getStringExtra("sum");
            String img = getIntent().getStringExtra("img");

            Picasso.get().load(img)
                    .fit()
                    .centerCrop()
                    .into(imageView);


            String id = FirebaseAuth.getInstance().getCurrentUser().getEmail();

            source.setText(id);
            tit.setText(titulo);
            ing.setText(ingredientes);
            ins.setText(instrucciones);
            tiem.setText(minutos);
            sum.setText(resumen);
            rac.setText(raciones);
// title,  servings,  readyInMinutes,  sourceName,  instructions,  summary,  ingrediente

            if(raciones.equals("")){
                r.setServings(0);
            } else {
                r.setServings(Integer.parseInt(raciones));
            }
            if(minutos.equals("")){
                r.setReadyInMinutes(0);
            } else {
                r.setReadyInMinutes(Integer.parseInt(minutos));

            }

            r.setTitle(titulo);
            r.setSourceName(id);
            r.setInstructions(instrucciones);
            r.setSummary(resumen);
            r.setIngredientes(ingredientes);

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    DatabaseReference recetasRef = database.getReference().child("recetas").child(id);
                    String recetaId = recetasRef.push().getKey();
                    recetasRef.child(recetaId).setValue(r).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(CreaVerActivity.this, "Receta guardada", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                            } else {
                                Toast.makeText(CreaVerActivity.this, "Error al guardar la receta", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        }
    }
}