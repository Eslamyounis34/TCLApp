package com.example.tclapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tclapp.Activities.ProductAllDetails;
import com.example.tclapp.R;
import com.example.tclapp.model.Product;

import java.util.List;

public class ProductAdapter extends  RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context mContext;
    private List<Product> productList;

    public ProductAdapter(Context mContext, List<Product> productList) {
        this.mContext = mContext;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.productitemrow, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.productName.setText(productList.get(position).getName());
        holder.productLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ProductAllDetails.class);
                i.putExtra("SelectedProductId", productList.get(position).getId());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                mContext.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        RelativeLayout productLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.productrowtext);
            productLayout = itemView.findViewById(R.id.productlayout);
        }
    }




}
