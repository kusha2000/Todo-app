package com.example.todo_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddToDo extends AppCompatActivity {
    private EditText title,desc;
    private Button add;
    private DbHandler dbHandler;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);

        context=this;
        dbHandler=new DbHandler(context);

        title=findViewById(R.id.editTextTitle);
        desc=findViewById(R.id.editTextDescription);
        add=findViewById(R.id.buttonAdd);




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uderTitle=title.getText().toString();
                String uderdesc=desc.getText().toString();
                long started=System.currentTimeMillis();

                ToDo toDo=new ToDo(uderTitle,uderdesc,started,0);
                dbHandler.addToDo(toDo);

                startActivity(new Intent(context,MainActivity.class));
            }
        });
    }
}








