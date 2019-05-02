package sg.edu.rp.c346.ps;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainPageActivity extends AppCompatActivity {
    private final static int REQUEST_CODE = 1;

    ListView lv;
    ArrayAdapter aa;
    ArrayList<DailyCA> dailyca;

    Button btnInfo, btnAdd, btnEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        lv = findViewById(R.id.lvWeekly);
        btnInfo = findViewById(R.id.buttonINFO);
        btnAdd = findViewById(R.id.buttonADD);
        btnEmail = findViewById(R.id.buttonEmail);

        dailyca = new ArrayList<DailyCA>();
        dailyca.add(new DailyCA("B","C347",1));
        dailyca.add(new DailyCA("C","C347",2));
        dailyca.add(new DailyCA("A","C347",3));

        aa = new DailyCAAdapter(this,R.layout.row,dailyca);
        lv.setAdapter(aa);

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rpIntent = new Intent(Intent.ACTION_VIEW);
                rpIntent.setData(Uri.parse("https://www.rp.edu.sg/soi/full-time-diplomas/details/r47"));
                startActivity(rpIntent);

            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"jason_lim@rp.edu.sg"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Test Email from C347");
                email.putExtra(Intent.EXTRA_TEXT,"");
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainPageActivity.this,AddActivity.class);
                int weekNum = dailyca.get(dailyca.size()-1).getWeek();
                i.putExtra("weekNum",weekNum);
                startActivityForResult(i,REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent dataIntent) {
        super.onActivityResult(requestCode, resultCode, dataIntent);

        switch (requestCode) {

            case REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    String grade = dataIntent.getStringExtra("grade");
                    dailyca.add(new DailyCA(grade,"C347",dailyca.get(dailyca.size() - 1).getWeek()+1));
                    aa.notifyDataSetChanged();
                }
        }
    }
}
