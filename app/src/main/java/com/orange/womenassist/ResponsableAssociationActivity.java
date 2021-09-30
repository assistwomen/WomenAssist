package com.orange.womenassist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ResponsableAssociationActivity extends AppCompatActivity {

    private RecyclerView recyclerView_respAssociation;
    private List<ResponsableAssociation> responsableAssociationList;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responsable_association);

        responsableAssociationList = new ArrayList<>();
        responsableAssociationList.add(new ResponsableAssociation("Queen",R.drawable.ic_contact));
        responsableAssociationList.add(new ResponsableAssociation("Alex",R.drawable.ic_contact));
        responsableAssociationList.add(new ResponsableAssociation("Jaures",R.drawable.ic_contact));
        responsableAssociationList.add(new ResponsableAssociation("Queen",R.drawable.ic_contact));
        responsableAssociationList.add(new ResponsableAssociation("Alex",R.drawable.ic_contact));
        responsableAssociationList.add(new ResponsableAssociation("Jaures",R.drawable.ic_contact));
        responsableAssociationList.add(new ResponsableAssociation("Queen",R.drawable.ic_contact));
        responsableAssociationList.add(new ResponsableAssociation("Alex",R.drawable.ic_contact));
        responsableAssociationList.add(new ResponsableAssociation("Jaures",R.drawable.ic_contact));
        responsableAssociationList.add(new ResponsableAssociation("Queen",R.drawable.ic_contact));
        responsableAssociationList.add(new ResponsableAssociation("Alex",R.drawable.ic_contact));
        responsableAssociationList.add(new ResponsableAssociation("Jaures",R.drawable.ic_contact));

        recyclerView_respAssociation = findViewById(R.id.respAssociation_recyclerview);
        floatingActionButton = findViewById(R.id.floating_btn);
        recyclerView_respAssociation.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        ResponsableAssociationAdapter responsableAssociationAdapter = new ResponsableAssociationAdapter(getApplicationContext(), responsableAssociationList);

        recyclerView_respAssociation.setLayoutManager(layoutManager);
        recyclerView_respAssociation.setAdapter(responsableAssociationAdapter);
    }
}