package com.example.myintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    private Spinner spinner;
    private TextView term_result;
    public static int term;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);


        spinner = findViewById(R.id.spinner);
        term_result = findViewById(R.id.term_result);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.term, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                term_result.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button TermSelect;
        TermSelect = findViewById(R.id.TermSelect);

        TermSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 선택값 표시
                String selectedTerm = term_result.getText().toString();

                // 학기 선택 후 다음 페이지로 넘어가는 버튼 클릭 시 1학기 -> 1, 2학기 -> 2를 WorldCup클래스로 전달
                Intent intent = new Intent(SubActivity.this, WorldCup.class);
                if ("1학기".equals(selectedTerm)) {
                    term = 1;
                }
                else if ("2학기".equals(selectedTerm)) {
                    term = 2;
                }
                startActivity(intent);
            }
        });

        Button goBack;
        goBack = (Button) findViewById(R.id.goBack);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent2 = new Intent(SubActivity.this, MainActivity.class);
                startActivity(intent2);
            }
        });
    }
}
