package tcp.last.view.h_home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import tcp.last.R;
import tcp.last.model.Recipe;

public class AdapterViewPager extends RecyclerView.Adapter<AdapterViewPager.ViewHolder> {
    private Context context;
    private List<Recipe> recipes;

    public AdapterViewPager(Context context, List<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.y_row_pager, parent, false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewPager.ViewHolder holder, int position) {
        Recipe r = recipes.get(position);
        holder.bind(r);
    }
    /*
        holder.tit.setText(r.getTitle());
        holder.rac.setText(r.getServings());
        holder.by.setText(r.getSourceName());
        holder.tiempo.setText(r.getReadyInMinutes());
        holder.ins.setText(r.getInstructions());
        holder.sum.setText(r.getSummary());
        holder.ing.setText(r.getIngredientes());

        Picasso.get().load(r.getImage())
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }
*/
    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tit, by, rac, tiempo, ins, ing, sum;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tit = itemView.findViewById(R.id.titGranRow);
            by = itemView.findViewById(R.id.byGranRow);
            tiempo = itemView.findViewById(R.id.tiempoGranRow);
            rac = itemView.findViewById(R.id.racGranRow);
            ins = itemView.findViewById(R.id.insGranRow);
            ing = itemView.findViewById(R.id.oriGranRow);
            sum = itemView.findViewById(R.id.sumGranrow);

            imageView = itemView.findViewById(R.id.imgGranRow);
        }
        public void bind(Recipe receta) {
            tit.setText(receta.getTitle());
            by.setText(receta.getSourceName());
            tiempo.setText(receta.getReadyInMinutes());
            rac.setText(receta.getServings());
            ins.setText(receta.getInstructions());
            ing.setText(receta.getIngredientes());
            sum.setText(receta.getSummary());
            Picasso.get()
                    .load(receta.getImage())
                    .placeholder(R.drawable.s_refres_b)
                    .error(R.drawable.s_about_b) //
                    .fit()
                    .centerCrop()
                    .into(imageView);
        }
    }
}