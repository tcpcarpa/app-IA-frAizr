package tcp.last.view.p_perfil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import tcp.last.R;
import tcp.last.model.Usuario;

public class PerfilDataActivity extends AppCompatActivity {
    private Button bt_edit;
    ImageView img;
    private TextView tname,tuser,tedad,ttelf,tfb,tins,ttwit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_activity_perfildata);

        hook();
       // showData();

        bt_edit = findViewById(R.id.buttonProfileEdit);

        bt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PerfilDataActivity.this, EditPerfilDataActivity.class));

            }
        });
    }
    /*
    public void showData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference perfilRef = database.getReference().child("usuario");
        perfilRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String nombre = dataSnapshot.child("nombre").getValue(String.class);
                    String usuario = dataSnapshot.child("usuaario").getValue(String.class);
                    String edad = dataSnapshot.child("edad").getValue(String.class);
                    String telf = dataSnapshot.child("telefono").getValue(String.class);
                    String face = dataSnapshot.child("facebook").getValue(String.class);
                    String ins = dataSnapshot.child("instagram").getValue(String.class);
                    String twti = dataSnapshot.child("twitter").getValue(String.class);

                    tname.setText(nombre);
                    tuser.setText(usuario);
                    tedad.setText(edad);
                    ttelf.setText(telf);
                    tfb.setText(face);
                    tins.setText(ins);
                    ttwit.setText(twti);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("PerfilActivity", "Error al obtener los datos del perfil: " + databaseError.getMessage());
            }
        });
    }
*/
    private void hook() {
        tname = findViewById(R.id.editTextTextPersonUsu);
        tuser = findViewById(R.id.editTextTextPersonNam);
        tedad = findViewById(R.id.editTextTextPersonEdad);
        ttelf = findViewById(R.id.editTextTextPersonTelf);
        tfb = findViewById(R.id.editTextTextPersonfb);
        tins = findViewById(R.id.editTextTextPersonIg);
        ttwit = findViewById(R.id.editTextTextPersonTwit);
    }

}