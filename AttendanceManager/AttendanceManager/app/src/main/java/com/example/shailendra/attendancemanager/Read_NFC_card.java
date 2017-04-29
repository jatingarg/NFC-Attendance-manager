package com.example.shailendra.attendancemanager;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Read_NFC_card extends AppCompatActivity {

    public static final String ERROR_DETECTED = "No NFC Tag";
    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    IntentFilter writeTagFilters[];
    boolean writeMode;
    Tag myTag;
    Context context;

    TextView tvNFCContent;
    String result;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read__nfc_card);
        context = this;
        Bundle extra=getIntent().getExtras();
        if(extra!=null) {
            result= getIntent().getStringExtra("result");
        }

        tvNFCContent = (TextView) findViewById(R.id.nfc_contents);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {

            Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
            finish();
        }
        readFromIntent(getIntent());

        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        tagDetected.addCategory(Intent.CATEGORY_DEFAULT);

    }

    private void readFromIntent(Intent intent)
    {
        String currentState = intent.getAction();

        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(currentState)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(currentState)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(currentState))
        {
            Parcelable[] parcelData = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] nfcRecordArr = null;

            if(parcelData == null)
            {
                return;
            }
            else
            {
                nfcRecordArr = new NdefMessage[parcelData.length];
                for (int i = 0; i < parcelData.length; i++)
                {
                    nfcRecordArr[i] = (NdefMessage) parcelData[i];
                }
            }
            buildTagViews(nfcRecordArr);
        }
    }

    /*

    shailendra code

     */


    private void Read_Data_From_DataBase(String text){
        Intent intent = new Intent(getBaseContext(), Display_tag_Info.class);
        String copy=result;
        result=result+"::"+text;
        Log.d("CREATION",result);
        intent.putExtra("ID", result);
        startActivity(intent);
        result=copy;
    }

    private boolean isValidRecordArr(NdefMessage[] nfcRecordArr)
    {
        return (nfcRecordArr.length!=0 && nfcRecordArr!=null);
    }


    private void buildTagViews(NdefMessage[] nfcRecordArr)
    {
        String resultMsg = "No Message";
        String recordUTFversion = "UTF-8";

        int recordLangversion;
        byte[] bytArr = null;

        try
        {
            if(isValidRecordArr(nfcRecordArr))
            {
                bytArr = nfcRecordArr[0].getRecords()[0].getPayload();

                if((bytArr[0]&128)!=0)
                {
                    recordUTFversion = "UTF-16";
                }

                recordLangversion = bytArr[0] & 0063;

                resultMsg = new String(bytArr, recordLangversion + 1, bytArr.length - recordLangversion - 1, recordUTFversion);

                tvNFCContent.setText(" Tag Data : " + resultMsg);

                if(!resultMsg.equals(""))
                    Read_Data_From_DataBase(resultMsg);
            }
            else return;
        }
        catch (Exception e)
        {

        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        readFromIntent(intent);
        if(NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())){
            myTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        WriteModeOff();
    }

    @Override
    public void onResume(){
        super.onResume();
        WriteModeOn();
    }


    private void WriteModeOn(){
        writeMode = true;
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, writeTagFilters, null);
    }

    private void WriteModeOff(){
        writeMode = false;
        nfcAdapter.disableForegroundDispatch(this);
    }
}

