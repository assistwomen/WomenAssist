package com.orange.womenassist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FicheConseilAdapter extends RecyclerView.Adapter<FicheConseilAdapter.FicheConseilViewHolder> {

    Context context;
    List<FicheConseil> ficheConseilList;

    public FicheConseilAdapter(Context context, List<FicheConseil> ficheConseilList) {
        this.context = context;
        this.ficheConseilList = ficheConseilList;
    }

    @NonNull
    @Override
    public FicheConseilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(context).inflate(R.layout.item_fiche_conseil,parent,false);
         FicheConseilViewHolder holder = new FicheConseilViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FicheConseilViewHolder holder, int position) {
        holder.tv_nomFicheConseil.setText(ficheConseilList.get(position).getNomFiche());
        holder.tv_descriptionFicheConseil.setText(ficheConseilList.get(position).getDescriptionFiche());
    }

    @Override
    public int getItemCount() {
        return ficheConseilList.size();
    }

    public static class FicheConseilViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_nomFicheConseil;
        private TextView tv_descriptionFicheConseil;

        public FicheConseilViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_nomFicheConseil = itemView.findViewById(R.id.tv_fiche_name);
            tv_descriptionFicheConseil = itemView.findViewById(R.id.tv_descriptionFiche);
        }
    }
}
