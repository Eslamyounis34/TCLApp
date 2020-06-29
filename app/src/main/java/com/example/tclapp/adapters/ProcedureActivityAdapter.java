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
import com.example.tclapp.model.ProcedureModel;

import java.util.List;

public class ProcedureActivityAdapter extends RecyclerView.Adapter<ProcedureActivityAdapter.ViewHolder> {

    private Context mContext;
    private List<ProcedureModel>mProceduresList;

    public ProcedureActivityAdapter(Context mContext, List<ProcedureModel> mProceduresList) {
        this.mContext = mContext;
        this.mProceduresList = mProceduresList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.recycleritem,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tx.setText(mProceduresList.get(position).getName());

        holder.tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qty = mProceduresList.get(position).getId();
                Intent intent = new Intent("custom-message");
                //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));
                intent.putExtra("quantity",qty);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return mProceduresList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tx;
        ImageView im;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tx= itemView.findViewById(R.id.activityrowtext);
            im=itemView.findViewById(R.id.activityrowimage);


        }
    }

}
