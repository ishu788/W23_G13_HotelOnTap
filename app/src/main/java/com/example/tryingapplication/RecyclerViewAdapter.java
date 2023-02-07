package com.example.tryingapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final Context context;
    private List<ListItem> listitems;

    public RecyclerViewAdapter(Context context, List<ListItem> listitems) {
        this.context = context;
        this.listitems = listitems;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_note,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
      ListItem listItem = listitems.get(position);
      holder.id.setText(listItem.getId());
      holder.name.setText(listItem.getName());
      holder.urls.setText(listItem.getUrl());



    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        TextView name;
        TextView urls;
        ImageView hostThumbnail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.names);
            id = itemView.findViewById(R.id.ids);
            urls = itemView.findViewById(R.id.urls);
            hostThumbnail = itemView.findViewById(R.id.hostThumbnail);
        }
    }
}
