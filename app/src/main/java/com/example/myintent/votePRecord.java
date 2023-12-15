package com.example.myintent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class votePRecord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_precord);

        ListView listView = findViewById(R.id.listView);
        String[] data = {
                "1st : 김준석 교수님",
                "2nd : 박규동 교수님",
                "3rd : 박재성 교수님",
                "4th : 이상민 교수님",
                "5th : 임동혁 교수님",
                "6th : 김현경 교수님",
                "7th : 조민수 교수님",
                "8th : 조재희 교수님"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);

        Button goStartButton;
        goStartButton = (Button) findViewById(R.id.goStartButton);
        goStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent2 = new Intent(votePRecord.this, MainActivity.class);
                startActivity(intent2);
            }
        });
        Button goBackButton;
        goBackButton = (Button) findViewById(R.id.backButton);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
