package science.duanxu.memonotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyDB {
    public  SQLiteDatabase db;
    public  List<Memo> memos;
    private String dbPath;
    private String dbName = "MemoNotes";
    private String tableName = "memos";
    private String cmd = "";

    public MyDB(){
        memos = new ArrayList<>();
        dbPath = Environment.getExternalStorageDirectory().getPath();
        dbName = dbPath + "/" + dbName + ".db";
        db = SQLiteDatabase.openOrCreateDatabase(dbName, null);
        cmd = "CREATE TABLE IF NOT EXISTS " + tableName + " (date VARCHAR, subject VARCHAR, content VARCHAR)";
        db.execSQL(cmd);

        Cursor cursor = db.query(tableName, null, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            String subject = cursor.getString(0);
            String date = cursor.getString(1);
            String content = cursor.getString(2);
            memos.add(new Memo(subject, date, content));
            cursor.moveToNext();

            Log.i("INFO", subject + "\t" + date + "\t" + content);
        }
    }

    public void add(Memo memo) {
        memos.add(memo);
        ContentValues cv = new ContentValues();
        cv.put("date", memo.date);
        cv.put("subject", memo.subject);
        cv.put("content", memo.content);

        db.insert(tableName, null, cv);
    }


}
