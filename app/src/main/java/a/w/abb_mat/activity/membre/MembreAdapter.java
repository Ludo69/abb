package a.w.abb_mat.activity.membre;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import a.w.abb_mat.R;
import a.w.abb_mat.model.Membre;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MembreAdapter extends RecyclerView.Adapter<a.w.abb_mat.activity.membre.MembreAdapter.RecyclerViewAdapter> {

    private Context context;
    private List<Membre> membres;
    private a.w.abb_mat.activity.membre.MembreAdapter.ItemClickListener itemClickListener;

    public MembreAdapter(Context context, List<Membre> membres, a.w.abb_mat.activity.membre.MembreAdapter.ItemClickListener itemClickListener) {
        this.context = context;
        this.membres = membres;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public a.w.abb_mat.activity.membre.MembreAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_membre, parent, false);
        return new a.w.abb_mat.activity.membre.MembreAdapter.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull a.w.abb_mat.activity.membre.MembreAdapter.RecyclerViewAdapter holder, int position) {
        Membre membre = membres.get(position);
        holder.tv_nommembre.setText(membre.getNommembres());
    }

    @Override
    public int getItemCount() {
        return membres.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_nommembre;
        CardView card_item;
        a.w.abb_mat.activity.membre.MembreAdapter.ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemView, a.w.abb_mat.activity.membre.MembreAdapter.ItemClickListener itemClickListener) {
            super(itemView);

            tv_nommembre = itemView.findViewById(R.id.nommembre);
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


