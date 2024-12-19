package com.example.carpoolingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.DriverViewHolder> {

    private ArrayList<Driver> driverList;
    private OnItemClickListener onItemClickListener;

    public DriverAdapter(ArrayList<Driver> driverList) {
        this.driverList = driverList;
    }

    @Override
    public DriverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.userentry, parent, false);
        return new DriverViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DriverViewHolder holder, int position) {
        Driver currentDriver = driverList.get(position);
        holder.driverName.setText(currentDriver.getName());
        holder.vehicleType.setText(currentDriver.getVehicleType());
        holder.price.setText("Price: $" + currentDriver.getPrice());
        holder.driverRating.setText("Rating: " + currentDriver.getRating());

        holder.selectDriverButton.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return driverList.size();
    }

    public class DriverViewHolder extends RecyclerView.ViewHolder {
        public TextView driverName, vehicleType, price, driverRating;
        public Button selectDriverButton;
        public ImageView driverImage;

        public DriverViewHolder(View itemView) {
            super(itemView);
            driverName = itemView.findViewById(R.id.driverName);
            vehicleType = itemView.findViewById(R.id.vehicleType);
            price = itemView.findViewById(R.id.price);
            driverRating = itemView.findViewById(R.id.driverRating);
            driverImage = itemView.findViewById(R.id.driverImage);
            selectDriverButton = itemView.findViewById(R.id.selectDriverButton);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
