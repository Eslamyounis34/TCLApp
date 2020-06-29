package com.example.tclapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tclapp.R;
import com.example.tclapp.model.CountryModel;

import java.util.ArrayList;
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    private Context mContext;
    private ArrayList<CountryModel> mActivitiesModels;

    public CountryAdapter(Context mContext, ArrayList<CountryModel> mActivitiesModels) {
        this.mContext = mContext;
        this.mActivitiesModels = mActivitiesModels;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.country_card,parent,false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.names.setText("Name : " + mActivitiesModels.get(position).getName());
        holder.phone.setText("phone : " + mActivitiesModels.get(position).getPhone());
        holder.mail.setText("Email : " + mActivitiesModels.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return mActivitiesModels.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder{
        public TextView names,phone,mail;
        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            names = (TextView)itemView.findViewById(R.id.country_info_name);
            phone = (TextView)itemView.findViewById(R.id.country_info_phone);
            mail = (TextView)itemView.findViewById(R.id.country_info_email);
        }
    }
}

