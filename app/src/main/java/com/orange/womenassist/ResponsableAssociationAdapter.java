package com.orange.womenassist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResponsableAssociationAdapter extends RecyclerView.Adapter<ResponsableAssociationAdapter.ResponsableAssociationViewHolder>{

    Context context;
    List<ResponsableAssociation> responsableAssociationList;

    public ResponsableAssociationAdapter(Context context, List<ResponsableAssociation> responsableAssociationList) {
        this.context = context;
        this.responsableAssociationList = responsableAssociationList;
    }

    @NonNull
    @Override
    public ResponsableAssociationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_responsable_association,parent,false);
        ResponsableAssociationViewHolder holder = new ResponsableAssociationViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ResponsableAssociationViewHolder holder, int position) {
        holder.tv_nomRespAssociation.setText(responsableAssociationList.get(position).getNomRespAssociation());
        holder.img.setImageResource(responsableAssociationList.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return responsableAssociationList.size();
    }

    public static class ResponsableAssociationViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_nomRespAssociation;
        private ImageView img;

        public ResponsableAssociationViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_nomRespAssociation = itemView.findViewById(R.id.tv_respAssociation_name);
            img = itemView.findViewById(R.id.img_respAssociation);

        }
    }
}
