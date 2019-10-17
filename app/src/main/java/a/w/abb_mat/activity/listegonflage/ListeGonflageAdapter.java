package a.w.abb_mat.activity.listegonflage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import a.w.abb_mat.R;
import a.w.abb_mat.model.Gonflage;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ListeGonflageAdapter extends RecyclerView.Adapter<a.w.abb_mat.activity.listegonflage.ListeGonflageAdapter.RecyclerViewAdapter> {

    private Context context;
    private List<Gonflage> gonflages;
    private a.w.abb_mat.activity.listegonflage.ListeGonflageAdapter.ItemClickListener itemClickListener;

    public ListeGonflageAdapter(Context context, List<Gonflage> gonflages, a.w.abb_mat.activity.listegonflage.ListeGonflageAdapter.ItemClickListener itemClickListener) {
        this.context = context;
        this.gonflages = gonflages;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public a.w.abb_mat.activity.listegonflage.ListeGonflageAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gonflage, parent, false);
        return new a.w.abb_mat.activity.listegonflage.ListeGonflageAdapter.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull a.w.abb_mat.activity.listegonflage.ListeGonflageAdapter.RecyclerViewAdapter holder, int position) {
        Gonflage gonflage = gonflages.get(position);
        holder.tv_nomgonfleur.setText(gonflage.getGonfleur());
        holder.tv_dategonflage.setText(gonflage.getDategonflage());
        holder.tv_pressionfinale.setText(gonflage.getPressionfinale());
    }

    @Override
    public int getItemCount() {
        return gonflages.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_nomgonfleur, tv_dategonflage, tv_pressionfinale;
        CardView card_item;
        a.w.abb_mat.activity.listegonflage.ListeGonflageAdapter.ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemView, a.w.abb_mat.activity.listegonflage.ListeGonflageAdapter.ItemClickListener itemClickListener) {
            super(itemView);

            tv_nomgonfleur = itemView.findViewById(R.id.nomgonfleur);
            tv_dategonflage = itemView.findViewById(R.id.dategonflage);
            tv_pressionfinale = itemView.findViewById(R.id.pressionfinale);
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
