package com.example.lemon.firetesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AddNewTopic extends AppCompatActivity {
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

    public void OpenDb() {

    }

    public void AddNewData(){
        AddNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean except = true;
                if (TextTopic.getText().toString().equals("") && TextTopic.getText() == null) {
                    Toast.makeText(AddNewTopic.this, "題目不能為空", Toast.LENGTH_LONG).show();
                    except = false;
                }
                if (TextAnsCorrect.getText().toString().equals("") && TextAnsCorrect.getText() == null) {
                    Toast.makeText(AddNewTopic.this, "正確答案不能為空", Toast.LENGTH_LONG).show();
                    except = false;
                }
                ArrayList Ans = new ArrayList();
                if (TextAnsA.getText().toString() != "") {
                    Ans.add(TextAnsA.getText().toString());
                }
                if (TextAnsB.getText().toString() != "") {
                    Ans.add(TextAnsB.getText().toString());
                }
                if (TextAnsC.getText().toString() != "") {
                    Ans.add(TextAnsC.getText().toString());
                }
                if (TextAnsD.getText().toString() != "") {
                    Ans.add(TextAnsD.getText().toString());
                }
                if (Ans.size() < 1) {
                    Toast.makeText(AddNewTopic.this, "選擇答案至少要一個", Toast.LENGTH_LONG).show();
                    except = false;
                }
                if (TextAnsCorrect.getText().toString() != "") {
                    Ans.add(TextAnsCorrect.getText().toString());
                }
                Log.i("CCCCCCCCCCCC", Ans.toString());
                if (except) {
                    boolean isInserted = myDb.insertData(TextTopic.getText().toString(), Ans.toString());
                    if (isInserted) {
                        Toast.makeText(AddNewTopic.this, "資料新增完成", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(AddNewTopic.this, "資料新增失敗", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
