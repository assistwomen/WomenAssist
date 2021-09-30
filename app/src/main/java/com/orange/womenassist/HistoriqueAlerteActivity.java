package com.orange.womenassist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class HistoriqueAlerteActivity extends AppCompatActivity {

    private RecyclerView recyclerView_historiqueAlert;
    private List<HistoriqueAlerte> historiqueAlerteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique_alerte);

        historiqueAlerteList = new ArrayList<>();
        historiqueAlerteList.add(new HistoriqueAlerte("Mardi 28 Septembre 2021","4.047852","9.694579",R.drawable.alert));
        historiqueAlerteList.add(new HistoriqueAlerte("Mardi 28 Septembre 2021","4.047852","9.694579",R.drawable.alert));
        historiqueAlerteList.add(new HistoriqueAlerte("Mardi 28 Septembre 2021","4.047852","9.694579",R.drawable.alert));
        historiqueAlerteList.add(new HistoriqueAlerte("Mardi 28 Septembre 2021","4.047852","9.694579",R.drawable.alert));
        historiqueAlerteList.add(new HistoriqueAlerte("Mardi 28 Septembre 2021","4.047852","9.694579",R.drawable.alert));
        historiqueAlerteList.add(new HistoriqueAlerte("Mardi 28 Septembre 2021","4.047852","9.694579",R.drawable.alert));
        historiqueAlerteList.add(new HistoriqueAlerte("Mardi 28 Septembre 2021","4.047852","9.694579",R.drawable.alert));
        historiqueAlerteList.add(new HistoriqueAlerte("Mardi 28 Septembre 2021","4.047852","9.694579",R.drawable.alert));
        historiqueAlerteList.add(new HistoriqueAlerte("Mardi 28 Septembre 2021","4.047852","9.694579",R.drawable.alert));
        historiqueAlerteList.add(new HistoriqueAlerte("Mardi 28 Septembre 2021","4.047852","9.694579",R.drawable.alert));
        historiqueAlerteList.add(new HistoriqueAlerte("Mardi 28 Septembre 2021","4.047852","9.694579",R.drawable.alert));
        historiqueAlerteList.add(new HistoriqueAlerte("Mardi 28 Septembre 2021","4.047852","9.694579",R.drawable.alert));


        recyclerView_historiqueAlert= findViewById(R.id.historiqueAlerte_recyclerview);
        recyclerView_historiqueAlert.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        HistoriqueAlertAdapter historiqueAlertAdapter = new HistoriqueAlertAdapter(getApplicationContext(), historiqueAlerteList);

        recyclerView_historiqueAlert.setLayoutManager(layoutManager);
        recyclerView_historiqueAlert.setAdapter(historiqueAlertAdapter);

    }
}