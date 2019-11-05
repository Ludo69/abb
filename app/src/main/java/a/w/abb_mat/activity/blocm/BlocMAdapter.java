package a.w.abb_mat.activity.blocm;

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

public class BlocMAdapter extends RecyclerView.Adapter<a.w.abb_mat.activity.blocm.BlocMAdapter.RecyclerViewAdapter> {

    private Context context;
    private List<Bloc> blocs;
    private a.w.abb_mat.activity.blocm.BlocMAdapter.ItemClickListener itemClickListener;

    public BlocMAdapter(Context context, List<Bloc> blocs, a.w.abb_mat.activity.blocm.BlocMAdapter.ItemClickListener itemClickListener) {
        this.context = context;
        this.blocs = blocs;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public a.w.abb_mat.activity.blocm.BlocMAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bloc, parent, false);
        return new a.w.abb_mat.activity.blocm.BlocMAdapter.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull a.w.abb_mat.activity.blocm.BlocMAdapter.RecyclerViewAdapter holder, int position) {
        Bloc bloc = blocs.get(position);
        holder.tv_txtnumbloc.setText("Bloc n° : "+bloc.getNumbloc());
        holder.tv_litragebloc.setText(bloc.getLitragebloc()+ " L");
        holder.tv_txtcommentairebloc.setText(bloc.getCommentairebloc());
        holder.tv_txtemprunteurbloc.setText(bloc.getEmprunteurbloc());
        holder.tv_txtpressionbloc.setText(String.valueOf(bloc.getPressionbloc()));
        if(bloc.getPressionbloc() <= 100){
            holder.tv_txtpressionbloc.setTextColor(Color.parseColor("#FE0000"));
        }else{
            holder.tv_txtpressionbloc.setTextColor(Color.parseColor("#00C635"));
        }
        if(bloc.getDispobloc() == 1) {
            holder.tv_color.setBackgroundColor(Color.GREEN);
        } else {
            holder.tv_color.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return blocs.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_txtnumbloc, tv_litragebloc, tv_txtcommentairebloc, tv_txtemprunteurbloc, tv_txtpressionbloc;
        LinearLayout tv_color;
        CardView card_item;
        a.w.abb_mat.activity.blocm.BlocMAdapter.ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemView, a.w.abb_mat.activity.blocm.BlocMAdapter.ItemClickListener itemClickListener) {
            super(itemView);

            tv_txtnumbloc = itemView.findViewById(R.id.txtnumbloc);
            tv_litragebloc = itemView.findViewById(R.id.litragebloc);
            tv_txtcommentairebloc = itemView.findViewById(R.id.txtcommentairebloc);
            tv_txtemprunteurbloc = itemView.findViewById(R.id.txtemprunteurbloc);
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