package com.example.abhishekassignment2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchFragment extends Fragment
{

    public TextView idTextView, programTextView;
    public RadioGroup searchRadioGroup;
    public RadioButton idRadioButton, programRadioButton;
    public EditText idEditText;
    public ListView courseListView;
    public Button idSearchButton, codeSearchButton;

    DatabaseHandler db;
    RecyclerView recyclerView;
    //Created Array
    ArrayList<String> id, firstname, lastname, course, credits, marks;
    MyAdapter adapter;

    String listviewItem;

    //Course Array For List View
    String courseList[] = {"PROG 8480", "PROG 8470", "PROG 8460", "PROG 8450"};

    //Creates And Returns The View Hierarchy Associated With The Fragment.
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        //Inflate The Layout For This Fragment
        View rootView = inflater.inflate(R.layout.search_fragment,container,false);

        db = new DatabaseHandler(getContext());

        searchRadioGroup = (RadioGroup) rootView.findViewById (R.id.radiogroup1);
        idRadioButton = (RadioButton) rootView.findViewById (R.id.radioButton1);
        programRadioButton = (RadioButton) rootView.findViewById (R.id.radioButton2);
        idTextView = (TextView) rootView.findViewById (R.id.textview3);
        programTextView = (TextView) rootView.findViewById (R.id.textview4);
        idEditText = (EditText) rootView.findViewById (R.id.edittext1);
        courseListView = (ListView) rootView.findViewById (R.id.listview);
        idSearchButton = (Button) rootView.findViewById (R.id.idButton);
        codeSearchButton = (Button) rootView.findViewById (R.id.codeButton);

        //Creates A View For Each Array Item By Calling toString() On Each Item
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_single_choice, courseList);
        //Sets The Choice Mode Of List View To Single
        courseListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        //Set The Adapter With The Data
        courseListView.setAdapter(arrayAdapter);

        //List View On Item Click Listener
        courseListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            //Event For On Item Click
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                //Gets The Position Of The Item And Store It In listviewItem
                listviewItem = (String) adapterView.getItemAtPosition(i);
            }
        });

        //When The Screen Is Loaded Will Set All The Widgets To INVISIBLE Accept Title, Search By Radio Buttons
        idTextView.setVisibility(View.INVISIBLE);
        idEditText.setVisibility(View.INVISIBLE);
        programTextView.setVisibility(View.INVISIBLE);
        courseListView.setVisibility(View.INVISIBLE);
        idSearchButton.setVisibility(View.INVISIBLE);
        codeSearchButton.setVisibility(View.INVISIBLE);

        //Event For Radio Group
        searchRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                //If ID Radio Button Is Selected Then User Will Be Show An Edit Text Box. Where The User Need To Enter The ID
                if (idRadioButton.isChecked())
                {
                    idTextView.setVisibility(View.VISIBLE);
                    idEditText.setVisibility(View.VISIBLE);
                    idSearchButton.setVisibility(View.VISIBLE);
                    programTextView.setVisibility(View.INVISIBLE);
                    courseListView.setVisibility(View.INVISIBLE);
                    codeSearchButton.setVisibility(View.INVISIBLE);
                }

                //If Program Radio Button Is Selected Then User Will Be Show An List View. Where The User Need Item From The List View
                if (programRadioButton.isChecked())
                {
                    programTextView.setVisibility(View.VISIBLE);
                    courseListView.setVisibility(View.VISIBLE);
                    codeSearchButton.setVisibility(View.VISIBLE);
                    idSearchButton.setVisibility(View.INVISIBLE);
                    idTextView.setVisibility(View.INVISIBLE);
                    idEditText.setVisibility(View.INVISIBLE);
                }
            }
        });

        //Event For ID Search Button
        idSearchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                id = new ArrayList<>();
                firstname = new ArrayList<>();
                lastname = new ArrayList<>();
                course = new ArrayList<>();
                credits = new ArrayList<>();
                marks = new ArrayList<>();

                recyclerView = rootView.findViewById (R.id.recyclerview2);

                adapter = new MyAdapter(getContext(),id, firstname, lastname, course, credits, marks);
                recyclerView.setAdapter(adapter);

                //Will Set Layout To Recycler View
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                //Function Has Been Called Here
                displayIDdata();
            }
        });

        //Event For ID Search Button
        codeSearchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                id = new ArrayList<>();
                firstname = new ArrayList<>();
                lastname = new ArrayList<>();
                course = new ArrayList<>();
                credits = new ArrayList<>();
                marks = new ArrayList<>();

                recyclerView = rootView.findViewById (R.id.recyclerview2);

                adapter = new MyAdapter(getContext(),id, firstname, lastname, course, credits, marks);
                recyclerView.setAdapter(adapter);

                //Will Set Layout To Recycler View
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                //Function Has Been Called Here
                displayCodeData();
            }
        });

        return rootView;
    }

    //Function For Display ID Data
    private void displayIDdata()
    {
        //Will Fetch The Data From The Edit Text Box As String And Store It In A Variable
        String idNumber = idEditText.getText().toString();
        //Will Open The Database Named "GRADES"
        SQLiteDatabase simpledb = getContext().openOrCreateDatabase("GRADES.db", Context.MODE_PRIVATE,null);
        //Will Execute The Query. If There Is A Data With The ID Then Data Will Be Displayed
        Cursor cursor = simpledb.rawQuery("select * from GRADES where ID = '"+idNumber+"'",null);

        //If There Are No Data In The Table Or No Data Found With The Given ID Then Toast Message Will Be Printed
        if (cursor.getCount() == 0)
        {
            Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_LONG).show();
            return;
        }
        //If Data Is Found With Given ID The Data Will Be Printed
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

    //Function For Display ID Data
    private void displayCodeData()
    {
        //Will Open The Database Named "GRADES"
        SQLiteDatabase simpledb = getContext().openOrCreateDatabase("GRADES.db", Context.MODE_PRIVATE,null);
        //Will Execute The Query Where Course Has Been Selected From The List View.
        Cursor cursor = simpledb.rawQuery("select * from GRADES where COURSE = '"+listviewItem+"'",null);

        //If There Are No Data In The Table Or No Data Found Toast Message Will Be Printed
        if (cursor.getCount() == 0)
        {
            Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_LONG).show();
            return;
        }
        //If Data Is Found With The Selected Item Then The Data Will Be Printed
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
