package com.orange.womenassist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListeForumActivity extends AppCompatActivity {

    private RecyclerView recyclerView_forumList;
    private List<ListeForum> forumList;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_forum);

        forumList = new ArrayList<>();
        forumList.add(new ListeForum("Violence Congugale","La violence exercée par un des conjoints sur l'autre, au sein d'un couple..."));
        forumList.add(new ListeForum("Violence Congugale","La violence exercée par un des conjoints sur l'autre, au sein d'un couple..."));
        forumList.add(new ListeForum("Violence Congugale","La violence exercée par un des conjoints sur l'autre, au sein d'un couple..."));
        forumList.add(new ListeForum("Violence Congugale","La violence exercée par un des conjoints sur l'autre, au sein d'un couple..."));
        forumList.add(new ListeForum("Violence Congugale","La violence exercée par un des conjoints sur l'autre, au sein d'un couple..."));
        forumList.add(new ListeForum("Violence Congugale","La violence exercée par un des conjoints sur l'autre, au sein d'un couple..."));
        forumList.add(new ListeForum("Violence Congugale","La violence exercée par un des conjoints sur l'autre, au sein d'un couple..."));
        forumList.add(new ListeForum("Violence Congugale","La violence exercée par un des conjoints sur l'autre, au sein d'un couple..."));
        forumList.add(new ListeForum("Violence Congugale","La violence exercée par un des conjoints sur l'autre, au sein d'un couple..."));
        forumList.add(new ListeForum("Violence Congugale","La violence exercée par un des conjoints sur l'autre, au sein d'un couple..."));
        forumList.add(new ListeForum("Violence Congugale","La violence exercée par un des conjoints sur l'autre, au sein d'un couple..."));

        recyclerView_forumList = findViewById(R.id.lstForum_recyclerview);
        floatingActionButton = findViewById(R.id.floating_btn);
        recyclerView_forumList.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        ListeForumAdapter listeForumAdapter = new ListeForumAdapter(getApplicationContext(), forumList);

        recyclerView_forumList.setLayoutManager(layoutManager);
        recyclerView_forumList.setAdapter(listeForumAdapter);
    }
}