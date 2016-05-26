package science.duanxu.memonotes;

import android.util.Log;
import android.widget.SimpleAdapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Memo extends HashMap<String, String>{
    String date;
    String subject;
    String content;

    public Memo(String date, String subject, String content){
        this.date = date;
        this.subject = subject;
        this.content = content;
        DateFormat df = SimpleDateFormat.getDateTimeInstance();
        DateFormat sdf = new SimpleDateFormat("MM/dd");
        try {
            Date t = df.parse(date);
//            Log.i("INFO", t.toString());
            this.put("date", sdf.format(t));
            this.put("subject", subject);
            this.put("content", content);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}