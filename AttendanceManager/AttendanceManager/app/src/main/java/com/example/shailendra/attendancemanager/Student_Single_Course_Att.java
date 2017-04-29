package com.example.shailendra.attendancemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Student_Single_Course_Att extends AppCompatActivity {
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__single__course__att);
        Bundle extra=getIntent().getExtras();
        if(extra!=null) {
            result= getIntent().getStringExtra("result");
//            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

            TextView tv = (TextView)findViewById(R.id.att);
            tv.setText(result);
        }

    }
}
