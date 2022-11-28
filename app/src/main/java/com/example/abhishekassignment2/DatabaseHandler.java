package com.example.abhishekassignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper
{
    //This Function Will Create A Database Named "GRADES"
    public DatabaseHandler(Context context)
    {
        super(context, "GRADES.db", null, 1);
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
    }

    //This Function Will Create The Table Named "GRADES" With 6 Properties Namely ID, FIRST_NAME, LAST_NAME, COURSE, CREDITS AND MARLS
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        //Here ID Is Auto Increment It Is A Primary Key
        sqLiteDatabase.execSQL("create table GRADES (ID integer primary key autoincrement, FIRST_NAME TEXT, LAST_NAME TEXT, COURSE TEXT, CREDITS TEXT, MARKS TEXT)");
    }

    //This Function Will Drop The Table If There Is A Table With Same Name
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        sqLiteDatabase.execSQL("drop table if exists GRADES");
    }

    //Function For Inserting Data
    public boolean insert(String FIRST_NAME, String LAST_NAME, String COURSE, String CREDITS, String MARKS)
    {
        // Gets The Data Repository In Write Mode
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        // Create A New Map Of Values, Where Column Names Are The Keys
        ContentValues contentValues=new ContentValues();
        contentValues.put("FIRST_NAME", FIRST_NAME);
        contentValues.put("LAST_NAME", LAST_NAME);
        contentValues.put("COURSE", COURSE);
        contentValues.put("CREDITS", CREDITS);
        contentValues.put("MARKS", MARKS);
        // Insert The New Row, Returning The Primary Key Value Of The New Row
        long res=sqLiteDatabase.insert("GRADES",null,contentValues);
        if(res == -1)
            return false;
        else
            return true;
    }

    //Function For Showing Data
    public Cursor show()
    {
        // Gets The Data Repository In Write Mode
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        //Selects All The Data From The Table
        Cursor cur=sqLiteDatabase.rawQuery("select * from GRADES",null);
        return cur;
    }
}
