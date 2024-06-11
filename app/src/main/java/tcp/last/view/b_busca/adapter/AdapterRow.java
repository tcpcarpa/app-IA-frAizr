package tcp.last.view.b_busca.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tcp.last.R;
import tcp.last.model.Recipe;
import tcp.last.view.b_busca.BuscaDetailActivity;

public class AdapterRow extends RecyclerView.Adapter<AdapterRow.MyViewHolder> {
    private Context mContext;
    private ArrayList<Recipe> recipeList;

    public AdapterRow(Context mContext, ArrayList<Recipe> recipeList) {
        this.mContext = mContext;
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.y_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.tit.setText(recipeList.get(position).getTitle());

        Picasso.get()
                .load(recipeList.get(position).getImage())
                .resize(636, 393)
                .into(holder.img);


       holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BuscaDetailActivity.class);
                intent.putExtra("id", recipe.getId());
                intent.putExtra("recipeTitle", recipe.getTitle());
                intent.putExtra("recipeRaciones", recipe.getServings());
                intent.putExtra("recipeTiempo", recipe.getReadyInMinutes());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tit;
        private ImageView img;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tit = itemView.findViewById(R.id.titRow);
            img = itemView.findViewById(R.id.imgRow);
           constraintLayout = itemView.findViewById(R.id.consRow);
        }




    }
}
