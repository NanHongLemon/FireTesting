package com.example.lemon.firetesting;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DialogTitle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class TestingPage extends AppCompatActivity {

    private ArrayList Ans = new ArrayList();
    private List<Map<String, Object>> Exam = new ArrayList<Map<String, Object>>();
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private TextView NextTopic;
    private int Next = 0;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_page);

        myDb = new DatabaseHelper(this);
        Exam = myDb.getData();

        HideInformation();
        ShowNextData(Exam, 0);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        NextTopic = (TextView) findViewById(R.id.NextTopic);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MatchAnswer(Exam, Next, view);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MatchAnswer(Exam, Next, view);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MatchAnswer(Exam, Next, view);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MatchAnswer(Exam, Next, view);
            }
        });

        NextTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Next + 1 == Exam.size()) {
                    ShowEndAlertDialog();
                } else {
                    Next++;
                    nextTopic(Exam, Next);
                }
            }
        });

    }

    public void MatchAnswer(List<Map<String, Object>> exam, int n, View view) {
        int id = view.getId();
        Button PushButton = (Button) findViewById(id);
        List<String> Answers = new ArrayList<String>(asList(exam.get(n).get("answers").toString().replaceAll(" ","").split(",")));
        String CorrectAns = Answers.get(Answers.size()-1).toString();
        boolean correct = false;
        if (CorrectAns.equals(PushButton.getText().toString())) {
            correct = true;
        }
        for (int i = 0; i < Answers.size()-1; i++) {
            int btnID = getResources().getIdentifier("button"+i, "id", getPackageName());
            Button button = (Button) findViewById(btnID);
            if (Answers.get(i).equals(CorrectAns)) {
                button.setTextColor(Color.GREEN);
            } else {
                button.setTextColor(Color.RED);
            }
        }

        AlertDialog.Builder MyAlertDialog = new AlertDialog.Builder(this);
        ImageView image = new ImageView(this);
        if(correct) {
            image.setImageResource(R.drawable.cycle);
        } else {
            image.setImageResource(R.drawable.cross);
        }
        MyAlertDialog.setView(image);
        float imageWidthInPX = (float)image.getWidth();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Math.round(imageWidthInPX),
                Math.round(imageWidthInPX));
        image.setLayoutParams(layoutParams);

        MyAlertDialog.show();
    }

    public void HideInformation() {
        for (int i = 0; i < 4; i++) {
            int btnID = getResources().getIdentifier("button"+i, "id", getPackageName());
            Button button = (Button) findViewById(btnID);
            button.setVisibility(View.GONE);
            button.setTextColor(Color.BLACK);
        }
    }

    public void OpenInformation(int n) {
        for (int i = 0; i < n; i++) {
            int btnID = getResources().getIdentifier("button"+i, "id", getPackageName());
            Button button = (Button) findViewById(btnID);
            button.setVisibility(View.VISIBLE);
        }
    }

    public void ShowNextData(List<Map<String, Object>> exam, int n) {
        TextView testTopic = (TextView) findViewById(R.id.TestTopic);
        testTopic.setText(exam.get(n).get("topic").toString());
        List<String> Answers = new ArrayList<String>(Arrays.asList(exam.get(n).get("answers").toString().replaceAll(" ", "").split(",")));
        for (int i = 0; i < Answers.size()-1; i++) {
            int btnID = getResources().getIdentifier("button"+i, "id", getPackageName());
            Button button = (Button) findViewById(btnID);
            button.setText(Answers.get(i).toString());
        }
        OpenInformation(Answers.size()-1);
    }

    public void nextTopic(List<Map<String, Object>> exam, int n) {
        HideInformation();
        ShowNextData(exam, n);
    }

    public void ShowEndAlertDialog() {
        AlertDialog.Builder EndAlertDialog = new AlertDialog.Builder(this);
        EndAlertDialog.setTitle("已完成測驗");
        DialogInterface.OnClickListener oKClick = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setClass(TestingPage.this, MainFirePage.class);
                startActivity(intent);
                Activity activity1 = TestingPage.this;
                activity1.finish();
            }
        };
        EndAlertDialog.setNegativeButton("結束", oKClick);
        EndAlertDialog.show();
    }
}
