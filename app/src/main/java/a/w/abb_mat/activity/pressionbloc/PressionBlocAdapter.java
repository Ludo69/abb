package a.w.abb_mat.activity.pressionbloc;

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

public class PressionBlocAdapter extends RecyclerView.Adapter<a.w.abb_mat.activity.pressionbloc.PressionBlocAdapter.RecyclerViewAdapter> {

    private Context context;
    private List<Bloc> blocs;
    private a.w.abb_mat.activity.pressionbloc.PressionBlocAdapter.ItemClickListener itemClickListener;

    public PressionBlocAdapter(Context context, List<Bloc> blocs, a.w.abb_mat.activity.pressionbloc.PressionBlocAdapter.ItemClickListener itemClickListener) {
        this.context = context;
        this.blocs = blocs;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public a.w.abb_mat.activity.pressionbloc.PressionBlocAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pressionbloc, parent, false);
        return new a.w.abb_mat.activity.pressionbloc.PressionBlocAdapter.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull a.w.abb_mat.activity.pressionbloc.PressionBlocAdapter.RecyclerViewAdapter holder, int position) {
        Bloc bloc = blocs.get(position);
        holder.tv_txtnumpressionbloc.setText("Bloc n° : "+bloc.getNumbloc());
        holder.tv_litragepressionbloc.setText(bloc.getLitragebloc()+" L");
        holder.tv_txtpressionbloc.setText(String.valueOf(bloc.getPressionbloc()));
        if(bloc.getPressionbloc() <= 100){
            holder.tv_txtpressionbloc.setTextColor(Color.parseColor("#FE0000"));
        }else{
            holder.tv_txtpressionbloc.setTextColor(Color.parseColor("#00C635"));
        }
    }

    @Override
    public int getItemCount() {
        return blocs.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_txtnumpressionbloc, tv_litragepressionbloc, tv_txtpressionbloc;
        LinearLayout tv_color;
        CardView card_item;
        a.w.abb_mat.activity.pressionbloc.PressionBlocAdapter.ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemView, a.w.abb_mat.activity.pressionbloc.PressionBlocAdapter.ItemClickListener itemClickListener) {
            super(itemView);

            tv_txtnumpressionbloc = itemView.findViewById(R.id.txtnumpressionbloc);
            tv_litragepressionbloc = itemView.findViewById(R.id.litragepressionbloc);
            tv_txtpressionbloc = itemView.findViewById(R.id.txtpressionbloc);
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

