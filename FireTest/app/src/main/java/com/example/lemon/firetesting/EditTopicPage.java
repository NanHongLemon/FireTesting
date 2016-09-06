package com.example.lemon.firetesting;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditTopicPage extends ListActivity implements ListView.OnItemClickListener {

    private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    private DatabaseHelper myDb = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyAdapter myAdapter = new MyAdapter(this);
        list = getData();
        setListAdapter(myAdapter);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> exam = new ArrayList<Map<String, Object>>();
        exam = myDb.getData();
        for (int i = 0; i < exam.size(); i++) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("id", exam.get(i).get("id").toString());
            item.put("topic", exam.get(i).get("topic").toString());
            item.put("answers", exam.get(i).get("answers").toString());
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
        public View getView(final int i, View view, ViewGroup viewGroup) {
            final MyView myviews = new MyView();
            view = inflater.inflate(R.layout.activity_edit_topic_page, null);
            myviews.Topic = (TextView) view.findViewById(R.id.EditTopic);
            myviews.ID = (TextView) view.findViewById(R.id.EditID);
            myviews.Answers = (TextView) view.findViewById(R.id.EditAns);
            myviews.EditBtn = (Button) view.findViewById(R.id.EditBtn);
            myviews.DeleteBtn = (Button) view.findViewById(R.id.DeleteBtn);

            myviews.Topic.setText(list.get(i).get("topic").toString());
            myviews.ID.setText(list.get(i).get("id").toString());
            String[] ans = list.get(i).get("answers").toString().replaceAll(" ", "").split(",");
            String comAns = "";
            for(int j = 0; j < ans.length-1; j++) {
                comAns += (j+1) + "." + ans[j] + " ";
            }
            myviews.Answers.setText(comAns);
            myviews.EditBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(EditTopicPage.this, EditContent.class);
                    intent.putExtra("id", list.get(i).get("id").toString());
                    intent.putExtra("topic", list.get(i).get("topic").toString());
                    intent.putExtra("answers", list.get(i).get("answers").toString());
                    startActivity(intent);
                    Activity activity = EditTopicPage.this;
                    activity.finish();
                }
            });
            myviews.DeleteBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    final int id = Integer.parseInt(list.get(i).get("id").toString());
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(EditTopicPage.this);
                    alertDialog.setTitle("確定要刪除?");
                    alertDialog.setNeutralButton("確定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            boolean result = myDb.deleteData(id);
                            if (result) {
                                Intent intent = new Intent();
                                intent.setClass( EditTopicPage.this, EditTopicPage.class );
                                startActivity( intent );
                                Activity activity = EditTopicPage.this;
                                activity.finish();
                                Toast.makeText(EditTopicPage.this, "資料刪除成功", Toast.LENGTH_LONG);
                            } else {
                                Toast.makeText(EditTopicPage.this, "資料刪除失敗", Toast.LENGTH_LONG);
                            }
                        }
                    });
                    alertDialog.setNegativeButton( "取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alertDialog.show();
                }

            });
            return view;
        }
    }

}
