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

public class Student_Course extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__course);
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
                            Get_Student_Att obj = new Get_Student_Att(Id,String.valueOf(myLV.getItemAtPosition(position)));
                            String result = obj.execute(Id,String.valueOf(myLV.getItemAtPosition(position))).get();
                            Log.d("CREATION",result);
                            Intent intent = new Intent(getBaseContext(),Student_Single_Course_Att.class);
                            intent.putExtra("result",result);
                            startActivity(intent);
                        }catch (Exception e){

                        }
                   // Toast.makeText(Student_Course.this, selected, Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
}
