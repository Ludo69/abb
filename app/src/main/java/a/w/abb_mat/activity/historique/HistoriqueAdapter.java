package a.w.abb_mat.activity.historique;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import a.w.abb_mat.R;
import a.w.abb_mat.model.Historique;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class HistoriqueAdapter extends RecyclerView.Adapter<a.w.abb_mat.activity.historique.HistoriqueAdapter.RecyclerViewAdapter> {

    private Context context;
    private List<Historique> historiques;
    private a.w.abb_mat.activity.historique.HistoriqueAdapter.ItemClickListener itemClickListener;

    public HistoriqueAdapter(Context context, List<Historique> historiques, a.w.abb_mat.activity.historique.HistoriqueAdapter.ItemClickListener itemClickListener) {
        this.context = context;
        this.historiques = historiques;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public a.w.abb_mat.activity.historique.HistoriqueAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_historique, parent, false);
        return new a.w.abb_mat.activity.historique.HistoriqueAdapter.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull a.w.abb_mat.activity.historique.HistoriqueAdapter.RecyclerViewAdapter holder, int position) {
        Historique historique = historiques.get(position);
        holder.tv_emprunteur.setText(historique.getEmprunteur());
        holder.tv_nummat.setText(historique.getNummat());
        holder.tv_dateemprunt.setText(historique.getDatepret());
        holder.tv_daterestitution.setText(historique.getDaterestitution());
        if(historique.getTypemat() == 0) {
            holder.tv_imagemat.setImageResource(R.drawable.stab);
        } else if(historique.getTypemat() == 1) {
            holder.tv_imagemat.setImageResource(R.drawable.detendeur);
        } else {
            holder.tv_imagemat.setImageResource(R.drawable.bloc_small);
        }
    }

    @Override
    public int getItemCount() {
        return historiques.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_emprunteur, tv_nummat, tv_dateemprunt, tv_daterestitution;
        ImageView tv_imagemat;
        CardView card_item;
        a.w.abb_mat.activity.historique.HistoriqueAdapter.ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemView, a.w.abb_mat.activity.historique.HistoriqueAdapter.ItemClickListener itemClickListener) {
            super(itemView);

            tv_emprunteur = itemView.findViewById(R.id.nomemprunteur);
            tv_nummat = itemView.findViewById(R.id.nummat);
            tv_dateemprunt = itemView.findViewById(R.id.dateemprunt);
            tv_daterestitution = itemView.findViewById(R.id.daterestitution);
            tv_imagemat = itemView.findViewById(R.id.imagemat);
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
