package com.orange.womenassist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FicheConseilActivity extends AppCompatActivity {

    private RecyclerView recyclerView_ficheConseilList;
    private List<FicheConseil> ficheConseilList;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_conseil);

        ficheConseilList = new ArrayList<>();
        ficheConseilList.add(new FicheConseil("Victime | Déposer plainte", "Association des femmes Dynamiques située à Douala Cameroun et vient ..."));
        ficheConseilList.add(new FicheConseil("Victime | Déposer plainte", "Association des femmes Dynamiques située à Douala Cameroun et vient ..."));
        ficheConseilList.add(new FicheConseil("Victime | Déposer plainte", "Association des femmes Dynamiques située à Douala Cameroun et vient ..."));
        ficheConseilList.add(new FicheConseil("Victime | Déposer plainte", "Association des femmes Dynamiques située à Douala Cameroun et vient ..."));
        ficheConseilList.add(new FicheConseil("Victime | Déposer plainte", "Association des femmes Dynamiques située à Douala Cameroun et vient ..."));
        ficheConseilList.add(new FicheConseil("Victime | Déposer plainte", "Association des femmes Dynamiques située à Douala Cameroun et vient ..."));
        ficheConseilList.add(new FicheConseil("Victime | Déposer plainte", "Association des femmes Dynamiques située à Douala Cameroun et vient ..."));
        ficheConseilList.add(new FicheConseil("Victime | Déposer plainte", "Association des femmes Dynamiques située à Douala Cameroun et vient ..."));
        ficheConseilList.add(new FicheConseil("Victime | Déposer plainte", "Association des femmes Dynamiques située à Douala Cameroun et vient ..."));
        ficheConseilList.add(new FicheConseil("Victime | Déposer plainte", "Association des femmes Dynamiques située à Douala Cameroun et vient ..."));

        recyclerView_ficheConseilList = findViewById(R.id.fiche_recyclerview);
        floatingActionButton = findViewById(R.id.floating_btn);
        recyclerView_ficheConseilList.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        FicheConseilAdapter ficheConseilAdapter = new FicheConseilAdapter(getApplicationContext(), ficheConseilList);

        recyclerView_ficheConseilList.setLayoutManager(layoutManager);
        recyclerView_ficheConseilList.setAdapter(ficheConseilAdapter);
    }
}