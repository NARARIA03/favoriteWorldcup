package com.example.myintent;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;

public class WinnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_winner);

        ImageView img = findViewById(R.id.imgView);
        int resID = getResId(WorldCup.winprof, R.drawable.class); // 우승 교수님 데이터 가져오기
        img.setImageResource(resID); // 이미지 리소스를 우승 교수님으로 변경해서 출력

        TextView txt = findViewById(R.id.txtView);
        txt.setText(WorldCup.winSubj); // 텍스트 리소스를 우승 과목으로 변경해서 출력

        // 결과보기 버튼 클릭 시
        Button resBtn = findViewById(R.id.resBtn);
        resBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WinnerActivity.this, RecordView.class);
                startActivity(intent);
            }
        });

        // 인기투표 버튼 클릭 시
        Button nextButton = findViewById(R.id.nextBtn);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WinnerActivity.this, voteP.class);
                startActivity(intent);
            }
        });
    }
    // 우승 교수님데이터 int값으로 가져오게 하는 함수 (뭐 이상한거 쓴듯)
    public static int getResId(String resName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
