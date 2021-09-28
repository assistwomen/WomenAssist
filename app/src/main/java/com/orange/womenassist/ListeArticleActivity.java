package com.orange.womenassist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListeArticleActivity extends AppCompatActivity {

    private RecyclerView recyclerView_articleList;
    private List<ListeArticle> articleList;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_article);

        articleList = new ArrayList<>();
        articleList.add(new ListeArticle("Natasha","Je me fais battre par mon mari et insulter par ma belle famille"));
        articleList.add(new ListeArticle("Natasha","Je me fais battre par mon mari et insulter par ma belle famille"));
        articleList.add(new ListeArticle("Natasha","Je me fais battre par mon mari et insulter par ma belle famille"));
        articleList.add(new ListeArticle("Natasha","Je me fais battre par mon mari et insulter par ma belle famille"));
        articleList.add(new ListeArticle("Natasha","Je me fais battre par mon mari et insulter par ma belle famille"));
        articleList.add(new ListeArticle("Natasha","Je me fais battre par mon mari et insulter par ma belle famille"));
        articleList.add(new ListeArticle("Natasha","Je me fais battre par mon mari et insulter par ma belle famille"));
        articleList.add(new ListeArticle("Natasha","Je me fais battre par mon mari et insulter par ma belle famille"));
        articleList.add(new ListeArticle("Natasha","Je me fais battre par mon mari et insulter par ma belle famille"));

        recyclerView_articleList = findViewById(R.id.lstArticle_recyclerview);
        floatingActionButton = findViewById(R.id.floating_btn);
        recyclerView_articleList.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        ListeArticleAdapter listeArticleAdapter = new ListeArticleAdapter(getApplicationContext(), articleList);

        recyclerView_articleList.setLayoutManager(layoutManager);
        recyclerView_articleList.setAdapter(listeArticleAdapter);
    }
}