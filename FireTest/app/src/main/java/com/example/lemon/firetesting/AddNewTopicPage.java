package com.example.lemon.firetesting;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddNewTopicPage extends AppCompatActivity {
    DatabaseHelper myDb;
    private TextView TextTopic, TextAnsA, TextAnsB, TextAnsC, TextAnsD, TextAnsCorrect;
    private Button AddNewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_topic);
        myDb = new DatabaseHelper(this);

        TextTopic = (TextView) findViewById(R.id.TextTopic);
        TextAnsA = (TextView) findViewById(R.id.TextAnsA);
        TextAnsB = (TextView) findViewById(R.id.TextAnsB);
        TextAnsC = (TextView) findViewById(R.id.TextAnsC);
        TextAnsD = (TextView) findViewById(R.id.TextAnsD);
        TextAnsCorrect = (TextView) findViewById(R.id.TextAnsCorrect);
        AddNewBtn = (Button) findViewById(R.id.AddNewBtn);
        AddNewData();
    }

    public void AddNewData(){
        AddNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean except = true;
                boolean CorrectAns = true;
                if (TextTopic.getText().toString().isEmpty()) {
                    Toast.makeText(AddNewTopicPage.this, "題目不能為空", Toast.LENGTH_SHORT).show();
                    except = false;
                    CorrectAns =false;
                }
                if (TextAnsCorrect.getText().toString().isEmpty()) {
                    Toast.makeText(AddNewTopicPage.this, "正確答案不能為空", Toast.LENGTH_SHORT).show();
                    except = false;
                    CorrectAns =false;
                }
                ArrayList Ans = new ArrayList();
                if (!TextAnsA.getText().toString().isEmpty()) {
                    Ans.add(TextAnsA.getText().toString());
                }
                if (!TextAnsB.getText().toString().isEmpty()) {
                    Ans.add(TextAnsB.getText().toString());
                }
                if (!TextAnsC.getText().toString().isEmpty()) {
                    Ans.add(TextAnsC.getText().toString());
                }
                if (!TextAnsD.getText().toString().isEmpty()) {
                    Ans.add(TextAnsD.getText().toString());
                }
                if (Ans.size() < 1) {
                    Toast.makeText(AddNewTopicPage.this, "選擇答案至少要一個", Toast.LENGTH_SHORT).show();
                    except = false;
                    CorrectAns =false;
                }

                if (CorrectAns) {
                    boolean flag = false;
                    for (int i = 0; i < Ans.size(); i++) {
                        if (Ans.get( i ).equals( TextAnsCorrect.getText().toString() )) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag == false) {
                        Toast.makeText( AddNewTopicPage.this, "正確答案與上方答案有所不符", Toast.LENGTH_SHORT ).show();
                        except = false;
                    }
                }
                if (!TextAnsCorrect.getText().toString().isEmpty()) {
                    Ans.add(TextAnsCorrect.getText().toString());
                }

                if (except) {
                    boolean isInserted = myDb.insertData(TextTopic.getText().toString(), Ans.toString());
                    if (isInserted) {
                        Intent intent = new Intent();
                        intent.setClass(AddNewTopicPage.this, AddNewTopicPage.class);
                        startActivity(intent);
                        Activity activity = AddNewTopicPage.this;
                        activity.finish();
                        Toast.makeText(AddNewTopicPage.this, "資料新增完成", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddNewTopicPage.this, "資料新增失敗", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
