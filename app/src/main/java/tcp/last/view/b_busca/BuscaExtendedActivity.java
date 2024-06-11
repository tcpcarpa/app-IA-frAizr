package tcp.last.view.b_busca;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import tcp.last.R;
import tcp.last.model.Recipe;
import tcp.last.view.m_menu.MenuActivity;
import tcp.last.view.s_start.main.MainActivity;


public class BuscaExtendedActivity extends AppCompatActivity {
    ImageView imageView;
    TextView tit, tiem, rac, ins, source, sum, ing;
    Button mas, atras, url;
    String enlace;
    ArrayList<Recipe> recipeArrayList;
    Recipe r = new Recipe();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_activity_busca_extended);

        tit = findViewById(R.id.titExt);
        tiem = findViewById(R.id.minExt);
        rac = findViewById(R.id.racExt);
        sum = findViewById(R.id.sumExt);
        ins = findViewById(R.id.pairExt);
        source = findViewById(R.id.sourceExt);
        ing = findViewById(R.id.ingExt);

        imageView = findViewById(R.id.imgExt);

        atras = findViewById(R.id.buttonBack);
        mas = findViewById(R.id.buttonMasRand);
        url = findViewById(R.id.buttonOK);


        String id = getIntent().getStringExtra("idstr");

        // https://api.spoonacular.com/recipes/545696/information?apiKey=24c3c41028564ad0a6aa0df8ed938325;

        String JSON_URL = "https://api.spoonacular.com/recipes/" + id + "/information?apiKey=24c3c41028564ad0a6aa0df8ed938325";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, JSON_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String title = response.getString("title");
                            String sourceUrl = response.getString("sourceUrl");
                            String sourceName = response.getString("sourceName");
                            String summary = response.getString("summary");
                            String instructions = response.getString("instructions");
                            int servings = response.getInt("servings");
                            int readyInMinutes = response.getInt("readyInMinutes");
                            String image = response.getString("image");


                            JSONArray extendedIngredients = response.getJSONArray("extendedIngredients");
                            StringBuilder ingredientsBuilder = new StringBuilder();
                            for (int i = 0; i < extendedIngredients.length(); i++) {
                                JSONObject ingredient = extendedIngredients.getJSONObject(i);
                                String original = ingredient.getString("original");
                                ingredientsBuilder.append(original).append("\n");
                            }
                            String originalIngredients = ingredientsBuilder.toString();

                            tit.setText(title);
                            rac.setText(String.valueOf(servings));
                            tiem.setText(String.valueOf(readyInMinutes));
                            source.setText(sourceName);
                            ins.setText(instructions);
                            sum.setText(summary);
                            ing.setText(originalIngredients);

                            Picasso.get().load(image)
                                    .fit()
                                    .centerCrop()
                                    .into(imageView);

                            enlace = sourceUrl;

                            r.setTitle(title);
                            r.setServings(servings);
                            r.setReadyInMinutes(readyInMinutes);
                            r.setSourceName(sourceName);
                            r.setInstructions(instructions);
                            r.setSummary(summary);
                            r.setIngredientes(originalIngredients);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("extended", "Error: " + error.getMessage());
                    }
                });

        Volley.newRequestQueue(this).add(request);

//-------------------------------------------------------------------------------------------------

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BuscaActivity.class);
                startActivity(intent);
            }
        });

        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(enlace));
                startActivity(intent);
            }
        });

        mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DatabaseReference recetasRef = database.getReference().child("recetas").child(id);
                String recetaId = recetasRef.child(currentUser.getUid()).push().getKey();
                if (currentUser != null) {
                    recetasRef.child(currentUser.getUid()).child(recetaId).setValue(r).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(BuscaExtendedActivity.this, "Receta guardada", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), MenuActivity.class));

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(BuscaExtendedActivity.this, "Error al guardar la receta", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(BuscaExtendedActivity.this, "Debes iniciar sesión para guardar la receta", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}









                /*
                recetasRef.child(recetaId).setValue(r).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(BuscaExtendedActivity.this, "Receta guardada", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), BuscaActivity.class));
                        } else {
                            Toast.makeText(BuscaExtendedActivity.this, "Error al guardar la receta", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

}
*/