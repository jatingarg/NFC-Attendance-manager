package com.example.shailendra.attendancemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Teacher_ATT_OPT extends AppCompatActivity {
    private String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__att__opt);
        Bundle extra=getIntent().getExtras();
        if(extra!=null) {
            result= getIntent().getStringExtra("result");
        }
    }
    public void View_Att(View v){
        Intent intent = new Intent(getBaseContext(),Display_All_Student_Att.class);
        intent.putExtra("result",result);
        startActivity(intent);
    }
    public void Take_Att(View v){
        try{
           //String[] arr=result.split("::");
         //   INCREMENT_NUM_CLASS obj = new INCREMENT_NUM_CLASS(arr[0],arr[1]);
           // String out_put = obj.execute(arr[0],arr[1]).get();
            //Toast.makeText(Teacher_ATT_OPT.this,out_put, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getBaseContext(),Read_NFC_card.class);
            intent.putExtra("result",result);
            startActivity(intent);
           // Log.d("CREATION",result);
        }catch (Exception e){

        }

    }

    public void Class_Inc(View v){
        try{
            String[] arr=result.split("::");
            INCREMENT_NUM_CLASS obj = new INCREMENT_NUM_CLASS(arr[0],arr[1]);
            String out_put = obj.execute(arr[0],arr[1]).get();
            Toast.makeText(Teacher_ATT_OPT.this,out_put, Toast.LENGTH_SHORT).show();

        }catch (Exception e){

        }

    }
}
