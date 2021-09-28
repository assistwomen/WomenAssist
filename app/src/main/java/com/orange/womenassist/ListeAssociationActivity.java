package com.orange.womenassist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListeAssociationActivity extends AppCompatActivity {

    private RecyclerView recyclerView_associationList;
    private List<ListeAssociation> associationList;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_association);

        associationList = new ArrayList<>();
        associationList.add(new ListeAssociation(R.drawable.assofedy, "ASSOFEDY","Association des femmes Dynamiques située à Douala Cameroun et vient ..."));
        associationList.add(new ListeAssociation(R.drawable.fdfa, "FDFA","Association des femmes Dynamiques située à Douala Cameroun et vient ..."));
        associationList.add(new ListeAssociation(R.drawable.femmesbattues, "Femmes Battues","Association des femmes Dynamiques située à Douala Cameroun et vient ..."));
        associationList.add(new ListeAssociation(R.drawable.assofedy, "ASSOFEDY","Association des femmes Dynamiques située à Douala Cameroun et vient ..."));
        associationList.add(new ListeAssociation(R.drawable.fdfa, "FDFA","Association des femmes Dynamiques située à Douala Cameroun et vient ..."));
        associationList.add(new ListeAssociation(R.drawable.femmesbattues, "Femmes Battues","Association des femmes Dynamiques située à Douala Cameroun et vient ..."));
        associationList.add(new ListeAssociation(R.drawable.assofedy, "ASSOFEDY","Association des femmes Dynamiques située à Douala Cameroun et vient ..."));
        associationList.add(new ListeAssociation(R.drawable.fdfa, "FDFA","Association des femmes Dynamiques située à Douala Cameroun et vient ..."));
        associationList.add(new ListeAssociation(R.drawable.femmesbattues, "Femmes Battues","Association des femmes Dynamiques située à Douala Cameroun et vient ..."));

        recyclerView_associationList = findViewById(R.id.lstAssociation_recyclerview);
        floatingActionButton = findViewById(R.id.floating_btn);
        recyclerView_associationList.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        ListeAssociationAdapter listeAssociationAdapter = new ListeAssociationAdapter(getApplicationContext(), associationList);

        recyclerView_associationList.setLayoutManager(layoutManager);
        recyclerView_associationList.setAdapter(listeAssociationAdapter);

        listeAssociationAdapter.OnRecyclerViewClickListener(new ListeAssociationAdapter.OnRecyclerViewClickListener() {
            @Override
            public void OnItemClick(int position) {
                Toast.makeText(ListeAssociationActivity.this, "Position" +position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}