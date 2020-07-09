package com.example.tclapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tclapp.Activities.CleaningProductActivity;
import com.example.tclapp.R;
import com.example.tclapp.model.MaterialsModel;

import java.util.ArrayList;

public class MaterailsAdapter extends RecyclerView.Adapter<MaterailsAdapter.MaterialsViewHolder> {
    private Context mContext;
    private ArrayList<MaterialsModel> mActivitiesModels;
    public MaterailsAdapter(Context mContext, ArrayList<MaterialsModel> mActivitiesModels) {
        this.mContext = mContext;
        this.mActivitiesModels = mActivitiesModels;
    }

    @NonNull
    @Override
    public MaterialsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_card,parent,false);
        return new MaterialsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialsViewHolder holder, final int position) {
        holder.names.setText(mActivitiesModels.get(position).getName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = mActivitiesModels.get(position).getId();
                Intent intent = new Intent(mContext, CleaningProductActivity.class);
                intent.putExtra("id",id);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mActivitiesModels.size();
    }

    public class MaterialsViewHolder extends RecyclerView.ViewHolder{
        public TextView names;
        public ImageView arrow;
        public LinearLayout layout;
        public MaterialsViewHolder(@NonNull View itemView) {
            super(itemView);
            names = (TextView)itemView.findViewById(R.id.activity_names);
            arrow = (ImageView)itemView.findViewById(R.id.next_icon);
            layout=itemView.findViewById(R.id.recommendproductitemlayout);
        }
    }
}
