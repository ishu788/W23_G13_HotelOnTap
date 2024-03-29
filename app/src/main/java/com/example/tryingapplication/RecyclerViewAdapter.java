package com.example.tryingapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
      holder.price.setText( "$" + listItem.getPrice());
      holder.id.setText(listItem.getId());
      holder.name.setText(listItem.getName());
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
        //TextView urls;
        ImageView hostThumbnail;
        TextView price;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.names);
            price=itemView.findViewById(R.id.item_Price);
            id = itemView.findViewById(R.id.ids);
           // urls = itemView.findViewById(R.id.urls);
            hostThumbnail = itemView.findViewById(R.id.hostThumbnail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent intent = new Intent(context,SingleSelection.class);
           // Toast.makeText(context, "position"+position, Toast.LENGTH_SHORT).show();
            intent.putExtra("name",listitems.get(position).getName());
            intent.putExtra("location",listitems.get(position).getId());
            intent.putExtra("price",listitems.get(position).getPrice());
            intent.putExtra("latitude",listitems.get(position).getLatitude());
            intent.putExtra("longitude",listitems.get(position).getLongitude());
            intent.putExtra("img1", listitems.get(position).getImage_item().get(2));
            intent.putExtra("img2", listitems.get(position).getImage_item().get(3));
            intent.putExtra("img3", listitems.get(position).getImage_item().get(4));
            intent.putExtra("daysSelected",listitems.get(position).getDaysSelected());

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


            context.startActivity(intent);


        }
    }
}
