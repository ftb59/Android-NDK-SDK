package cn.bjtu.power.hellocustomui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CustomUI extends ActionBarActivity implements View.OnClickListener {


    private final SimpleDateFormat _sdfWatchTime = new SimpleDateFormat("hh:mm");
    private final SimpleDateFormat _date = new SimpleDateFormat("EEEE dd-MM-yyyy");
    private final SimpleDateFormat _hours = new SimpleDateFormat("HH");
    private TextView _time;
    private TextView _ampm;
    private TextView _datetxt;
    BroadcastReceiver _broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_ui);
        _time = (TextView) findViewById(R.id.hour);
        _time.setText(_sdfWatchTime.format(new Date()));
        _ampm = (TextView) findViewById(R.id.ampm);
        if (Integer.parseInt(_hours.format(new Date())) > 12)
            _ampm.setText("PM");
        else
            _ampm.setText("AM");
        _datetxt = (TextView) findViewById(R.id.date);
        _datetxt.setText(_date.format(new Date()));

        ((ImageButton) findViewById(R.id.callButton)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.lockButton)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.smsButton)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.photoButton)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.ieButton)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.musicButton)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String viewName = v.getResources().getResourceName(v.getId());
        viewName = viewName.substring(viewName.indexOf("/") + 1, viewName.indexOf("Button"));
        Toast toast = new Toast(getBaseContext());
        toast = Toast.makeText(getApplicationContext(), viewName, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        _broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context ctx, Intent intent)
            {
                if (intent.getAction().compareTo(Intent.ACTION_TIME_TICK) == 0) {
                    _time.setText(_sdfWatchTime.format(new Date()));
                    if (Integer.parseInt(_hours.format(new Date())) > 12)
                        _ampm.setText("PM");
                    else
                        _ampm.setText("AM");
                    _datetxt.setText(_date.format(new Date()));
                }
            }
        };

        registerReceiver(_broadcastReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));
    }

    @Override
    public void onStop()
    {
        super.onStop();
        if (_broadcastReceiver != null)
            unregisterReceiver(_broadcastReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_custom_ui, menu);
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
