package com.example.gnosis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;

import com.example.gnosis.reclerview.CategoryAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // Using ArrayList to store images data
    //ArrayList categoryImg = null;
    ArrayList categoryImg = new ArrayList<>(Arrays.asList(R.drawable.checklist, R.drawable.lightbulb,
            R.drawable.idea, R.drawable.mindful,
            R.drawable.schedule));
    ArrayList categoryName = new ArrayList<>(Arrays.asList("All"));
    ArrayList categoryDesc = new ArrayList<>(Arrays.asList("Data Structure", "C++", "C#", "JavaScript", "Java",
            "C-Language", "HTML 5", "CSS"));
    // test comment

    int DBLoadCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addbtn = findViewById(R.id.fab_create);

        // add categoryname in list
        for(String category : getResources().getStringArray(R.array.spinner_category)) {
            categoryName.add(category);
        }


        // Getting reference of recyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewMain);

        // Setting the layout as grid (2 cols)
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Sending reference and data to Adapter
        CategoryAdapter adapter = new CategoryAdapter(MainActivity.this, categoryImg, categoryName, categoryDesc);

        // Chul MIn : Call list activity when category button clicked
        adapter.setItemClickListener(new CategoryAdapter.ItemClickListener() {
            @Override
            public void itemListener(String categoryName) {

                if(categoryName.equals("Timetable")) {
                    Intent intent = new Intent(MainActivity.this, TimetableActivity.class);
                    startActivity(intent);
                    return;
                }

                Intent intent = new Intent(MainActivity.this, LIstActivity.class);
                intent.putExtra("categoryName", categoryName);
                startActivity(intent);
            }
        });

        // Setting Adapter to RecyclerView
        recyclerView.setAdapter(adapter);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                intent.putExtra("key", "new");
                startActivity(intent);
            }
        });
        CheckList();
    }

    private void CheckList() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DBLoadCounter = 0;
        for(String category : getResources().getStringArray(R.array.spinner_category)) {
            if(!category.equals("Timetable")) {

            }
        }

    }
}