package tcp.last.view.b_busca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import tcp.last.R;
import tcp.last.model.Ingrediente;
import tcp.last.model.Recipe;

public class BuscaDetailActivity extends AppCompatActivity {
    TextView tit, ti, rac;
    ImageView imageView;

    Button bt;
    String imagen, idstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_activity_busca_detail);

        imageView = findViewById(R.id.imgDetail);
        tit = findViewById(R.id.titDetail);
        ti = findViewById(R.id.tiempoDetail);
        rac = findViewById(R.id.numDetail);

        bt = findViewById(R.id.btMasDetail);

        int id = getIntent().getIntExtra("id", 0);
        String recipeTitle = getIntent().getStringExtra("recipeTitle");
        int recipeRaciones = getIntent().getIntExtra("recipeRaciones", 0);
        int recipeTiempo = getIntent().getIntExtra("recipeTiempo", 0);

        if (recipeTitle != null) {
            tit.setText(recipeTitle);
            ti.setText(String.valueOf(recipeTiempo));
            rac.setText(String.valueOf(recipeRaciones));
            idstr = String.valueOf(id);
            imagen = "https://spoonacular.com/recipeImages/" + idstr + "-636x393.jpg";

            Picasso.get().load(imagen)
                    .fit()
                    .centerCrop()
                    .into(imageView);

        }

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BuscaExtendedActivity.class);
                intent.putExtra("idstr", idstr);
                startActivity(intent);
            }
        });


    }

}
