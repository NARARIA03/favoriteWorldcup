package com.example.myintent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RecordView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordview);

        ListView listView = findViewById(R.id.listView);
        String[] data = {
                "1st : IOT시스템설계및실습",
                "2nd : 빅데이터프로그래밍",
                "3rd : 오픈소스소프트웨어",
                "4th : 데이터애널리틱스",
                "5th : 비주얼컴퓨팅",
                "6th : 컴퓨터비전",
                "7th : 기계학습",
                "8th : 소셜네트워크분석"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button goStartButton = findViewById(R.id.goStartButton);
        goStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(RecordView.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
