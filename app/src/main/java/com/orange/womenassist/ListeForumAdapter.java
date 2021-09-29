package com.orange.womenassist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListeForumAdapter extends RecyclerView.Adapter<ListeForumAdapter.ListeForumViewHolder> {

    Context context;
    List<ListeForum> forumList;

    public ListeForumAdapter(Context context, List<ListeForum> forumList) {
        this.context = context;
        this.forumList = forumList;
    }

    @NonNull
    @Override
    public ListeForumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_forum,parent,false);
        ListeForumViewHolder holder = new ListeForumViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListeForumViewHolder holder, int position) {
        holder.tv_nomForum.setText(forumList.get(position).getNomForum());
        holder.tv_descriptionForum.setText(forumList.get(position).getDescriptionForum());
    }

    @Override
    public int getItemCount() {
        return forumList.size();
    }

    public static class ListeForumViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_nomForum;
        private TextView tv_descriptionForum;

        public ListeForumViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_nomForum = itemView.findViewById(R.id.tv_nameForum);
            tv_descriptionForum = itemView.findViewById(R.id.tv_descriptionForum);
        }
    }
}
