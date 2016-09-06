package com.example.lemon.firetesting;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditTopicPage extends ListActivity implements ListView.OnItemClickListener {

    private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyAdapter myAdapter = new MyAdapter(this);
        list = getData();
        setListAdapter(myAdapter);
    }



    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
        DatabaseHelper myDb = new DatabaseHelper(this);
        List<Map<String, Object>> exam = new ArrayList<Map<String, Object>>();
        exam = myDb.getData();
        for (int i = 0; i < exam.size(); i++) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("id", exam.get(i).get("id").toString());
            item.put("topic", exam.get(i).get("topic").toString());
            String[] ans = exam.get(i).get("answers").toString().replaceAll(" ", "").split(",");
            String comAns = "";
            for(int j = 0; j < ans.length-1; j++) {
                comAns += (j+1) + "." + ans[j];
                if (j+1 < ans.length-1) {
                    comAns += ", ";
                }
            }
            item.put("answers", comAns);
            list2.add(item);
        }
        return list2;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    public final class MyView {
        public TextView Topic;
        public TextView ID;
        public TextView Answers;
        public Button EditBtn;
        public Button DeleteBtn;
    }

    public class MyAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        public MyAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            MyView myviews = new MyView();
            view = inflater.inflate(R.layout.activity_edit_topic_page, null);
            myviews.Topic = (TextView) view.findViewById(R.id.EditTopic);
            myviews.ID = (TextView) view.findViewById(R.id.EditID);
            myviews.Answers = (TextView) view.findViewById(R.id.EditAns);
            myviews.EditBtn = (Button) view.findViewById(R.id.EditBtn);
            myviews.DeleteBtn = (Button) view.findViewById(R.id.DeleteBtn);

            myviews.Topic.setText(list.get(i).get("topic").toString());
            myviews.ID.setText(list.get(i).get("id").toString());
            myviews.Answers.setText(list.get(i).get("answers").toString());
            myviews.EditBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                }
            });
            myviews.DeleteBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                }
            });
            return view;
        }
    }

}
