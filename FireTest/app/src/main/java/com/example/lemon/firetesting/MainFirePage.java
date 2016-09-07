package com.example.lemon.firetesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainFirePage extends AppCompatActivity {

    private TextView AddNewTopic;
    private TextView EditTopic;
    private RelativeLayout touchTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fire_page);

        touchTest = (RelativeLayout) findViewById(R.id.touchTest);
        touchTest.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainFirePage.this, TestingPage.class);
                startActivity(intent);
            }
        });

        AddNewTopic = (TextView) findViewById(R.id.AddNewTopic);
        AddNewTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainFirePage.this, AddNewTopicPage.class);
                startActivity(intent);
            }
        });

        EditTopic = (TextView) findViewById(R.id.EditTopic);
        EditTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainFirePage.this, EditTopicPage.class);
                startActivity(intent);
            }
        });
    }
}
