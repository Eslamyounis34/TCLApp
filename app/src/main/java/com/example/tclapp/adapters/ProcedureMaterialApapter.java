package com.example.tclapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tclapp.Activities.ProcedurePdfActivity;
import com.example.tclapp.R;
import com.example.tclapp.model.ProcedureMaterialModel;
import com.example.tclapp.model.ProcedureModel;

import java.util.List;

public class ProcedureMaterialApapter extends RecyclerView.Adapter<ProcedureMaterialApapter.ViewHolder> {

    private Context mContext;
    private List<ProcedureMaterialModel> mProceduresList;

    public ProcedureMaterialApapter(Context mContext, List<ProcedureMaterialModel> mProceduresList) {
        this.mContext = mContext;
        this.mProceduresList = mProceduresList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.recycleritem,parent,false);
        return new ViewHolder(v);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.tx.setText(mProceduresList.get(position).getTitle());
        holder.rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "My Id is "+mProceduresList.get(position).getId() , Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(mContext.getApplicationContext(), ProcedurePdfActivity.class);
                intent.putExtra("ProcedurePdfID",mProceduresList.get(position).getId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mProceduresList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tx;
        ImageView im;
        RelativeLayout rv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tx= itemView.findViewById(R.id.activityrowtext);
            im=itemView.findViewById(R.id.activityrowimage);
            rv=itemView.findViewById(R.id.procedureitemlayout);

        }
    }
}
