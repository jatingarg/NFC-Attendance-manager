package com.example.shailendra.attendancemanager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by shailendra on 18/4/17.
 */


public class SigninActivity extends AsyncTask<String,Void,String>
{
    private TextView idResult,nameResult,batchResult,imageAddress;
    private ImageView photoResult;
    private Context context;
    private EditText idInput;
    private String result;
    private Bitmap photo;
    private String ip="10.1.4.18";
    private String link="http://"+ip+"/nfc/allCommands.php";

    public SigninActivity(Context context, TextView idResult, TextView nameResult, TextView batchResult, TextView imageAddress, ImageView photoResult)
    {
        this.context = context;
//        this.idInput = idInput;
        this.idResult = idResult;
        this.nameResult = nameResult;
        this.batchResult = batchResult;
        this.imageAddress = imageAddress;
        this.photoResult = photoResult;
    }

    protected void onPreExecute()
    {

    }

    String getResult()
    {
        return result;
    }
    protected String getEncoding(String name,String value)
    {
        try
        {
            String data= URLEncoder.encode(name, "UTF-8") + "=" +
                    URLEncoder.encode(value, "UTF-8");

            return data;
        }
        catch(Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    protected String connectionWork(String data)
    {
        try
        {
            //String link="http://myphpmysqlweb.hostei.com/loginpost.php";

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            // Log.d("CREATION",data);

            conn.setDoOutput(true);
            // Log.d("CREATION","after true");
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write( data );
            wr.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }

            // Log.d("CREATION","after while");

            return sb.toString();
        }
        catch(Exception e) {
            // Log.d("CREATION","Exception e");
            return new String("Exception: " + e.getMessage());
        }
    }


    @Override
    protected String doInBackground(String... arg0)
    {
        try
        {
            String ProfId=arg0[0];
            String course=arg0[1];
            String id=arg0[2];

            Log.d("CREATION","dfsssssssssssssssssssssss");

            String data=getEncoding("commandID","4");
            data +="&"+getEncoding("Prof_ID",ProfId);
            data+="&"+getEncoding("Course_ID",course);
            data+="&"+getEncoding("Stud_ID",id);
            result = connectionWork(data);

            Log.d("CREATION"," here " + data);
            Log.d("CREATION",result);

            /*String link="http://10.20.1.187/w3Tut/getStudentByID.php";

            String data  = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write( data );
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }

            result = sb.toString();
            */
            String arrResult[] = result.split("-");

            if(arrResult[0]=="NA")
            {
                return null;
            }

            String image_url = "http://"+ip+"/nfc/"+arrResult[4];
            InputStream in = new URL(image_url).openStream();
            photo = BitmapFactory.decodeStream(in);

            return result;
        }
        catch(Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }



    @Override
    protected void onPostExecute(String result)
    {
       String arrResult[] = result.split("-");

        if(arrResult[1]=="NA")
        {
            return;
        }

        this.idResult.setText(arrResult[1]);
        this.nameResult.setText(arrResult[0]);
//        this.batchResult.setText("sassdd");
        this.imageAddress.setText(arrResult[4]);
        this.photoResult.setImageBitmap(photo);
    }
}