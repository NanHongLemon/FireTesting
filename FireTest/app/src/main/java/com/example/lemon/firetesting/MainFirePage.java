package com.example.lemon.firetesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainFirePage extends AppCompatActivity {

    private Button startTesting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fire_page);

        startTesting = (Button) findViewById(R.id.StartTesting);
        startTesting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainFirePage.this, TestingPage.class);
                startActivity(intent);
            }
        });

    }
}
