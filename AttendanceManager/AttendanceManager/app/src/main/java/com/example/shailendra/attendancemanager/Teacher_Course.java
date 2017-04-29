package com.example.shailendra.attendancemanager;

import android.content.Intent;
import android.net.sip.SipAudioCall;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Teacher_Course extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__course);
        Bundle extra=getIntent().getExtras();
        if(extra!=null){

            String result =getIntent().getStringExtra("result");

            Toast.makeText(getApplicationContext(),result.toString(),Toast.LENGTH_SHORT).show();

            String[] arr=result.split("::");
            final String Id=arr[1];
            String str=arr[0];
            String[] courses=str.split("=");
            ListAdapter daAdap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, courses);

            final ListView myLV = (ListView)findViewById(R.id.myLV);

            myLV.setAdapter(daAdap);

            myLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selected = "Course is " + String.valueOf(myLV.getItemAtPosition(position));
                    try{

                        Intent intent = new Intent(getBaseContext(),Teacher_ATT_OPT.class);
                        String result=Id+"::"+String.valueOf(myLV.getItemAtPosition(position));
                        intent.putExtra("result",result);
                        startActivity(intent);

                    }catch (Exception e){

                    }
                    Toast.makeText(Teacher_Course.this, selected, Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
}
