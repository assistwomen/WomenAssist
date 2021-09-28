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

public class ListeAssociationAdapter extends RecyclerView.Adapter<ListeAssociationAdapter.AssociationListViewHolder>{

    Context context;
    List<ListeAssociation> associationList;

    private OnRecyclerViewClickListener listener;


    public interface OnRecyclerViewClickListener{
        void OnItemClick(int position);
    }

    public void OnRecyclerViewClickListener (OnRecyclerViewClickListener listener){
        this.listener = listener;
    }

    public ListeAssociationAdapter(Context context, List<ListeAssociation> associationList) {
        this.context = context;
        this.associationList = associationList;
    }

    @NonNull
    @Override
    public AssociationListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_association,parent,false);
        AssociationListViewHolder holder = new AssociationListViewHolder(view, listener);
        return holder ;
    }

    @Override
    public void onBindViewHolder(@NonNull AssociationListViewHolder holder, int position) {

        holder.tv_nomAssociation.setText(associationList.get(position).getNomAssociation());
        holder.tv_descriptionAssociation.setText(associationList.get(position).getDescriptionAssociation());
        holder.img_association.setImageResource(associationList.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return associationList.size();
    }

    public static class AssociationListViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_nomAssociation;
        private TextView tv_descriptionAssociation;
        private ImageView img_association;

        public AssociationListViewHolder(@NonNull View itemView, OnRecyclerViewClickListener listener) {
            super(itemView);

            img_association = itemView.findViewById(R.id.img_association);
            tv_nomAssociation = itemView.findViewById(R.id.tv_nameAssociation);
            tv_descriptionAssociation = itemView.findViewById(R.id.tv_descriptionAssociation);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!=null && getAbsoluteAdapterPosition()!=RecyclerView.NO_POSITION){
                        listener.OnItemClick(getAbsoluteAdapterPosition());
                    }
                }
            });
        }
    }
}
