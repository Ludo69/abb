package a.w.abb_mat.activity.sortie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import a.w.abb_mat.R;
import a.w.abb_mat.model.Sortie;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class SortieAdapter extends RecyclerView.Adapter<a.w.abb_mat.activity.sortie.SortieAdapter.RecyclerViewAdapter> {

    private Context context;
    private List<Sortie> sorties;
    private a.w.abb_mat.activity.sortie.SortieAdapter.ItemClickListener itemClickListener;

    public SortieAdapter(Context context, List<Sortie> sorties, a.w.abb_mat.activity.sortie.SortieAdapter.ItemClickListener itemClickListener) {
        this.context = context;
        this.sorties = sorties;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public a.w.abb_mat.activity.sortie.SortieAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sortie, parent, false);
        return new a.w.abb_mat.activity.sortie.SortieAdapter.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull a.w.abb_mat.activity.sortie.SortieAdapter.RecyclerViewAdapter holder, int position) {
        Sortie sortie = sorties.get(position);
        holder.tv_nomsortie.setText(sortie.getNomsortie());
        holder.tv_datesortie.setText(sortie.getDatesortie());
    }

    @Override
    public int getItemCount() {
        return sorties.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_nomsortie, tv_datesortie;
        CardView card_item;
        a.w.abb_mat.activity.sortie.SortieAdapter.ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemView, a.w.abb_mat.activity.sortie.SortieAdapter.ItemClickListener itemClickListener) {
            super(itemView);

            tv_nomsortie = itemView.findViewById(R.id.nomsortie);
            tv_datesortie = itemView.findViewById(R.id.datesortie);
            card_item = itemView.findViewById(R.id.card_item);

            this.itemClickListener = itemClickListener;
            card_item.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}


