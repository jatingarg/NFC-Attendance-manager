package com.example.shailendra.attendancemanager;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import static java.security.AccessController.getContext;

/**
 * Created by shailendra on 25/4/17.
 */


public class Activity extends AsyncTask<String,Void,String>
{
    private EditText usr_name,Pass;
    private Context context;
    private String About;
    private int flag;
    private String username;
    private String password;
    private String result="Result";
    private String ID;
    private String ip="10.1.4.18";
    private String link="http://"+ip+"/nfc/allCommands.php";

    public Activity(Context context, String About)
    {
        this.context = context;
        this.About=About;
        this.flag=0;
    }

    protected void onPreExecute()
    {

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


   String getResult()
    {
        return result;
    }




    @Override
    protected String doInBackground(String... arg0)
    {

        try
        {
            username = (String)arg0[0];
            password = (String)arg0[1];
           // Log.d("CREATION",username);
            //Log.d("CREATION",password);
            String reader;
          //  result=((String)arg0[2]);
            if(((String)arg0[2]).equals("Student")) {
                String data=getEncoding("commandID","0");
                data +="&"+getEncoding("username",username);
                data+="&"+getEncoding("password",password);
                data+="&"+getEncoding("type","S");
                reader = connectionWork(data);
                //System.out.println("result is "+reader);
               // Log.d("CREATION",data);
               // Log.d("CREATION",reader);
            }
            else{
                String data=getEncoding("commandID","0");
                data +="&"+getEncoding("username",username);
                data+="&"+getEncoding("password",password);
                data+="&"+getEncoding("type","T");
                reader = connectionWork(data);
               // Log.d("CREATION",data);
                //Log.d("CREATION",reader);
            }
            if(reader.equals("no"))
            {
                flag=0;
                result="Wrong Pass";
                return result;
            }
            else
            {
                flag=1;
                ID=reader;
                result=ID;
            }

            if(flag==1){
                if(About.equals("Student"))
                {
                    String data=getEncoding("commandID","8");
                    data+="&"+getEncoding("studID",ID);
                    reader = connectionWork(data);
                    result=reader;

                    //Log.d("CREATION",data);
                    //Log.d("CREATION",result);
                }else if(About.equals("Teacher"))
                {
                    String data=getEncoding("commandID","7");
                    data+="&"+getEncoding("profID",ID);
                    reader = connectionWork(data);
                    result=reader;
                    Log.d("CREATION",data);
                    Log.d("CREATION",result);
                }
            }
            result=result+"::"+ID;
            return result;
        }
        catch(Exception e) {
           // return new String("Exception: " + e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String str)
    {
    /*
        if(flag==1){
            if(About.equals("Student"))
            {
                Log.d("CREATION",result);
                Intent intent = new Intent(getApplicationContext(), Student_Course.class);
                startActivity(intent);

            }else if(About.equals("Teacher"))
            {
               // Intent intent = new Intent(getApplicationContext(), Teacher_Course.class);
               // startActivity(intent);
                Log.d("CREATION",result);
            }
        }
        */

    }
}