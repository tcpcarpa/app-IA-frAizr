package tcp.last.view.p_perfil;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.squareup.picasso.Picasso;

import tcp.last.R;
import tcp.last.model.Usuario;
import tcp.last.view.m_menu.MenuActivity;

public class EditPerfilDataActivity extends AppCompatActivity {
    Button bt_edit, bt_cancel;
    FloatingActionButton uploadImage;
    EditText tname, tuser, tedad, ttelf, tfb, tins, ttwit;
    ImageView imageView;
    Uri uri;
    String img = "https://cdn.autobild.es/sites/navi.axelspringer.es/public/media/image/2016/09/569465-whatsapp-que-tus-contactos-ponen-rana-perfil.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_activity_perfildata_edit);

        bt_edit = findViewById(R.id.buttonProfileEdit);
        bt_cancel = findViewById(R.id.buttonProfileCancel);
        uploadImage = findViewById(R.id.fab);

        hook();
        //showData();

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            uri = data.getData();
                            uploadImage.setImageURI(uri);
                            img = uri.toString();
                        } else {
                            Toast.makeText(getApplicationContext(), "No has selecuionado la imagen", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        bt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // guardarDatosPerfil();
                startActivity(new Intent(getApplicationContext(), PerfilDataActivity.class));

            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
        });
    }
/*
    private void showData() {
    String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private DocumentReference perfilRef = FirebaseFirestore.getInstance().collection("usuario").document(userID);

        perfilRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String nombre = documentSnapshot.getString("nombre");
                            String usuario = documentSnapshot.getString("usuario");
                            String edad = String.valueOf(documentSnapshot.getLong("edad"));
                            String telefono = documentSnapshot.getString("telefono");
                            String facebook = documentSnapshot.getString("facebook");
                            String instagram = documentSnapshot.getString("instagram");
                            String twitter = documentSnapshot.getString("twitter");
                            String image = documentSnapshot.getString("iamgen");

                            tname.setText(nombre);
                            tuser.setText(usuario);
                            tedad.setText(String.valueOf(edad));
                            ttelf.setText(telefono);
                            tfb.setText(facebook);
                            tins.setText(instagram);
                            ttwit.setText(twitter);

                            Picasso.get().load(image)
                                    .fit()
                                    .centerCrop()
                                    .into(imageView);

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }

    private void guardarDatosPerfil() {
        String nombre = tname.getText().toString().trim();
        String edad = tedad.getText().toString().trim();
        String telefono = ttelf.getText().toString().trim();
        String facebook = tfb.getText().toString().trim();
        String instagram = tins.getText().toString().trim();
        String twitter = ttwit.getText().toString().trim();

//    public Usuario(String name, String birth, String telf, String face, String twit, String insta, String image) {

        Usuario perfil = new Usuario(nombre, edad, telefono, facebook, twitter, instagram, img);

        perfilRef.set(perfil)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("PerfilActivity", "Datos del perfil guardados correctamente.");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("PerfilActivity", "Error al guardar los datos del perfil: " + e.getMessage());
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        guardarDatosPerfil();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        guardarDatosPerfil();
    }
*/
    private void hook() {
        tname = findViewById(R.id.editTextTextPersonNam);
        tuser = findViewById(R.id.editTextTextPersonUsu);
        tedad = findViewById(R.id.editTextTextPersonEdad);
        ttelf = findViewById(R.id.editTextTextPersonTelf);
        tfb = findViewById(R.id.editTextTextPersonfb);
        tins = findViewById(R.id.editTextTextPersonIg);
        ttwit = findViewById(R.id.editTextTextPersonTwit);

        imageView = findViewById(R.id.imagePerfil);

    }
}