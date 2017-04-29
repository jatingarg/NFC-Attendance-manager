package com.example.shailendra.attendancemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Display_tag_Info extends AppCompatActivity {
    //    private EditText idField;
    private TextView idResult, nameResult, batchResult;
    private TextView imageAddress;
    private ImageView photoResult;

    private String ProfId = null;
    private String course = null;

    private boolean isImageFitToScreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_tag__info);

//        idField = (EditText)findViewById(R.id.editText1);

        idResult = (TextView) findViewById(R.id.textView3);
        nameResult = (TextView) findViewById(R.id.textView5);
        batchResult = (TextView) findViewById(R.id.textView7);
        imageAddress = (TextView) findViewById(R.id.textView9);
        photoResult = (ImageView) findViewById(R.id.photo);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {

            String str = getIntent().getStringExtra("ID");

            String[] result = str.split("::");

            ProfId = result[0];
            course = result[1];
            String id = result[2];

            //  Toast.makeText(getApplicationContext(),id.toString(),Toast.LENGTH_SHORT).show();

            SigninActivity obj = new SigninActivity(this, idResult, nameResult, batchResult, imageAddress, photoResult);
            obj.execute(ProfId, course, id);

        }
    }

}
