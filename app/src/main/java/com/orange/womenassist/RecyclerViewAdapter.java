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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context context;
    List<ContactConfiance> data;

    public RecyclerViewAdapter(Context context, List<ContactConfiance> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_contact,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_nom.setText(data.get(position).getNom());
        holder.tv_telephone.setText(data.get(position).getTelephone());
        holder.img.setImageResource(data.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_nom;
        private TextView tv_telephone;
        private ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_nom = itemView.findViewById(R.id.contact_name);
            tv_telephone = itemView.findViewById(R.id.phone_number);
            img = itemView.findViewById(R.id.img_contact);
        }
    }
}