package a.w.abb_mat.activity.gonfleur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import a.w.abb_mat.R;
import a.w.abb_mat.model.Gonfleur;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class GonfleurAdapter extends RecyclerView.Adapter<a.w.abb_mat.activity.gonfleur.GonfleurAdapter.RecyclerViewAdapter> {

    private Context context;
    private List<Gonfleur> gonfleurs;
    private a.w.abb_mat.activity.gonfleur.GonfleurAdapter.ItemClickListener itemClickListener;

    public GonfleurAdapter(Context context, List<Gonfleur> gonfleurs, a.w.abb_mat.activity.gonfleur.GonfleurAdapter.ItemClickListener itemClickListener) {
        this.context = context;
        this.gonfleurs = gonfleurs;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public a.w.abb_mat.activity.gonfleur.GonfleurAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gonfleur, parent, false);
        return new a.w.abb_mat.activity.gonfleur.GonfleurAdapter.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull a.w.abb_mat.activity.gonfleur.GonfleurAdapter.RecyclerViewAdapter holder, int position) {
        Gonfleur gonfleur = gonfleurs.get(position);
        holder.tv_nom.setText(gonfleur.getNom());
    }

    @Override
    public int getItemCount() {
        return gonfleurs.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_nom;
        CardView card_item;
        a.w.abb_mat.activity.gonfleur.GonfleurAdapter.ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemView, a.w.abb_mat.activity.gonfleur.GonfleurAdapter.ItemClickListener itemClickListener) {
            super(itemView);

            tv_nom = itemView.findViewById(R.id.nomgonfleur);
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



