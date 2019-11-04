package a.w.abb_mat.activity.detendeurm;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import a.w.abb_mat.R;
import a.w.abb_mat.model.Detendeur;
import a.w.abb_mat.model.Stab;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class DetendeurMAdapter extends RecyclerView.Adapter<a.w.abb_mat.activity.detendeurm.DetendeurMAdapter.RecyclerViewAdapter> {

    private Context context;
    private List<Detendeur> detendeurs;
    private a.w.abb_mat.activity.detendeurm.DetendeurMAdapter.ItemClickListener itemClickListener;

    public DetendeurMAdapter(Context context, List<Detendeur> detendeurs, a.w.abb_mat.activity.detendeurm.DetendeurMAdapter.ItemClickListener itemClickListener) {
        this.context = context;
        this.detendeurs = detendeurs;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public a.w.abb_mat.activity.detendeurm.DetendeurMAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detendeur, parent, false);
        return new a.w.abb_mat.activity.detendeurm.DetendeurMAdapter.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull a.w.abb_mat.activity.detendeurm.DetendeurMAdapter.RecyclerViewAdapter holder, int position) {
        Detendeur detendeur = detendeurs.get(position);
        holder.tv_txtnumdetendeur.setText("Detendeur n° : "+detendeur.getNumdetendeur());
        holder.tv_txtcommentairedetendeur.setText(detendeur.getCommentairedetendeur());
        holder.tv_txtemprunteurdetendeur.setText(detendeur.getEmprunteurdetendeur());
        holder.tv_txtrevisiondetendeur.setText("Révisé le : "+detendeur.getRevisiondetendeur());
        if(detendeur.getDispodetendeur() == 1) {
            holder.tv_color.setBackgroundColor(Color.GREEN);
        } else {
            holder.tv_color.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return detendeurs.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_txtnumdetendeur, tv_txtcommentairedetendeur, tv_txtemprunteurdetendeur, tv_txtrevisiondetendeur;
        LinearLayout tv_color;
        CardView card_item;
        a.w.abb_mat.activity.detendeurm.DetendeurMAdapter.ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemView, a.w.abb_mat.activity.detendeurm.DetendeurMAdapter.ItemClickListener itemClickListener) {
            super(itemView);

            tv_txtnumdetendeur = itemView.findViewById(R.id.txtnumdetendeur);
            tv_txtcommentairedetendeur = itemView.findViewById(R.id.txtcommentairedetendeur);
            tv_txtemprunteurdetendeur = itemView.findViewById(R.id.txtemprunteurdetendeur);
            tv_txtrevisiondetendeur = itemView.findViewById(R.id.txtrevisiondetendeur);
            tv_color = itemView.findViewById(R.id.colordispo);
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






