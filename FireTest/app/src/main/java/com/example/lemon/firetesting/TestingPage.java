package com.example.lemon.firetesting;

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

import junit.framework.Test;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TestingPage extends AppCompatActivity {

    private ArrayList Ans = new ArrayList();
    private HashMap Exam = new HashMap();
    private ArrayList TopicArray = new ArrayList();
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private TextView NextTopic;
    private int Next = 0;
    private int MaxSize = 0;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_page);

        myDb = new DatabaseHelper(this);
        Exam = myDb.getData();


        for (Object key : Exam.keySet()) {
            TopicArray.add(key.toString());
            MaxSize++;
        }
        HideInformation();
        ShowNextData(Exam, TopicArray, 0);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        NextTopic = (TextView) findViewById(R.id.NextTopic);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MatchAnswer(Exam, TopicArray, Next, view);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MatchAnswer(Exam, TopicArray, Next, view);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MatchAnswer(Exam, TopicArray, Next, view);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MatchAnswer(Exam, TopicArray, Next, view);
            }
        });

        NextTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Next + 1 == MaxSize) {
                    ShowEndAlertDialog();
                } else {
                    Next++;
                    nextTopic(Exam, TopicArray, Next);
                }
            }
        });

    }

    public void MatchAnswer(HashMap exam, ArrayList topicArray, int n, View view) {
        int id = view.getId();
        Button PushButton = (Button) findViewById(id);
        String topicKey = topicArray.get(n).toString();
        List<String> Answers = new ArrayList<String>(Arrays.asList(exam.get(topicKey).toString().replaceAll(" ", "").split(",")));
        String CorrectAns = Answers.get(Answers.size()-1).toString();
        Log.i("correct", CorrectAns);
        Log.i("correct", PushButton.getText().toString());
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

    public void ShowNextData(HashMap exam, ArrayList topicArray, int n) {
        TextView testTopic = (TextView) findViewById(R.id.TestTopic);
        String topicKey = topicArray.get(n).toString();
        testTopic.setText(topicKey);
        List<String> Answers = new ArrayList<String>(Arrays.asList(exam.get(topicKey).toString().replaceAll(" ", "").split(",")));

        for (int i = 0; i < Answers.size()-1; i++) {
            int btnID = getResources().getIdentifier("button"+i, "id", getPackageName());
            Button button = (Button) findViewById(btnID);
            button.setText(Answers.get(i).toString());
        }
        OpenInformation(Answers.size()-1);
    }

    public void nextTopic(HashMap exam, ArrayList topicArray, int n) {
        HideInformation();
        ShowNextData(exam, topicArray, n);
    }

    public void ShowEndAlertDialog() {
        AlertDialog.Builder EndAlertDialog = new AlertDialog.Builder(this);
        EndAlertDialog.setTitle("已完成測驗");
        DialogInterface.OnClickListener oKClick = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setClass(TestingPage.this, MainFirePage.class);
                startActivity(intent);
            }
        };
        EndAlertDialog.setNegativeButton("結束", oKClick);
        EndAlertDialog.show();
    }
}
