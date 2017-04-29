package com.example.shailendra.attendancemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Display_All_Student_Att extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__all__student__att);
        Bundle extra=getIntent().getExtras();
        String All_Att = null;
        String[] finalArr;
        if(extra!=null) {
            String result = getIntent().getStringExtra("result");
            String[] arr = result.split("::");
            String Id = arr[0];
            String course_id = arr[1];
            try {
                Get_Teacher_Att obj = new Get_Teacher_Att(Id, course_id);
                All_Att = obj.execute(Id, course_id).get();


                Log.d("CREATION", All_Att);
                Toast.makeText(getApplicationContext(), All_Att, Toast.LENGTH_SHORT).show();

            } catch (Exception e) {

            }

            finalArr = All_Att.split("=");
            ListAdapter daAdap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, finalArr);

            final ListView myLV = (ListView) findViewById(R.id.myLV);

            myLV.setAdapter(daAdap);

//            myLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    String selected = "Course is " + String.valueOf(myLV.getItemAtPosition(position));
//                    try{
//
//                        Intent intent = new Intent(getBaseContext(),Teacher_ATT_OPT.class);
//                        String result=Id+"::"+String.valueOf(myLV.getItemAtPosition(position));
//                        intent.putExtra("result",result);
//                        startActivity(intent);
//
//                    }catch (Exception e){
//
//                    }
//                    Toast.makeText(Display_All_Student_Att.this, selected, Toast.LENGTH_SHORT).show();
//                }
//            });

        //}
        }
    }
}
