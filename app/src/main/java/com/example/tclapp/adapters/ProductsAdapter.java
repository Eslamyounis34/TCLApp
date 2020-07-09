package com.example.tclapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tclapp.Activities.ProcedureActivity;
import com.example.tclapp.Activities.ProductAllDetails;
import com.example.tclapp.R;
import com.example.tclapp.model.ProductsModel;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {
    private Context mContext;
    private ArrayList<ProductsModel> mActivitiesModels;
    public ProductsAdapter(Context mContext, ArrayList<ProductsModel> mActivitiesModels) {
        this.mContext = mContext;
        this.mActivitiesModels = mActivitiesModels;
    }
    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.product_card,parent,false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, final int position) {
        holder.names.setText(mActivitiesModels.get(position).getName());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ProductAllDetails.class);
                i.putExtra("SelectedProductId",mActivitiesModels.get(position).getId());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Toast.makeText(mContext, mActivitiesModels.get(position).getId(), Toast.LENGTH_SHORT).show();
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mActivitiesModels.size();
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder{
        public TextView names;
        public LinearLayout linearLayout;
        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            names = (TextView)itemView.findViewById(R.id.product_name);
            linearLayout=itemView.findViewById(R.id.recommendationnproductlayout);
        }
    }
}