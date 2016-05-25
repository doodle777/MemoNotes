package science.duanxu.memonotes;

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
        this.put("date", date);
        this.put("subject", subject);
        this.put("content", content);
    }

}