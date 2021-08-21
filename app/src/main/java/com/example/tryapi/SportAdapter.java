package com.example.tryapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.sportHolder> {

    private ArrayList<SportModel> listData;
    private Callback callback;

    public SportAdapter(ArrayList<SportModel> listData, Callback callback) {
        this.listData = listData;
        this.callback = callback;
    }

    public interface Callback {
        void onClick(int position);
    }

    @NonNull
    @Override
    public SportAdapter.sportHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sport, parent, false);
        sportHolder holder = new sportHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SportAdapter.sportHolder holder, int position) {
        final SportModel getData = listData.get(position);
        String name = getData.getTvName();
        String description = getData.getTvDescription();

        holder.tvName.setText(name);
        holder.tvDescription.setText(description);
        Glide.with(holder.ivImage)
                .load(listData.get(position).getIvImage())
                .into(holder.ivImage);

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class sportHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvDescription;
        ImageView ivImage;
        LinearLayout linearLayout;

        public sportHolder(View view) {
            super(view);

                tvName = view.findViewById(R.id.tvName);
                tvDescription = view.findViewById(R.id.tvDescription);
                ivImage = view.findViewById(R.id.ivImage);
                linearLayout = view.findViewById(R.id.linearLayout);
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callback.onClick(getAdapterPosition());
                    }
                });
        }
    }
}
