package com.jin.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AdapterViewExamActivity extends AppCompatActivity {

    private ArrayList<People> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_view_exam);

        // View
        ListView listView = findViewById(R.id.list_view);
        GridView gridView = findViewById(R.id.grid_view);
        Spinner spinner = findViewById(R.id.spinner);

        // Data
        data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int picture;
            if (i % 2 == 0) {
                picture = R.drawable.miku;
            } else {
                picture = R.mipmap.ic_launcher;
            }
            People people = new People("아무개 " + i, "전화번호 " + i, picture);
            data.add(people);
        }

        // Adapter
        PeopleAdapter adapter = new PeopleAdapter(AdapterViewExamActivity.this, data);

        listView.setAdapter(adapter);

        gridView.setAdapter(adapter);

        spinner.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            People people = data.get(position);
//            data.get(position);
                People people = (People) parent.getAdapter().getItem(position);
                Toast.makeText(AdapterViewExamActivity.this, people.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}