package sg.edu.rp.c346.ps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DailyCAAdapter extends ArrayAdapter <DailyCA> {
    private ArrayList<DailyCA> dailyCAS;
    private Context context;
    private TextView tvDG,tvGrade,tvWeek;
    private ImageView imageView;

    public DailyCAAdapter(Context context, int resource, ArrayList<DailyCA> objects){
        super(context, resource, objects);

        dailyCAS = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row, parent, false);

        tvDG = rowView.findViewById(R.id.textViewDG);
        tvGrade = rowView.findViewById(R.id.textViewGrade);
        tvWeek = rowView.findViewById(R.id.textViewWeek);
        imageView = rowView.findViewById(R.id.imageView);

        DailyCA dailyCA = dailyCAS.get(position);

        tvGrade.setText(dailyCA.getDgGrade());
        tvWeek.setText("Week " + dailyCA.getWeek());

        imageView.setImageResource(R.drawable.dg);


        return rowView;
    }
}
