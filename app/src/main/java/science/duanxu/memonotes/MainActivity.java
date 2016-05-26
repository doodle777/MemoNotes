package science.duanxu.memonotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private Intent intent;
    private MyDB mydb;
    private List<Memo> memos;
    private SimpleAdapter adapter;
    private String date, subject, content;
    private Information info = new Information();

    @Override
    protected void onResume() {
        super.onResume();

        if(info.mode.equals("newFinish")) {
            date = info.date;
            subject = info.subject;
            content = info.content;

            Memo memo = new Memo(date, subject, content);
            memos.add(memo);
            mydb.add(memo);
        }

        if(info.mode.equals("editFinish")) {
            date = info.date;
            subject = info.subject;
            content = info.content;

            Memo memo = new Memo(date, subject, content);
            for(int i = 0; i < memos.size(); i++) {
                if(memos.get(i).date.equals(date)) {
                    memos.set(i, memo);
                }
            }
        }

        if(info.mode.equals("delete")) {
            date = info.date;
            subject = info.subject;
            content = info.content;

            for(int i = 0; i < memos.size(); i++) {
                if(memos.get(i).date.equals(date)) {
                    memos.remove(i);
                }
            }
        }

        info.flag = false;
        info.mode = "new";
        info.date = "";
        info.subject = "";
        info.content = "";
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info.mode = "new";
                startActivity(new Intent(MainActivity.this, DetailActivity.class));
            }
        });

        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mydb.clear();;
        for(int i = 0; i < memos.size(); i++)
            mydb.add(memos.get(i));
    }

    public void initView()
    {
        mydb = new MyDB();
        memos = mydb.memos;

        listView = (ListView) findViewById(R.id.listView);
        adapter = new SimpleAdapter(this, memos, R.layout.item_listview,
                new String[]{"subject", "date", "content"},
                new int[]{R.id.item_subject, R.id.item_date, R.id.item_content});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Memo memo = (Memo)listView.getItemAtPosition(i);
                info.mode = "edit";
                info.subject = memo.subject;
                info.date = memo.date;
                info.content = memo.content;
                startActivity(new Intent(MainActivity.this, DetailActivity.class));
            }
        });
    }
}
