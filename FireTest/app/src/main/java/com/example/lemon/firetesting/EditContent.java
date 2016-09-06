package com.example.lemon.firetesting;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EditContent extends AppCompatActivity {

    private TextView TextTopic, TextAnsA, TextAnsB, TextAnsC, TextAnsD, TextAnsCorrect;
    private Button SummitBtn, CancelBtn;
    private DatabaseHelper myDb = new DatabaseHelper(this);
    private int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_content);

        TextTopic = (TextView) findViewById(R.id.editTextTopic);
        TextAnsA = (TextView) findViewById(R.id.editTextAnsA);
        TextAnsB = (TextView) findViewById(R.id.editTextAnsB);
        TextAnsC = (TextView) findViewById(R.id.editTextAnsC);
        TextAnsD = (TextView) findViewById(R.id.editTextAnsD);
        TextAnsCorrect = (TextView) findViewById(R.id.editTextAnsCor);
        SummitBtn = (Button) findViewById(R.id.summitBtn);
        CancelBtn = (Button) findViewById(R.id.cancelBtn);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            ID = Integer.parseInt(extra.get("id").toString());
            TextTopic.setText(extra.getString("topic"));
            String[] ans = extra.get("answers").toString().replaceAll(" ", "").split(",");
            String[] TextAns = {"editTextAnsA", "editTextAnsB", "editTextAnsC", "editTextAnsD"};
            for (int i = ans.length; i > 0; i--) {
                if (i == ans.length) {
                    TextAnsCorrect.setText(ans[i-1]);
                } else {
                    int TextID = getResources().getIdentifier(TextAns[i-1], "id", getPackageName());
                    TextView textView = (TextView) findViewById(TextID);
                    textView.setText(ans[i-1]);
                }
            }
        }

        SummitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean except = true;
                String topic = "";
                if (TextTopic.getText().toString().isEmpty()) {
                    Toast.makeText(EditContent.this, "題目不能為空", Toast.LENGTH_LONG).show();
                    except = false;
                }
                if (TextAnsCorrect.getText().toString().isEmpty()) {
                    Toast.makeText(EditContent.this, "正確答案不能為空", Toast.LENGTH_LONG).show();
                    except = false;
                }
                if (!TextTopic.getText().toString().isEmpty()) {
                    topic = TextTopic.getText().toString();
                }

                ArrayList ans = new ArrayList();
                if (!TextAnsA.getText().toString().isEmpty()) {
                    ans.add(TextAnsA.getText().toString());
                }
                if (!TextAnsB.getText().toString().isEmpty()) {
                    ans.add(TextAnsB.getText().toString());
                }
                if (!TextAnsC.getText().toString().isEmpty()) {
                    ans.add(TextAnsC.getText().toString());
                }
                if (!TextAnsD.getText().toString().isEmpty()) {
                    ans.add(TextAnsD.getText().toString());
                }
                if (ans.size() < 1) {
                    Toast.makeText(EditContent.this, "選擇答案至少要一個", Toast.LENGTH_LONG).show();
                    except = false;
                }

                boolean flag = false;
                for (int i = 0; i < ans.size(); i++) {
                    if (ans.get(i).equals(TextAnsCorrect.getText().toString())) {
                        flag = true;
                        break;
                    }
                }

                if (flag == false) {
                    Toast.makeText(EditContent.this, "正確答案與上方答案有所不符", Toast.LENGTH_LONG).show();
                    except = false;
                }

                if (!TextAnsCorrect.getText().toString().isEmpty()) {
                    ans.add(TextAnsCorrect.getText().toString());
                }

                if (except) {
                    boolean isUpdated = myDb.updateData(ID, topic, ans.toString());
                    if (isUpdated) {
                        Toast.makeText(EditContent.this, "資料更新完成", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.setClass(EditContent.this, EditTopicPage.class);
                        startActivity(intent);
                        Activity activity = EditContent.this;
                        activity.finish();
                    } else {
                        Toast.makeText(EditContent.this, "資料更新失敗", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(EditContent.this, EditTopicPage.class);
                startActivity(intent);
                Activity activity = EditContent.this;
                activity.finish();
            }
        });
    }
}
