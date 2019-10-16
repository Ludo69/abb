package a.w.abb_mat.activity.gonflagebloc;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import a.w.abb_mat.R;
import a.w.abb_mat.model.Bloc;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class GonflageBlocAdapter extends RecyclerView.Adapter<a.w.abb_mat.activity.gonflagebloc.GonflageBlocAdapter.RecyclerViewAdapter> {

    private Context context;
    private List<Bloc> blocs;
    private a.w.abb_mat.activity.gonflagebloc.GonflageBlocAdapter.ItemClickListener itemClickListener;

    public GonflageBlocAdapter(Context context, List<Bloc> blocs, a.w.abb_mat.activity.gonflagebloc.GonflageBlocAdapter.ItemClickListener itemClickListener) {
        this.context = context;
        this.blocs = blocs;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public a.w.abb_mat.activity.gonflagebloc.GonflageBlocAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_blocg, parent, false);
        return new a.w.abb_mat.activity.gonflagebloc.GonflageBlocAdapter.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull a.w.abb_mat.activity.gonflagebloc.GonflageBlocAdapter.RecyclerViewAdapter holder, int position) {
        Bloc bloc = blocs.get(position);
        holder.tv_txtnumblocg.setText("Bloc n° : "+bloc.getNumbloc());
        holder.tv_litrageblocg.setText(bloc.getLitragebloc()+" L");
        holder.tv_txtpressionblocg.setText(String.valueOf(bloc.getPressionbloc()));
        if(bloc.getPressionbloc() <= 100){
            holder.tv_txtpressionblocg.setTextColor(Color.parseColor("#FE0000"));
        }else{
            holder.tv_txtpressionblocg.setTextColor(Color.parseColor("#00C635"));
        }
    }

    @Override
    public int getItemCount() {
        return blocs.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_txtnumblocg, tv_litrageblocg, tv_txtpressionblocg;
        LinearLayout tv_color;
        CardView card_item;
        a.w.abb_mat.activity.gonflagebloc.GonflageBlocAdapter.ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemView, a.w.abb_mat.activity.gonflagebloc.GonflageBlocAdapter.ItemClickListener itemClickListener) {
            super(itemView);

            tv_txtnumblocg = itemView.findViewById(R.id.txtnumblocg);
            tv_litrageblocg = itemView.findViewById(R.id.litrageblocg);
            tv_txtpressionblocg = itemView.findViewById(R.id.txtpressionblocg);
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
