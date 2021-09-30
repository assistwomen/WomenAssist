package com.orange.womenassist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MembreAssociationActivity extends AppCompatActivity {

    private RecyclerView recyclerView_memberAssociation;
    private List<MemberAssociation> memberAssociationList;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membre_association);

        memberAssociationList = new ArrayList<>();
        memberAssociationList.add(new MemberAssociation("Queen",R.drawable.ic_contact));
        memberAssociationList.add(new MemberAssociation("Alex",R.drawable.ic_contact));
        memberAssociationList.add(new MemberAssociation("Jaures",R.drawable.ic_contact));
        memberAssociationList.add(new MemberAssociation("Queen",R.drawable.ic_contact));
        memberAssociationList.add(new MemberAssociation("Alex",R.drawable.ic_contact));
        memberAssociationList.add(new MemberAssociation("Jaures",R.drawable.ic_contact));
        memberAssociationList.add(new MemberAssociation("Queen",R.drawable.ic_contact));
        memberAssociationList.add(new MemberAssociation("Alex",R.drawable.ic_contact));
        memberAssociationList.add(new MemberAssociation("Jaures",R.drawable.ic_contact));
        memberAssociationList.add(new MemberAssociation("Queen",R.drawable.ic_contact));
        memberAssociationList.add(new MemberAssociation("Alex",R.drawable.ic_contact));
        memberAssociationList.add(new MemberAssociation("Jaures",R.drawable.ic_contact));

        recyclerView_memberAssociation= findViewById(R.id.memberAssociation_recyclerview);
        floatingActionButton = findViewById(R.id.floating_btn);
        recyclerView_memberAssociation.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        MemberAssociationAdapter memberAssociationAdapter = new MemberAssociationAdapter(getApplicationContext(), memberAssociationList);

        recyclerView_memberAssociation.setLayoutManager(layoutManager);
        recyclerView_memberAssociation.setAdapter(memberAssociationAdapter);
    }
}