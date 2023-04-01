package com.example.tryingapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final Context context;
    public static List<ListItem> listitems;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      ListItem listItem = listitems.get(position);
      holder.id.setText(listItem.getId());
      holder.name.setText(listItem.getName());
      holder.urls.setText(listItem.getUrl());
        Picasso.get().load(listItem.getImage_item().get(1))
                .into(holder.hostThumbnail);



    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

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
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent intent = new Intent(context,SingleSelection.class);
            Toast.makeText(context, "position"+position, Toast.LENGTH_SHORT).show();
            intent.putExtra("name",listitems.get(position).getName());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intent);


        }
    }
}
