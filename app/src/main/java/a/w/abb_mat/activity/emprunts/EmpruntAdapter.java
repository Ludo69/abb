package a.w.abb_mat.activity.emprunts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import a.w.abb_mat.R;
import a.w.abb_mat.model.Emprunt;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class EmpruntAdapter extends RecyclerView.Adapter<a.w.abb_mat.activity.emprunts.EmpruntAdapter.RecyclerViewAdapter> {

    private Context context;
    private List<Emprunt> emprunts;
    private a.w.abb_mat.activity.emprunts.EmpruntAdapter.ItemClickListener itemClickListener;

    public EmpruntAdapter(Context context, List<Emprunt> emprunts, a.w.abb_mat.activity.emprunts.EmpruntAdapter.ItemClickListener itemClickListener) {
        this.context = context;
        this.emprunts = emprunts;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public a.w.abb_mat.activity.emprunts.EmpruntAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_emprunt, parent, false);
        return new a.w.abb_mat.activity.emprunts.EmpruntAdapter.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull a.w.abb_mat.activity.emprunts.EmpruntAdapter.RecyclerViewAdapter holder, int position) {
        Emprunt emprunt = emprunts.get(position);
        //holder.tv_typeemprunt.setText(emprunt.getTypeemprunt());
        holder.tv_numtype.setText(emprunt.getNumtype());
        holder.tv_emprunteur.setText(emprunt.getEmprunteur());
        holder.tv_dateemprunt.setText(emprunt.getDateemprunt());
    }

    @Override
    public int getItemCount() {
        return emprunts.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_typeemprunt, tv_numtype, tv_emprunteur, tv_dateemprunt;
        CardView card_item;
        a.w.abb_mat.activity.emprunts.EmpruntAdapter.ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemView, a.w.abb_mat.activity.emprunts.EmpruntAdapter.ItemClickListener itemClickListener) {
            super(itemView);

//            tv_typeemprunt = itemView.findViewById(R.id.typeemprunt);
            tv_numtype = itemView.findViewById(R.id.numtype);
            tv_emprunteur = itemView.findViewById(R.id.emprunteur);
            tv_dateemprunt = itemView.findViewById(R.id.dateemprunt);
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


