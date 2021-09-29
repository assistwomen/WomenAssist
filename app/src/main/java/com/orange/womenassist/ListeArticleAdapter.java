package com.orange.womenassist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListeArticleAdapter extends RecyclerView.Adapter<ListeArticleAdapter.ListeArticleViewHolder> {

    Context context;
    List<ListeArticle> articleList;

    public ListeArticleAdapter(Context context, List<ListeArticle> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public ListeArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_liste_article,parent,false);
        ListeArticleViewHolder holder = new ListeArticleViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListeArticleViewHolder holder, int position) {
        holder.tv_nomArticle.setText(articleList.get(position).getNomArticle());
        holder.tv_descriptionArticle.setText(articleList.get(position).getDescriptionArticle());

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public static class ListeArticleViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_nomArticle;
        private TextView tv_descriptionArticle;

        public ListeArticleViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_nomArticle = itemView.findViewById(R.id.tv_nameArticle);
            tv_descriptionArticle = itemView.findViewById(R.id.tv_descriptionArticle);
        }
    }
}
