package com.example.todo_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Button add;
    private ListView listView;
    private TextView count;
    Context context;
    private DbHandler dbHandler;

    private List<ToDo> toDos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add=findViewById(R.id.add);
        listView=findViewById(R.id.todolist);
        count=findViewById(R.id.todocount);

        context=this;
        dbHandler=new DbHandler(this);

        toDos=new ArrayList<>();
        toDos=dbHandler.getAllToDos();

        ToDoAdapter adapter=new ToDoAdapter(context,R.layout.single_todo,toDos);
        listView.setAdapter(adapter);

        int countTodo=dbHandler.countToDo();
        count.setText("You have "+countTodo+" ToDos in today");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(context,AddToDo.class));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                ToDo todo=toDos.get(position);
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle(todo.getTitle());
                builder.setMessage(todo.getDescription());

                builder.setPositiveButton("Finished", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(context,MainActivity.class));
                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHandler.deleteToDo(todo.getId());
                        startActivity(new Intent(context,MainActivity.class));
                    }
                });
                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(context,EditToDo.class));
                    }
                });
                builder.show();

            }
        });

    }
}






