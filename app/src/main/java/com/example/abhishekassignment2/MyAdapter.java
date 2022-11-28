package com.example.abhishekassignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{
    private Context context;
    private ArrayList id, first_name, last_name, course, credits, marks;

    //Created Constructor For Context And Array List
    public MyAdapter(Context context, ArrayList id, ArrayList first_name, ArrayList last_name, ArrayList course, ArrayList credits, ArrayList marks)
    {
        this.context = context;
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.course = course;
        this.credits = credits;
        this.marks = marks;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        //Infalte The Item Layout
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_layout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        //Set The Data In Items
        holder.id.setText(String.valueOf(id.get(position)));
        holder.first_name.setText(String.valueOf(first_name.get(position)));
        holder.last_name.setText(String.valueOf(last_name.get(position)));
        holder.course.setText(String.valueOf(course.get(position)));
        holder.credits.setText(String.valueOf(credits.get(position)));
        holder.marks.setText(String.valueOf(marks.get(position)));
    }

    //Will Get The Count Of Data From The Table
    @Override
    public int getItemCount()
    {
        return id.size();
    }

    //Extended Recycler View
    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView id, first_name, last_name, course, credits, marks;

        //Set The Data In The Text View
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            id = itemView.findViewById (R.id.textview1);
            first_name = itemView.findViewById (R.id.textview2);
            last_name = itemView.findViewById (R.id.textview3);
            course = itemView.findViewById (R.id.textview4);
            credits = itemView.findViewById (R.id.textview5);
            marks = itemView.findViewById (R.id.textview6);
        }
    }
}
