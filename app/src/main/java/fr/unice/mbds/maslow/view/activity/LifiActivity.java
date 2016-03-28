package fr.unice.mbds.maslow.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.luciom.opticallbs.SmartLightRunnable;

public class LifiActivity extends AppCompatActivity {


    /*
     @Override
       protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void startService()
    {

        Intent intent =new Intent(this,LifiService.class);


        startService(intent);
    }


    public void stopService()
    {
        Intent intent = new Intent(this,LifiService.class);
        stopService(intent);
    }
*/

    private SmartLightRunnable smartLight = null;
    private Thread lifiThread = null;
    private SmartLightHandler mHandler;
    private String id_filter;
    private String message;
    LifiActivity lifiActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new SmartLightHandler(id_filter, message, lifiActivity);
        smartLight = new SmartLightRunnable(mHandler, getApplicationContext());
        // Toast.makeText(this,"Service started",Toast.LENGTH_LONG).show();
        System.out.println("test service");

    }

    @Override
    public void onResume() {
        super.onResume();
        if(!smartLight.isRecording()) {
            lifiThread = new Thread(smartLight);
            lifiThread.start();
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        if(smartLight.isRecording()) {
            lifiThread.interrupt();
            lifiThread = null;
        }
    }


























    // setContentView(R.layout.activity_lifi);
    // mHandler = new SmartLightHandler((TextView)findViewById(R.id.id_filteredTxtView),(TextView)findViewById(R.id.msgTxtView), this);
    // mHandler = new SmartLightHandler(id_filter, message, this);
    // smartLight = new SmartLightRunnable(mHandler, getApplicationContext());

    // System.out.println("mon message :" + mHandler.getMessage());


    // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    // setSupportActionBar(toolbar);

    //  TextView messageTV = (TextView)findViewById(R.id.id_filteredTxtView);
    // String message = messageTV.getText().toString();

    // System.out.println("mon id_filter est:" + id_filter);

    // Add the Up Action
    //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    /*
    @Override
    public void onResume() {
        super.onResume();
        if(!smartLight.isRecording()) {
            lifiThread = new Thread(smartLight);
            lifiThread.start();
            System.out.println("mon message thread:" + mHandler.getMessage());
        }

        else
        {
            System.out.println("mon message thread:" + mHandler.getMessage());
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        if(smartLight.isRecording()) {
            lifiThread.interrupt();
            lifiThread = null;
        }
    }

*/



}