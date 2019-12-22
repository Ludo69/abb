package a.w.abb_mat.activity.histocomp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import a.w.abb_mat.R;
import a.w.abb_mat.model.HistoComp;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class histoCompAdapter extends RecyclerView.Adapter<a.w.abb_mat.activity.histocomp.histoCompAdapter.RecyclerViewAdapter> {

    private Context context;
    private List<HistoComp> histoComps;
    private a.w.abb_mat.activity.histocomp.histoCompAdapter.ItemClickListener itemClickListener;

    public histoCompAdapter(Context context, List<HistoComp> histoComps, a.w.abb_mat.activity.histocomp.histoCompAdapter.ItemClickListener itemClickListener) {
        this.context = context;
        this.histoComps = histoComps;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public a.w.abb_mat.activity.histocomp.histoCompAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_histocomp, parent, false);
        return new a.w.abb_mat.activity.histocomp.histoCompAdapter.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull a.w.abb_mat.activity.histocomp.histoCompAdapter.RecyclerViewAdapter holder, int position) {
        HistoComp histoComp = histoComps.get(position);
        holder.tv_dateent.setText(histoComp.getDateent());
        holder.tv_nbrgonf.setText(histoComp.getNbrgonflage());
        holder.tv_tpsmaj.setText(histoComp.getTpsgonflage());
        holder.tv_tpsmaj.setText(histoComp.getTpsmajoree());
        holder.tv_tpsmoy.setText(histoComp.getMoytemp());
    }

    @Override
    public int getItemCount() {
        return histoComps.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_dateent, tv_nbrgonf, tv_tpsgonf, tv_tpsmaj, tv_tpsmoy;
        CardView card_item;
        a.w.abb_mat.activity.histocomp.histoCompAdapter.ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemView, a.w.abb_mat.activity.histocomp.histoCompAdapter.ItemClickListener itemClickListener) {
            super(itemView);

            tv_dateent = itemView.findViewById(R.id.dateent);
            tv_nbrgonf = itemView.findViewById(R.id.nbrgonf);
            tv_tpsgonf = itemView.findViewById(R.id.tpsgonf);
            tv_tpsmaj = itemView.findViewById(R.id.tpsmaj);
            tv_tpsmoy = itemView.findViewById(R.id.tpsmoy);
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

