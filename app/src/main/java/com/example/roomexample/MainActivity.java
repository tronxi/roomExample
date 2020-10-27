package com.example.roomexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.roomexample.models.Result;
import com.example.roomexample.models.ResultDataBase;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ResultDataBase db;
    private TextView textView;
    private EditText nameText;
    private EditText fichasText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        nameText = findViewById(R.id.name);
        fichasText = findViewById(R.id.fichas);

        db = Room.databaseBuilder(
                getApplicationContext(),
                ResultDataBase.class,
                "resultados.db"
        ).allowMainThreadQueries().build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        read();
    }

    public void deleteAll(View view) {
        db.resultDao().deleteAll();
        read();
    }

    private void read() {
        List<Result> results = db.resultDao().getAll();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < results.size(); i++) {
            stringBuilder
                    .append("Fichas: ")
                    .append(results.get(i).getFichas())
                    .append(" Name: ")
                    .append(results.get(i).getName())
                    .append(" \n");
        }
        Log.i("MIW", results.toString());
        textView.setText(stringBuilder.toString());
    }

    public void save(View view) {
        Result rl = new Result( Integer.parseInt(fichasText.getText().toString()), nameText.getText().toString());
        db.resultDao().insert(rl);
        fichasText.setText("");
        nameText.setText("");
        read();
    }
}