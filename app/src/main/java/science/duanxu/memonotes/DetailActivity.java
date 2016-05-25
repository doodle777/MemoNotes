package science.duanxu.memonotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    private EditText edSubject, edContent;
    private String date, subject, content, mode;
    private Information info = new Information();

    @Override
    protected void onPause() {
        super.onPause();

        date = new Date().toString();
        subject = edSubject.getText().toString();
        content = edContent.getText().toString();
        if(!(subject.equals("") && content.equals(""))) {
            if(subject.equals("")) subject = "主题为空";
            if(content.equals("")) content = "内容为空";

            if(mode.equals("edit"))
                info.mode = "editFinish";
            else
                info.mode = "newFinish";

            info.date = date;
            info.subject = subject;
            info.content = content;
            info.flag = true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        edSubject = (EditText)findViewById(R.id.et_subject);
        edContent = (EditText)findViewById(R.id.et_content);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mode = info.mode;
        // 点击编辑
        if(mode.equals("edit")) {
            date = info.date;
            subject= info.subject;
            content = info.content;

            edSubject.setText(subject);
            edContent.setText(content);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
