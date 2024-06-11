package tcp.last.view.h_home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

import tcp.last.R;
import tcp.last.model.Recipe;
import tcp.last.view.c_crea.CreaActivity;
import tcp.last.view.c_crea.CreaVerActivity;
import tcp.last.view.h_home.adapter.AdapterViewPager;

public class MisRecetasEditActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private List<Recipe> recipeList ;
    private AdapterViewPager viewPagerAdapter;
    private Recipe recipe;

    Button btvoler, btborrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_activity_mis_recetas_edit);

        viewPager = findViewById(R.id.viewPager2Recetas);

        Intent intent = getIntent();
        recipeList = intent.getParcelableArrayListExtra("recetasList");
        viewPagerAdapter = new AdapterViewPager(this, recipeList );
        viewPager.setAdapter(viewPagerAdapter);

    }
}