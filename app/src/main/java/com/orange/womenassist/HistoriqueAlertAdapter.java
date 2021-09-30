package com.orange.womenassist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoriqueAlertAdapter extends RecyclerView.Adapter<HistoriqueAlertAdapter.HistoriqueAlertViewHolder>{

    Context context;
    List<HistoriqueAlerte> historiqueAlerteList;

    public HistoriqueAlertAdapter(Context context, List<HistoriqueAlerte> historiqueAlerteList) {
        this.context = context;
        this.historiqueAlerteList = historiqueAlerteList;
    }

    @NonNull
    @Override
    public HistoriqueAlertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_historique_alerte,parent,false);
        HistoriqueAlertViewHolder holder = new HistoriqueAlertViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoriqueAlertViewHolder holder, int position) {
       holder.tv_dateAlert.setText(historiqueAlerteList.get(position).getDateAlert());
       holder.tv_Longitude.setText(historiqueAlerteList.get(position).getLongitude());
       holder.tv_Latitude.setText(historiqueAlerteList.get(position).getLatitude());
       holder.img.setImageResource(historiqueAlerteList.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return historiqueAlerteList.size();
    }

    public static class HistoriqueAlertViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_dateAlert;
        private TextView tv_Longitude;
        private TextView tv_Latitude;
        private ImageView img;

        public HistoriqueAlertViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_dateAlert = itemView.findViewById(R.id.date_alerte);
            tv_Longitude = itemView.findViewById(R.id.location_longitude);
            tv_Latitude = itemView.findViewById(R.id.location_latitude);
            img = itemView.findViewById(R.id.img_alert);

        }
    }
}
