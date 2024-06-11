package tcp.last.view.h_home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import tcp.last.R;
import tcp.last.model.Recipe;
import tcp.last.view.h_home.adapter.AdapterMisrecetas;

public class MisRecetasActivity extends AppCompatActivity{
    private RecyclerView recyclerView;
    private AdapterMisrecetas recipesAdapter;
    private List<Recipe> recipeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_activity_mis_recetas);

        recyclerView = findViewById(R.id.recyclerMisrecetas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recipeList = new ArrayList<>();
        recipesAdapter = new AdapterMisrecetas(recipeList);
        recyclerView.setAdapter(recipesAdapter);

        loadRecipesFromFirebase();
    }

    private void loadRecipesFromFirebase() {
        FirebaseDatabase.getInstance().getReference("recetas")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        recipeList.clear();
                        for (DataSnapshot recipeSnapshot : dataSnapshot.getChildren()) {
                            Recipe recipe = recipeSnapshot.getValue(Recipe.class);
                            recipeList.add(recipe);
                        }
                        recipesAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d("RecipesActivity", "Failed to read recipes", databaseError.toException());
                    }
                });
    }
}