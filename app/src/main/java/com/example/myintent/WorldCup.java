package com.example.myintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;

public class WorldCup extends AppCompatActivity {

    // 버튼
    private static ImageButton imgt;
    private static ImageButton imgb;
    // 각종 INT값
    private static int round = 1;
    private static int totalRound = 8;
    // 각종 배열
    private static ArrayList<String> subjData = new ArrayList<>(); // 학기에 따라 과목명을 저장해둘 배열
    private static ArrayList<Integer> profData = new ArrayList<>(); // 학기에 따라 과목별 교수님 사진 번호를 저장해둘 배열
    private static ArrayList<String> subj = new ArrayList<>(); // init에서 subjData 배열을 활용해 랜덤하게 8개 저장할 배열
    private static ArrayList<Integer> prof = new ArrayList<>(); // init에서 profData 배열을 활용해 랜덤하게 8개 저장할 배열
    private static ArrayList<Integer> rand = new ArrayList<>(); // 랜덤하게 {과목,교수}를 가져오기 위해 인덱스를 섞는 배열
    private static ArrayList<String> subjTemp = new ArrayList<>(); // 과목 temp 리스트
    private static ArrayList<Integer> profTemp = new ArrayList<>(); // 교수 temp 리스트
    // 상단 Text
    private static TextView titleText;
    // 버튼 하단 과목명 Text
    private static TextView leftText;
    private static TextView rightText;




    // 다른 클래스에서 접근 가능한 우승 교수, 과목명
    public static String winprof;
    public static String winSubj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.world_cup);

        Button goBack;
        goBack = (Button) findViewById(R.id.goBack);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent2 = new Intent(WorldCup.this, SubActivity.class);
                startActivity(intent2);
            }
        });

        titleText = findViewById(R.id.titleText); // 화면 상단 "전공 월드컵 N강" 부분
        imgt = findViewById(R.id.imgt); // 왼쪽 버튼
        imgb = findViewById(R.id.imgb); // 오른쪽 버튼
        leftText = findViewById(R.id.leftText); // 왼쪽 과목 텍스트
        rightText = findViewById(R.id.rightText); // 오른쪽 과목 텍스트

        init(getApplicationContext());
        imgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LeftplayRound(); // 왼쪽 버튼이 눌렸을때 작동해야 하는 기능들 구현
            }
        });

        imgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RightplayRound(); // 오른쪽 버튼이 눌렸을 때 작동해야 하는 기능들 구현
            }
        });
    }

    public static void init(Context applicationContext) {
        setSubjProfNum(SubActivity.term); // 과목명과 교수님 이미지를 리스트에 추가, term이 1이면 1학기, 2면 2학기


        // 과목0부터 과목 7까지 rand 리스트에 저장
        for (int i = 0; i <= 7; i++) {
            rand.add(i);
        }
        // 0 ~ 7 순서 섞기
        Collections.shuffle(rand);
        // 순서가 섞인 rand 리스트에서 8개 뽑기
        for (int i = 0; i < 8; i++) {
            // 교수명과 과목을 동일한 구조의 랜덤으로 재배치한다
            prof.add(profData.get(rand.get(i))); // rand순서대로 profData에서 교수명 뽑아서 저장 -> 인덱스처럼 사진을 찾아갈 수 있음
            subj.add(subjData.get(rand.get(i)).toString()); // rand순서대로 subjData에서 과목명 뽑아서 저장
        }
        setImages(prof.get(0), prof.get(1), applicationContext); // 프로그램 시작 시교수님 이미지버튼 출력
        setSubjectText(subj.get(0), subj.get(1)); // 프로그램 시작 시 과목명 출력
        printRound(); // 프로그램 시작 시 상단에 제목과 라운드 수 표시
    }

    // 교수님 사진을 버튼에 그리는 함수
    public static void setImages(int n1, int n2, Context context) {
        int drawable1 = context.getResources().getIdentifier("prof" + n1, "drawable", context.getPackageName());
        int drawable2 = context.getResources().getIdentifier("prof" + n2, "drawable", context.getPackageName());
        imgt.setImageResource(drawable1);
        imgb.setImageResource(drawable2);
    }

    // 과목명 화면에 뜨게 하고, 버튼 클릭해서 바뀌면 같이 업데이트해주는 함수
    public static void setSubjectText(String s1, String s2) {
        leftText.setText(s1);
        rightText.setText(s2);
    }
    // 왼쪽 클릭 시 수행
    public void LeftplayRound() {
        if (round <= totalRound / 2) {
            profTemp.add(prof.get(round * 2 - 2)); // 선택한 교수사진값 임시저장
            subjTemp.add(subj.get(round * 2 - 2)); // 선택한 과목명 임시저장
            round++;
            addWinner(); // 승자를 temp리스트에 저장하는 메소드
            printRound(); // 화면에 제목과 라운드 출력 및 수정
        }
    }
    // 오른쪽 클릭 시 수행
    public void RightplayRound() {
        if (round <= totalRound / 2) {
            profTemp.add(prof.get(round * 2 - 1)); // 선택한 교수사진값 임시저장
            subjTemp.add(subj.get(round * 2 - 1)); // 선택한 과목명 임시저장
            round++;
            addWinner(); // 승자를 temp리스트에 저장하는 메소드
            printRound(); // 화면에 제목과 라운드 출력 및 수정
        }
    }

    public void addWinner() {
        // 강이 끝나지 않았다면
        if (!(round > totalRound / 2)) {
            // 새로운 라운드 이미지를 출력
            setImages(prof.get(round * 2 - 2), prof.get(round * 2 - 1), getApplicationContext());
            setSubjectText(subj.get(round * 2 - 2), subj.get(round * 2 - 1));
        } else {
            // 강이 끝났다면 새로운 라운드에 대한 winners 리스트를 설정 (8강 -> 4강, 4강 -> 결숭)
            ArrayList<Integer> newProf = new ArrayList<>(); // 새로운 교수 리스트 생성
            ArrayList<String> newSubj = new ArrayList<>(); // 새로운 강의 리스트 생성
            totalRound /= 2; // 강 값 절반으로
            for (int i = 0; i < totalRound; i++) {
                newProf.add(profTemp.get(i)); // 새로운 교수 리스트에 profTemp에 임시저장된 승자들을 저장
                newSubj.add(subjTemp.get(i)); // 새로운 과목 리스트에 subjTemp에 임시저장된 승자과목들을 저장
            }
            prof = new ArrayList<>(newProf); // 기존의 prof 리스트에 대치
            subj = new ArrayList<>(newSubj); // 기존의 subj 리스트에 대치

            if (prof.size() == 1 && subj.size() == 1) {
                // 만약 교수 리스트의 크기와 과목 리스트의 크기가 모두 1이라면, 즉 이상형 월드컵이 종료되었다면
                winprof = "prof" + prof.get(0); // 우승 교수를 public 변수에 저장
                winSubj = subj.get(0); // 우승 과목명을 public 변수에 저장
                Intent intent = new Intent(WorldCup.this, WinnerActivity.class); // 우승페이지로 넘어가기
                // 모든 배열 초기화 및 8강을 구성하기 위한 변수 세팅
                subjData.clear();
                profData.clear();
                prof.clear();
                subj.clear();
                profTemp.clear();
                subjTemp.clear();
                round = 1;
                totalRound = 8;
                startActivity(intent); // 세팅 다 끝내고 학기 선택 페이지로 이동
            }else { // 이상형 월드컵 안 끝났다면
                // temp 리스트 초기화
                subjTemp.clear();
                profTemp.clear();
                // 라운드값 1로 초기화
                round = 1;
                // 아직 우승자가 나오지 않았다면 새로운 라운드 이미지 출력
                setImages(prof.get(0), prof.get(1), getApplicationContext());
                setSubjectText(subj.get(0), subj.get(1));
            }
        }
    }

    public static void printRound() {
        if(totalRound==8) {
            titleText.setText("전공 월드컵 8강");
        } else if(totalRound==4) {
            titleText.setText("전공 월드컵 4강");
        } else if(totalRound==2) {
            titleText.setText("전공 월드컵 결승");
        } else if(totalRound==1) {
            titleText.setText("");
        }
    }

    /////////////////////////////////////////////////////////////////////////////////

    // 여기부터 사진 대신 과목명을 숫자별로 1=자료구조 2=데이터베이스 이런거 공식을 만들고, 이거로 뜨게 하는 함수들 만들거

    // 학기에 따라 과목명과 해당 교수명을 배열에 저장하는 함수
    public static void setSubjProfNum(int term) {
        // 월드컵에 사용되는 각종 값 전부 초기화
        subjData.clear();
        subjTemp.clear();
        subj.clear();
        profData.clear();
        profTemp.clear();
        prof.clear();
        rand.clear();
        round = 1;
        totalRound = 8;
        if (term == 1){
            // 2학년 1학기, 3학년 1학기 전공선택 과목과 교수사진번호 저장
            subjData.add("빅데이터프로그래밍");
            profData.add(3);
            subjData.add("컴퓨터네트워크");
            profData.add(2);
            subjData.add("오픈소스소프트웨어");
            profData.add(6);
            subjData.add("인터랙티브미디어개론");
            profData.add(4);
            subjData.add("기계학습");
            profData.add(5);
            subjData.add("텍스트마이닝");
            profData.add(8);
            subjData.add("IoT시스템설계및실습");
            profData.add(2);
            subjData.add("컴퓨터비전");
            profData.add(9);
        }
        else if (term == 2){
            // 2학년 2학기, 3학년 2학기 전공선택 과목과 교수사진번호 저장
            subjData.add("데이터베이스");
            profData.add(3);
            subjData.add("비주얼컴퓨팅");
            profData.add(7);
            subjData.add("정보디자인프로그래밍");
            profData.add(6);
            subjData.add("인터랙티브심리학");
            profData.add(4);
            subjData.add("소셜네트워크분석");
            profData.add(1);
            subjData.add("빅데이터처리및응용");
            profData.add(3);
            subjData.add("데이터애널리틱스");
            profData.add(8);
            subjData.add("HCI와UX평가");
            profData.add(4);
        }
    }
}