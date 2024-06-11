package tcp.last.view.c_crea;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import tcp.last.R;
import tcp.last.model.Recipe;
import tcp.last.view.b_busca.BuscaActivity;
import tcp.last.view.b_busca.BuscaRandomActivity;

public class CreaActivity extends AppCompatActivity {
    private EditText ingRand, racRand, minRand, titRand, sumRand, insRand;
    Button buttonMas;
    Recipe r;
    ImageView imageView;
    FloatingActionButton fab;
    Uri uri;
    String img = "https://images.ecestaticos.com/MWLb9v9Lf33repBmyeLoY6lXuJA=/0x0:1254x836/1200x900/filters:fill(white):format(jpg)/f.elconfidencial.com%2Foriginal%2Fdbd%2F0bf%2Fcf6%2Fdbd0bfcf66e3dc6778dd2afade6f8604.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_activity_crea);

        ingRand = findViewById(R.id.ingCrea);
        racRand = findViewById(R.id.racCrea);
        minRand = findViewById(R.id.minCrea);
        titRand = findViewById(R.id.titCrea);
        sumRand = findViewById(R.id.sumCrea);
        insRand = findViewById(R.id.pairCrea);

        imageView = findViewById(R.id.imgCrea);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            uri = data.getData();
                            imageView.setImageURI(uri);
                            img = uri.toString();
                        } else {
                            Toast.makeText(CreaActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        fab = findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });


        buttonMas = findViewById(R.id.buttonMasCrea);
        buttonMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ingredientes = ingRand.getText().toString().trim();
                String instrucciones = insRand.getText().toString().trim();
                String raciones = racRand.getText().toString().trim();
                String minutos = minRand.getText().toString().trim();
                String titulo = titRand.getText().toString().trim();
                String resumen = sumRand.getText().toString().trim();

                Intent intent = new Intent(getApplicationContext(), CreaVerActivity.class);
                intent.putExtra("tit", titulo);
                intent.putExtra("min", minutos);
                intent.putExtra("rac", raciones);
                intent.putExtra("sum", resumen);
                intent.putExtra("ing", ingredientes);
                intent.putExtra("ins", instrucciones);
                intent.putExtra("img", img);
                startActivity(intent);
            }
        });
    }

}
