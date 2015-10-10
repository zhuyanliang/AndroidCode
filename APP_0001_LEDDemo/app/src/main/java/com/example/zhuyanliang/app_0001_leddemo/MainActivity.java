package com.example.zhuyanliang.app_0001_leddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean ledon = false;
    Button button = null;
    CheckBox checkBox1 = null;
    CheckBox checkBox2 = null;
    CheckBox checkBox3 = null;
    CheckBox checkBox4 = null;

    class MyButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            ledon = !ledon;
            if(ledon) {
                button.setText("LED ON");
                checkBox1.setChecked(true);
                checkBox2.setChecked(true);
                checkBox3.setChecked(true);
                checkBox4.setChecked(true);
            }
            else {
                button.setText("LED OFF");
                checkBox1.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
            }
        }
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.LED1:
                if (checked)
                {
                    Toast.makeText(getApplicationContext(),"LED1 ON",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"LED1 OFF",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.LED2:
                if (checked)
                {
                    Toast.makeText(getApplicationContext(),"LED2 ON",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"LED2 OFF",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.LED3:
                if (checked)
                {
                    Toast.makeText(getApplicationContext(),"LED3 ON",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"LED3 OFF",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.LED4:
                if (checked)
                {
                    Toast.makeText(getApplicationContext(),"LED4 ON",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"LED4 OFF",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.BUTTON);
        checkBox1 = (CheckBox)findViewById(R.id.LED1);
        checkBox2 = (CheckBox)findViewById(R.id.LED2);
        checkBox3 = (CheckBox)findViewById(R.id.LED3);
        checkBox4 = (CheckBox)findViewById(R.id.LED4);


        button.setOnClickListener(new MyButtonListener());

        /*
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ledon = !ledon;
                if(ledon)
                    button.setText("LED ON");
                else
                    button.setText("LED OFF");
            }
        });
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        button = (Button) findViewById(R.id.BUTTON);
        checkBox1 = (CheckBox) findViewById(R.id.LED1);
        checkBox2 = (CheckBox) findViewById(R.id.LED2);
        checkBox3 = (CheckBox) findViewById(R.id.LED3);
        checkBox4 = (CheckBox) findViewById(R.id.LED4);

        button.setOnClickListener(new MyButtonListener());

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
