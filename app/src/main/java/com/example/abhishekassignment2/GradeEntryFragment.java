package com.example.abhishekassignment2;

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

public class GradeEntryFragment extends Fragment
{

    public ListView courseListView;
    public EditText firstNameEditText, lastNameEditText, marksEditText;
    public RadioGroup creditsRadioGroup;
    public RadioButton credit1RadioButton, credit2RadioButton, credit3RadioButton, credit4RadioButton;
    public Button submitButton;

    String listviewItem;
    String Credits;

    DatabaseHandler db;

    //Course Array For List View
    String courseList[] = {"PROG 8480", "PROG 8470", "PROG 8460", "PROG 8450"};

    //Creates And Returns The View Hierarchy Associated With The Fragment.
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        //Inflate The Layout For This Fragment
        View rootView = inflater.inflate(R.layout.gradeentry_fragment,container,false);

        db = new DatabaseHandler(getContext());

        firstNameEditText = (EditText) rootView.findViewById (R.id.edittext1);
        lastNameEditText = (EditText) rootView.findViewById (R.id.edittext2);
        marksEditText = (EditText) rootView.findViewById (R.id.edittext3);
        courseListView = (ListView) rootView.findViewById (R.id.listview);
        creditsRadioGroup = (RadioGroup) rootView.findViewById (R.id.radiogroup1);
        credit1RadioButton = (RadioButton) rootView.findViewById (R.id.radioButton1);
        credit2RadioButton = (RadioButton) rootView.findViewById (R.id.radioButton2);
        credit3RadioButton = (RadioButton) rootView.findViewById (R.id.radioButton3);
        credit4RadioButton = (RadioButton) rootView.findViewById (R.id.radioButton4);
        submitButton = (Button) rootView.findViewById (R.id.button);

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
                //Toast.makeText(getContext(),"" +listviewItem, Toast.LENGTH_LONG).show();
            }
        });

        //Sobmit Button On Click Event
        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //If Credit 1 Radio Button Is Selected Will Set The Credits Value to 1
                if (credit1RadioButton.isChecked())
                {
                    Credits = "1";
                }
                //If Credit 2 Radio Button Is Selected Will Set The Credits Value to 2
                if (credit2RadioButton.isChecked())
                {
                    Credits = "2";
                }
                //If Credit 3 Radio Button Is Selected Will Set The Credits Value to 3
                if (credit3RadioButton.isChecked())
                {
                    Credits = "3";
                }
                //If Credit 4 Radio Button Is Selected Will Set The Credits Value to 4
                if (credit4RadioButton.isChecked())
                {
                    Credits = "4";
                }

                //Will Insert The Data In The Table. Will Get First Name, Last Name and Marks From Edit Text Box. While List View Data Will Be Fetch Using Position Values and Credits Will Be Taken From Radio Button
                boolean result=db.insert(firstNameEditText.getText().toString(), lastNameEditText.getText().toString(), listviewItem, Credits, marksEditText.getText().toString());
                //If Data Are True It Will Toast Message As "Data Inserted"
                if(result == true)
                    Toast.makeText(getContext(),"Data Inserted",Toast.LENGTH_LONG).show();
                //If Data Are False It Will Toast Message As "Data Inserted"
                else
                    Toast.makeText(getContext(),"Data Not Inserted",Toast.LENGTH_LONG).show();

                //Will Clear The Screen Data
                firstNameEditText.setText("");
                lastNameEditText.setText("");
                courseListView.clearChoices();
                creditsRadioGroup.clearCheck();
                marksEditText.setText("");
            }
        });

        return rootView;
    }
}
