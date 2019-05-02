package sg.edu.rp.c346.ps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {
    TextView tv;
    ImageView imageView;
    RadioGroup rg;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Intent i = getIntent();
        final int weekNum = i.getIntExtra("weekNum",0)+1;

        rg = findViewById(R.id.rg);
        imageView = findViewById(R.id.imageViewNew);
        tv = findViewById(R.id.tvNewWeek);
        btn = findViewById(R.id.buttonSubmit);
        imageView.setImageResource(R.drawable.dg);

        tv.setText("Week " + weekNum);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int SelectedButtonID = rg.getCheckedRadioButtonId();
                RadioButton rb = findViewById(SelectedButtonID);
                String grade = rb.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("grade", grade);
                setResult(RESULT_OK, intent);

                finish();
            }
        });
    }
}
