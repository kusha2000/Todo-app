package com.example.todo_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {
    private static final int VERSION=2;
    private static final String DB_NAME="todo";
    private static final String TABLE_NAME ="todo";

    private static final String ID="id";
    private static final String TITLE="title";
    private static final String DESCRIPTION="description";
    private static final String STARTED="started";
    private static final String FINISHED="finished";

    public DbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION  );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         String TABLE_CREATE_QUERY="CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TITLE+" TEXT,"+DESCRIPTION+" TEXT,"+STARTED+" TEXT,"+FINISHED+" TEXT"+");";

         db.execSQL(TABLE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE_QUERY="DROP TABLE IF EXISTS " +TABLE_NAME;
        db.execSQL(DROP_TABLE_QUERY);
        onCreate(db);
    }

    public void addToDo(ToDo todo){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(TITLE,todo.getTitle());
        contentValues.put(DESCRIPTION,todo.getDescription());
        contentValues.put(STARTED,todo.getStarted());
        contentValues.put(FINISHED,todo.getFinished());

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();


    }

    //count todo table records
    public int countToDo(){
        SQLiteDatabase db=getReadableDatabase();
        String query="SELECT * FROM "+TABLE_NAME;

        Cursor cursor=db.rawQuery(query,null);
        return cursor.getCount();
    }


    //Get all todos into a list
    public List<ToDo> getAllToDos(){

        List<ToDo> toDos=new ArrayList();
        SQLiteDatabase db=getReadableDatabase();
        String query="SELECT * FROM "+TABLE_NAME;

        Cursor cursor=db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                //create new ToDo object
                ToDo toDo=new ToDo();
                //get raw data
                toDo.setId(cursor.getInt(0));
                toDo.setTitle(cursor.getString(1));
                toDo.setDescription(cursor.getString(2));
                toDo.setStarted(cursor.getLong(3));
                toDo.setFinished(cursor.getLong(4));

                //toDos[obj1,obj2,obj3..]
                toDos.add(toDo);

            }while(cursor.moveToNext());
        }
        return toDos;
    }
}







