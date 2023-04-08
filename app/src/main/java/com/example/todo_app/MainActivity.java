package com.example.todo_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private Button add;
    private ListView listView;
    private TextView count;
    Context context;
    private DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add=findViewById(R.id.add);
        listView=findViewById(R.id.todolist);
        count=findViewById(R.id.todocount);

        context=this;
        dbHandler=new DbHandler(this);

        int countTodo=dbHandler.countToDo();
        count.setText("You have "+countTodo+" ToDos in today");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(context,AddToDo.class));
            }
        });

    }
}