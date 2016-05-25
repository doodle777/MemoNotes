package science.duanxu.memonotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private Intent intent;
    private MyDB mydb;
    private List<Memo> memos;
    private String date, subject, content;
    private Information info = new Information();

    @Override
    protected void onResume() {
        super.onResume();

        if(info.mode.equals("newFinish") && info.flag) {
            date = info.date;
            subject = info.subject;
            content = info.content;

            Memo memo = new Memo(date, subject, content);
            memos.add(memo);
            mydb.add(memo);
            info.mode = "new";
            info.flag = false;
        }
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
    }

    public void initView()
    {
        mydb = new MyDB();
        memos = mydb.memos;

        listView = (ListView) findViewById(R.id.listView);
        SimpleAdapter adapter = new SimpleAdapter(this, memos, R.layout.item_listview,
                new String[]{"subject", "date", "content"},
                new int[]{R.id.item_subject, R.id.item_date, R.id.item_content});
        listView.setAdapter(adapter);
    }
}
