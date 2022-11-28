package com.example.abhishekassignment2;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewGradesFragment extends Fragment
{

    DatabaseHandler db;
    RecyclerView recyclerView;
    ArrayList<String> id, firstname, lastname, course, credits, marks;
    MyAdapter adapter;

    //Creates And Returns The View Hierarchy Associated With The Fragment.
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        //Inflate The Layout For This Fragment
        View rootView = inflater.inflate(R.layout.viewgrades_fragment,container,false);

        db = new DatabaseHandler(getContext());

        id = new ArrayList<>();
        firstname = new ArrayList<>();
        lastname = new ArrayList<>();
        course = new ArrayList<>();
        credits = new ArrayList<>();
        marks = new ArrayList<>();

        recyclerView = rootView.findViewById (R.id.recyclerview1);

        adapter = new MyAdapter(getContext(),id, firstname, lastname, course, credits, marks);
        recyclerView.setAdapter(adapter);

        //Will Set Layout To Recycler View
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Function Has Been Called Here
        displaydata();

        return rootView;
    }

    //Function For View Data
    private void displaydata()
    {
        Cursor cursor = db.show();
        if (cursor.getCount() == 0)
        {
            Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_LONG).show();
            return;
        }
        else
        {
            while (cursor.moveToNext())
            {
                id.add(cursor.getString(0));
                firstname.add(cursor.getString(1));
                lastname.add(cursor.getString(2));
                course.add(cursor.getString(3));
                credits.add(cursor.getString(4));
                marks.add(cursor.getString(5));
            }
        }
    }
}
