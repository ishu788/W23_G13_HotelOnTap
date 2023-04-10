package com.example.tryingapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.MyViewHolder> {



    Context context;

    ArrayList<String> ids;
    ArrayList<String> hotelNames;

    ArrayList<String> names;
    ArrayList<String> pics;
    ArrayList<String> prices;
    ArrayList<String> days;


    BookingAdapter(Context context,ArrayList ids, ArrayList hotelNames, ArrayList pics, ArrayList names, ArrayList prices, ArrayList days){
        this.context = context;
        this.ids= ids;
        this.hotelNames = hotelNames;
        this.names = names;
        this.pics = pics;
        this.prices = prices;
        this.days = days;




    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.bookinglist,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.hotelBookName.setText(hotelNames.get(position));
        Picasso.get().load(pics.get(position)).into(holder.hotelBookPic);
        holder.hotelBookPerson.setText("Name: " +names.get(position));
        holder.hotelBookPrice.setText("Price: $ "+prices.get(position));
        holder.hotelBookDays.setText("No. of days: " +days.get(position));

    }

    @Override
    public int getItemCount() {
        return hotelNames.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{



        TextView hotelBookName, hotelBookPerson, hotelBookPrice,hotelBookDays;
        ImageView hotelBookPic;






        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            hotelBookPic = itemView.findViewById(R.id.hotelBookPic);
            hotelBookName = itemView.findViewById(R.id.hotelBookName);
            hotelBookPerson = itemView.findViewById(R.id.hotelBookPerson);
            hotelBookPrice= itemView.findViewById(R.id.hotelBookPrice);
            hotelBookDays= itemView.findViewById(R.id.hotelBookDays);

        }
    }

}
