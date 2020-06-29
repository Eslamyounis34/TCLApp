package com.example.tclapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tclapp.Activities.ProcedureActivity;
import com.example.tclapp.Models.ProdcutsModel;
import com.example.tclapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {
    private Context mContext;
    private ArrayList<ProdcutsModel> mActivitiesModels;
    public ProductsAdapter(Context mContext, ArrayList<ProdcutsModel> mActivitiesModels) {
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
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        holder.names.setText(mActivitiesModels.get(position).getName());
        Intent i = new Intent(mContext, ProcedureActivity.class);
        i.putExtra("SelectedProductId", mActivitiesModels.get(position).getId());
        mContext.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return mActivitiesModels.size();
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder{
        public TextView names;
        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            names = (TextView)itemView.findViewById(R.id.product_name);
        }
    }
}
