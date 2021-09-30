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

public class MemberAssociationAdapter extends RecyclerView.Adapter<MemberAssociationAdapter.MemberAssociationViewHolder>{

    Context context;
    List<MemberAssociation> memberAssociationList;

    public MemberAssociationAdapter(Context context, List<MemberAssociation> memberAssociationList) {
        this.context = context;
        this.memberAssociationList = memberAssociationList;
    }

    @NonNull
    @Override
    public MemberAssociationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_member_association,parent,false);
        MemberAssociationViewHolder holder = new MemberAssociationViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MemberAssociationViewHolder holder, int position) {
        holder.tv_nomMemberAssociation.setText(memberAssociationList.get(position).getNomMemberAssociation());
        holder.img.setImageResource(memberAssociationList.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return memberAssociationList.size();
    }

    public static class MemberAssociationViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_nomMemberAssociation;
        private ImageView img;

        public MemberAssociationViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_nomMemberAssociation = itemView.findViewById(R.id.tv_memberAssociation_name);
            img = itemView.findViewById(R.id.img_memberAssociation);
        }
    }
}
