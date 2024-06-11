package tcp.last.view.b_busca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import tcp.last.R;
import tcp.last.model.Recipe;
import tcp.last.view.b_busca.adapter.AdapterRow;

public class BuscaListActivity extends AppCompatActivity {

    String apiKey = "24c3c41028564ad0a6aa0df8ed938325";
    RecyclerView recyclerView;
    AdapterRow adapter;
    ArrayList<Recipe> recipeArrayList = new ArrayList<>();
    private static String JSON_URL = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_activity_busca_list);

        recyclerView = findViewById(R.id.recBusca);

        if (getIntent().hasExtra("nom")) ;
        String nom = getIntent().getStringExtra("nom");

        obtenerRecetas(nom);

    }

    private void obtenerRecetas(String nom) {
        JSON_URL = "https://api.spoonacular.com/recipes/search?apiKey=24c3c41028564ad0a6aa0df8ed938325&query=" + nom;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                JSON_URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray resultsArray = response.getJSONArray("results");

                            for (int i = 0; i < resultsArray.length(); i++) {
                                JSONObject resultObject = resultsArray.getJSONObject(i);
                                Recipe r = new Recipe(); //id, title, image, rac, tie);

                                r.setId(resultObject.getInt("id"));
                                r.setTitle(resultObject.getString("title"));
                                r.setServings(resultObject.getInt("servings"));
                                r.setReadyInMinutes(resultObject.getInt("readyInMinutes"));

                                int id = resultObject.getInt("id");
                                String image = "https://spoonacular.com/recipeImages/" + id + "-636x393.jpg";
                                r.setImage(image);

                                recipeArrayList.add(r);
                            }

                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            adapter = new AdapterRow(getApplicationContext(), recipeArrayList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(this).add(request);
    }
}