package a.w.abb_mat.activity.stabm;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import a.w.abb_mat.R;
import a.w.abb_mat.model.Stab;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class StabMAdapter extends RecyclerView.Adapter<a.w.abb_mat.activity.stabm.StabMAdapter.RecyclerViewAdapter> {

    private Context context;
    private List<Stab> stabs;
    private a.w.abb_mat.activity.stabm.StabMAdapter.ItemClickListener itemClickListener;

    public StabMAdapter(Context context, List<Stab> stabs, a.w.abb_mat.activity.stabm.StabMAdapter.ItemClickListener itemClickListener) {
        this.context = context;
        this.stabs = stabs;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public a.w.abb_mat.activity.stabm.StabMAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_stab, parent, false);
        return new a.w.abb_mat.activity.stabm.StabMAdapter.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull a.w.abb_mat.activity.stabm.StabMAdapter.RecyclerViewAdapter holder, int position) {
        Stab stab = stabs.get(position);
        holder.tv_txtnumstab.setText("Stab n° : "+stab.getNumstab());
        holder.tv_txtcommentairestab.setText(stab.getCommentairestab());
        holder.tv_txtemprunteurstab.setText(stab.getEmprunteurstab());
        holder.tv_txttaillestab.setText("Taille : "+stab.getTaillestab());
        if(stab.getDispostab() == 1) {
            holder.tv_color.setBackgroundColor(Color.GREEN);
        } else {
            holder.tv_color.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return stabs.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_txtnumstab, tv_txtcommentairestab, tv_txtemprunteurstab, tv_txttaillestab;
        LinearLayout tv_color;
        CardView card_item;
        a.w.abb_mat.activity.stabm.StabMAdapter.ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemView, a.w.abb_mat.activity.stabm.StabMAdapter.ItemClickListener itemClickListener) {
            super(itemView);

            tv_txtnumstab = itemView.findViewById(R.id.txtnumstab);
            tv_txtcommentairestab = itemView.findViewById(R.id.txtcommentairestab);
            tv_txtemprunteurstab = itemView.findViewById(R.id.txtemprunteurstab);
            tv_txttaillestab = itemView.findViewById(R.id.txttailleStab);
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





