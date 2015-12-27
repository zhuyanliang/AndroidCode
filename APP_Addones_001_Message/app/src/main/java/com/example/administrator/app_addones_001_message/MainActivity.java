package com.example.administrator.app_addones_001_message;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.util.Log;
import android.os.HandlerThread;

public class MainActivity extends AppCompatActivity {

    private Button mButton = null;
    private final String TAG = "MessageTest";
    private Thread myThread = null;
    private int ButtonCount = 0;
    private MyThread myThread2 = null;
    private Handler mHandler = null;
    private int mMessageCount = 0;
    private HandlerThread myThread3 = null;
    private Handler mHandler3 = null;

    class MyRunable implements Runnable{
        public void run(){
            int count = 0;
            for(;;){
                Log.d(TAG,"MyThread "+count++);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    class MyThread extends Thread{
        private Looper mLooper = null;
        @Override
        public void run() {
            super.run();
            Looper.prepare();
            synchronized (this){
                mLooper = Looper.myLooper();
                notifyAll();
            }
            Looper.loop();
        }
        public Looper getLooper(){
            if(!isAlive()){
                return null;
            }
            synchronized (this){
                while(isAlive()&&mLooper == null){
                    try{
                        wait();
                    }catch (InterruptedException e){
                    }
                }
            }
            return mLooper;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mButton = (Button)findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d(TAG, "Send Message " + ButtonCount++);
                Message msg = new Message();
                mHandler.sendMessage(msg);
                mHandler3.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG,"get Message for Thread3 "+mMessageCount++);
                    }
                });
            }
        });

        myThread = new Thread(new MyRunable(),"MeaageThreadTest");
        myThread.start();

        myThread2 = new MyThread();
        myThread2.start();

        myThread3 = new HandlerThread("MessageTestThread3");
        myThread3.start();

        mHandler = new Handler(myThread2.getLooper(),new Handler.Callback(){
            @Override
            public boolean handleMessage(Message msg) {
                Log.d(TAG,"get Message "+mMessageCount++);
                return false;
            }
        });

        mHandler3 = new Handler(myThread3.getLooper());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
