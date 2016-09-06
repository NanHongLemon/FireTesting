package com.example.lemon.firetesting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by Lemon on 2016/9/4.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "FireTest.db";
    public static final String TABLE_NAME = "FireTopic_table";
    public static final String TOPIC_COLUMN = "TOPIC";
    public static final String ANSWERS_COLUMN = "ANSWERS";
    public static final String ID_COLUMN = "ID";
    private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TOPIC TEXT NOT NULL, ANSWERS TEXT NOT NULL);");
        initLocalData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public boolean insertData(String Topic, String Answers) {
        Answers = Answers.substring(1, Answers.length()-1);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TOPIC_COLUMN, Topic);
        contentValues.put(ANSWERS_COLUMN, Answers);
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateData(int ID, String Topic, String Answers) {
        Answers = Answers.substring(1, Answers.length()-1);
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(TOPIC_COLUMN, Topic);
        values.put(ANSWERS_COLUMN, Answers);
        long result = db.update(TABLE_NAME, values, ID_COLUMN + "=" + ID, null);
        db.close();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteData(int ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        long result = db.delete(TABLE_NAME, ID_COLUMN + "=" + ID, null );
        db.close();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<Map<String, Object>> getData() {
        List<Map<String, Object>> item = new ArrayList<Map<String, Object>>();

        String[] columns = {ID_COLUMN, TOPIC_COLUMN, ANSWERS_COLUMN};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);

        cursor.moveToFirst();
        do {
            HashMap Exam = new HashMap();
            Exam.put("id", cursor.getInt(cursor.getColumnIndexOrThrow(ID_COLUMN)));
            Exam.put("topic", cursor.getString(cursor.getColumnIndexOrThrow(TOPIC_COLUMN)));
            Exam.put("answers", cursor.getString(cursor.getColumnIndexOrThrow(ANSWERS_COLUMN)));
            item.add(Exam);
        } while (cursor.moveToNext());
        db.close();
        return item;
    }

    public void initLocalData(SQLiteDatabase db) {
        HashMap Exam = new HashMap();
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

        ArrayList ans6 = new ArrayList();
        ans6.add("濕毛巾");
        ans6.add("乾毛巾");
        ans6.add("不需要");
        ans6.add("不需要");

        ArrayList ans7 = new ArrayList();
        ans7.add("廚房裡");
        ans7.add("浴室裡");
        ans7.add("寢室裡");
        ans7.add("逃生大門前");
        ans7.add("逃生大門前");

        ArrayList ans8 = new ArrayList();
        ans8.add("10秒");
        ans8.add("30秒");
        ans8.add("1分鐘");
        ans8.add("2分鐘");
        ans8.add("10秒");

        ArrayList ans9 = new ArrayList();
        ans9.add("2431");
        ans9.add("3241");
        ans9.add("2341");
        ans9.add("3421");
        ans9.add("2341");

        ArrayList ans10 = new ArrayList();
        ans10.add("快速奔跑尋找水源");
        ans10.add("脫除身上衣物");
        ans10.add("手摀臉、停止、躺下並左右翻滾");
        ans10.add("手摀臉、停止、躺下並左右翻滾");

        ArrayList ans11 = new ArrayList();
        ans11.add("立即逃出戶外");
        ans11.add("躲於堅固且不會位移之桌子下");
        ans11.add("躲在衣櫥或櫃子旁");
        ans11.add("將大門打開避免變形無法逃生");
        ans11.add("躲於堅固且不會位移之桌子下");


        Exam.put("火災現場濃煙密布難以逃生時，較佳方法應選擇?", ans1);
        Exam.put("當一棟12層大樓火災，10樓起火，而你在11樓，當你選擇逃生時應？", ans2);
        Exam.put("火場逃生時可使用塑膠袋蒐集空氣，以利逃生時可吸到塑膠袋內新鮮空氣?", ans3);
        Exam.put("火災現場無法逃生時，應選擇正確避難房間，下列房間何者正確?", ans4);
        Exam.put("準備晚餐油炸食物時，油炸油突然達燃點起火，請問第一時間該如何滅火?", ans5);
        Exam.put("火場逃生時應使用濕毛巾摀口鼻或乾毛巾摀口鼻?", ans6);
        Exam.put("家中設置滅火器，放置應為哪裡較佳?", ans7);
        Exam.put("一般市面大小（乾粉滅火器10型）按下開關後持續使用時間為?", ans8);
        Exam.put("請排列滅火器使用順序1.左右掃射起火點，2.拔開安全插銷、拉皮管，3.按壓開關，4.瞄準起火點?", ans9);
        Exam.put("身上著火時該如火處理?", ans10);
        Exam.put("當發生大地震時，應先選擇何種行為較為正確?", ans11);

        for (Object key : Exam.keySet()) {
            String ans = Exam.get(key.toString()).toString();
            ans = ans.substring(1, ans.length()-1);
            ContentValues contentValues = new ContentValues();
            contentValues.put(TOPIC_COLUMN, key.toString());
            contentValues.put(ANSWERS_COLUMN, ans);
            db.insert(TABLE_NAME, null, contentValues);
        }

    }
}
