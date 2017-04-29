package com.example.shailendra.attendancemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Teacher_Login extends AppCompatActivity {


    private EditText usr_name,Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
    }

    public void loginPost(View v){
        usr_name = (EditText) findViewById(R.id.editText);
        Pass= (EditText) findViewById(R.id.editText2);

        //Toast.makeText(getApplicationContext(),Integer.toString(100),Toast.LENGTH_SHORT).show();
        try {
            usr_name = (EditText) findViewById(R.id.editText);
            Pass = (EditText) findViewById(R.id.editText2);

            //Toast.makeText(getApplicationContext(),usr_name.getText().toString(),Toast.LENGTH_SHORT).show();

            Activity obj = new Activity(this, "Teacher");

            String result = obj.execute(usr_name.getText().toString(), Pass.getText().toString(), "Teacher").get();
            if(result.equals("Wrong Pass")){
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(getBaseContext(),Teacher_Course.class);
                intent.putExtra("result",result);
                startActivity(intent);
            }


           // Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {

        }

        //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

    }
}
