package tcp.last.view.b_busca;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import tcp.last.R;
import tcp.last.model.Recipe;
import tcp.last.view.m_menu.MenuActivity;
import tcp.last.view.s_start.main.MainActivity;


public class BuscaRandomActivity extends AppCompatActivity {
    ImageView imageView;
    TextView tit, tiem, rac, ins, source, sum, ing;

    String title, summary, sourceUrl, sourceName, instructions, originalIngredients, image;
    int servings, readyInMinutes;
    Button volver, desc;
    Recipe r = new Recipe();

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_activity_busca_random);

        tit = findViewById(R.id.titRand);
        tiem = findViewById(R.id.minRand);
        rac = findViewById(R.id.racRand);
        sum = findViewById(R.id.sumRand);
        ins = findViewById(R.id.pairRand);
        source = findViewById(R.id.sourceRand);
        ing = findViewById(R.id.ingRand);

        imageView = findViewById(R.id.imgRand);

        volver = findViewById(R.id.buttonVolver);
        desc = findViewById(R.id.buttonMasRand);

        String JSON_URL = "https://api.spoonacular.com/recipes/random?apiKey=24c3c41028564ad0a6aa0df8ed938325";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, JSON_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray recipes = response.getJSONArray("recipes");
                            if (recipes.length() > 0) {
                                JSONObject recipe = recipes.getJSONObject(0);

                                String title = recipe.getString("title");
                                int servings = recipe.getInt("servings");
                                int readyInMinutes = recipe.getInt("readyInMinutes");
                                String sourceName = recipe.getString("sourceName");
                                String instructions = recipe.getString("instructions");
                                String summary = recipe.getString("summary");
                                String image = recipe.getString("image");

                                JSONArray extendedIngredients = recipe.getJSONArray("extendedIngredients");
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

                                r.setTitle(title);
                                r.setServings(servings);
                                r.setReadyInMinutes(readyInMinutes);
                                r.setSourceName(sourceName);
                                r.setInstructions(instructions);
                                r.setSummary(summary);
                                r.setIngredientes(originalIngredients);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("RandomActivity", "Error: " + error.getMessage());
                    }
                });

        Volley.newRequestQueue(this).add(request);
//-------------------------------------------------------------------------------------------------------
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BuscaActivity.class);
                startActivity(intent);
            }
        });
        desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DatabaseReference recetasRef = database.getReference().child("recetas").child(id);
                String recetaId = recetasRef.push().getKey();
                recetasRef.child(recetaId).setValue(r).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(BuscaRandomActivity.this, "Receta guardada", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                                } else {
                                    Toast.makeText(BuscaRandomActivity.this, "Error al guardar la receta", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}