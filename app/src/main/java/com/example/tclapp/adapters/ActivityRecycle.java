package com.example.tclapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tclapp.R;
import com.example.tclapp.model.RecommendationModel;

import java.util.ArrayList;

public class ActivityRecycle extends RecyclerView.Adapter<ActivityRecycle.ActivitiesViewHolder> {
    private Context mContext;
    private ArrayList<RecommendationModel> mActivitiesModels;
    public ActivityRecycle(Context mContext, ArrayList<RecommendationModel> mActivitiesModels) {
        this.mContext = mContext;
        this.mActivitiesModels = mActivitiesModels;
    }

    @NonNull
    @Override
    public ActivitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_card,parent,false);
        return new ActivitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivitiesViewHolder holder, final int position) {
        holder.names.setText(mActivitiesModels.get(position).getName());
        holder.arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = mActivitiesModels.get(position).getId();
                Intent intent = new Intent("custom-message");
                intent.putExtra("id",id);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mActivitiesModels.size();
    }

    public class ActivitiesViewHolder extends RecyclerView.ViewHolder{
        public TextView names;
        public ImageView arrow;
        public ActivitiesViewHolder(@NonNull View itemView) {
            super(itemView);
            names = (TextView)itemView.findViewById(R.id.activity_names);
            arrow = (ImageView)itemView.findViewById(R.id.next_icon);
        }
    }
}
