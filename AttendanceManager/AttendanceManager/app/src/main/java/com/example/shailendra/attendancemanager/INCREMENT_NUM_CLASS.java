package com.example.shailendra.attendancemanager;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by shailendra on 25/4/17.
 */

public class INCREMENT_NUM_CLASS extends AsyncTask<String,Void,String> {

    private Context context;

    private String result="Result";
    private String ID;
    private String Course_ID;
    private String ip="10.1.4.18";
    private String link="http://"+ip+"/nfc/allCommands.php";

    public INCREMENT_NUM_CLASS(String ID,String Course_ID)
    {
        this.ID=ID;
        this.Course_ID=Course_ID;
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

            while((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }

            return sb.toString();
        }
        catch(Exception e) {

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
            ID = (String)arg0[0];
            Course_ID = (String)arg0[1];
            String data=getEncoding("commandID","3");
            data+="&"+getEncoding("profID",ID);
            data+="&"+getEncoding("Course_ID",Course_ID);
            String reader = connectionWork(data);
            result=reader;
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


    }
}
