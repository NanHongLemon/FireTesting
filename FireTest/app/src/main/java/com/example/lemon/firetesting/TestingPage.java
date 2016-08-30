package com.example.lemon.firetesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import junit.framework.Test;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class TestingPage extends AppCompatActivity {

    private ArrayList Ans = new ArrayList();
    private HashMap Exam = new HashMap();
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private TextView TestTopic;
    private TextView NextPopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_page);

        ArrayList ans1 = new ArrayList();
        ans1.add("濕毛巾摀口鼻逃生");
        ans1.add("躲入避難房間關門開窗呼救");
        ans1.add("躲入浴室等待救援");
        ans1.add("躲入避難房間關門開窗呼救");

        ArrayList ans2 = new ArrayList();
        ans2.add("進安全梯向下逃生");
        ans2.add("向上逃生至頂樓");
        ans2.add("坐電梯迅速逃生");
        ans2.add("進安全梯向下逃生");

        ArrayList ans3 = new ArrayList();
        ans3.add("可");
        ans3.add("不可");
        ans3.add("不可");

        ArrayList ans4 = new ArrayList();
        ans4.add("塑膠門浴室");
        ans4.add("全木門房間");
        ans4.add("玻璃門寢室");
        ans4.add("門上有氣窗之鐵門房間");
        ans4.add("全木門房間");

        ArrayList ans5 = new ArrayList();
        ans5.add("取乾粉滅火器朝油鍋噴灑");
        ans5.add("鍋蓋覆蓋");
        ans5.add("關閉瓦斯");
        ans5.add("水龍頭取水噴灑");
        ans5.add("鍋蓋覆蓋");

        Exam.put("火災現場濃煙密布難以逃生時，較佳方法應選擇?", ans1);
        Exam.put("當一棟12層大樓火災，10樓起火，而你在11樓，當你選擇逃生時應？", ans2);
        Exam.put("火場逃生時可使用塑膠袋蒐集空氣，以利逃生時可吸到塑膠袋內新鮮空氣?", ans3);
        Exam.put("火災現場無法逃生時，應選擇正確避難房間，下列房間何者正確?", ans4);
        Exam.put("準備晚餐油炸食物時，油炸油突然達燃點起火，請問第一時間該如何滅火?", ans5);

        ArrayList TopicArray = new ArrayList();
        for (Object key : Exam.keySet()) {
            TopicArray.add(key.toString());
        }

        ShowNextData(Exam, TopicArray, 0);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        TestTopic = (TextView) findViewById(R.id.TestTopic);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        TestTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void MatchAnswer(HashMap exam, ArrayList topicArray, int n, View view) {
        int id = view.getId();
        Button PushButton = (Button) findViewById(id);
        String userAns = PushButton.getText().toString();
        String topicKey = topicArray.get(n).toString();
        testTopic.setText(topicKey);
        ArrayList Answers = (ArrayList) exam.get(topicKey);
        for (int i = 0; i < Answers.size()-1; i++) {
            if ()
        }
    }

    public void HideInformation() {

    }

    public void OpenInformation() {

    }

    public void ShowNextData(HashMap exam, ArrayList topicArray, int n) {
        TextView testTopic = (TextView) findViewById(R.id.TestTopic);
        String topicKey = topicArray.get(n).toString();
        testTopic.setText(topicKey);
        ArrayList Answers = (ArrayList) exam.get(topicKey);
        for (int i = 0; i < Answers.size()-1; i++) {
            int btnID = getResources().getIdentifier("button"+i, "id", getPackageName());
            Button button = (Button) findViewById(btnID);
            button.setText(Answers.get(i).toString());
        }
    }

    public void nextTopic() {

    }
}
