package com.orange.womenassist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentProtege extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private List<ContactConfiance> contactConfianceList;

    public FragmentProtege() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.protege_fragment, container, false);
        recyclerView = view.findViewById(R.id.contact_recyclerview);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), contactConfianceList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contactConfianceList = new ArrayList<>();
        contactConfianceList.add(new ContactConfiance("Tiowa Alex","696012330", R.drawable.ic_contact));
        contactConfianceList.add(new ContactConfiance("Kuigoua Reine","693749787",R.drawable.ic_contact));
        contactConfianceList.add(new ContactConfiance("Tchoffo Jaures","693513121",R.drawable.ic_contact));
        contactConfianceList.add(new ContactConfiance("Tiowa Alex","696012330", R.drawable.ic_contact));
        contactConfianceList.add(new ContactConfiance("Kuigoua Reine","693749787",R.drawable.ic_contact));
        contactConfianceList.add(new ContactConfiance("Tchoffo Jaures","693513121",R.drawable.ic_contact));
        contactConfianceList.add(new ContactConfiance("Tiowa Alex","696012330", R.drawable.ic_contact));
        contactConfianceList.add(new ContactConfiance("Kuigoua Reine","693749787",R.drawable.ic_contact));
        contactConfianceList.add(new ContactConfiance("Tchoffo Jaures","693513121",R.drawable.ic_contact));
        contactConfianceList.add(new ContactConfiance("Tiowa Alex","696012330", R.drawable.ic_contact));
        contactConfianceList.add(new ContactConfiance("Kuigoua Reine","693749787",R.drawable.ic_contact));
        contactConfianceList.add(new ContactConfiance("Tchoffo Jaures","693513121",R.drawable.ic_contact));
        contactConfianceList.add(new ContactConfiance("Tiowa Alex","696012330", R.drawable.ic_contact));
        contactConfianceList.add(new ContactConfiance("Kuigoua Reine","693749787",R.drawable.ic_contact));
        contactConfianceList.add(new ContactConfiance("Tchoffo Jaures","693513121",R.drawable.ic_contact));
        contactConfianceList.add(new ContactConfiance("Tiowa Alex","696012330", R.drawable.ic_contact));
        contactConfianceList.add(new ContactConfiance("Kuigoua Reine","693749787",R.drawable.ic_contact));
        contactConfianceList.add(new ContactConfiance("Tchoffo Jaures","693513121",R.drawable.ic_contact));

    }
}
