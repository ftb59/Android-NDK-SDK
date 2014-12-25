package cn.bjtu.power.powerclient;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import cn.bjtu.power.powercommon.IPowerService;
import cn.bjtu.power.powercommon.PowerResult;
import cn.bjtu.power.powercommon.PowerParams;
import android.view.View.OnClickListener;

public class MainActivity extends ActionBarActivity implements View.OnClickListener, ServiceConnection {

    private static final String TAG = "MainActivity";
    private IPowerService service;
    private EditText number, power;
    private Button button;
    private PowerParams.FunctionCalled type;
    private TextView result;
    private Switch JN;
    private Switch IR;
    private OnClickListener switchlistener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
      //  input = (EditText) findViewById(R.id.input);
        button = (Button) findViewById(R.id.button);
        type = PowerParams.FunctionCalled.ITERATIVE_JAVA;
        JN = (Switch) findViewById(R.id.switch1);
        IR = (Switch) findViewById(R.id.switch2);
        initSwitchListener();
        JN.setOnClickListener(switchlistener);
        IR.setOnClickListener(switchlistener);
        number = (EditText) findViewById(R.id.nb);
        power = (EditText) findViewById(R.id.pow);
        result = (TextView) findViewById(R.id.result);
        button.setOnClickListener(this);
        button.setEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
       if (!super.bindService(explicitIntent(this, new Intent(IPowerService.class.getName())), this, BIND_AUTO_CREATE)) {
            Log.e("MainActivity", "Failed to bind to service");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        super.unbindService(this);
    }

    public void onServiceConnected(ComponentName name, IBinder service) {
        this.service = IPowerService.Stub.asInterface(service);
        this.button.setEnabled(true);
    }

    public void onServiceDisconnected(ComponentName name) {
        this.service = null;
        this.button.setEnabled(false);
    }

    public void initSwitchListener() {
        switchlistener = new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (JN.isChecked() && IR.isChecked()) {
                    type = PowerParams.FunctionCalled.ITERATIVE_JAVA;
                    JN.setText("JAVA");
                    IR.setText("ITERATIVE");
                } else if (!JN.isChecked() && IR.isChecked()) {
                    type = PowerParams.FunctionCalled.ITERATIVE_NATIVE;
                    JN.setText("NATIVE");
                    IR.setText("ITERATIVE");
                } else if (JN.isChecked() && !IR.isChecked()) {
                    type = PowerParams.FunctionCalled.RECURSIVE_JAVA;
                    JN.setText("JAVA");
                    IR.setText("RECURSIVE");
                } else {
                    type = PowerParams.FunctionCalled.RECURSIVE_NATIVE;
                    JN.setText("NATIVE");
                    IR.setText("RECURSIVE");
                }
            }
        };
    }

    public void onClick(View view) {
        final long n;
        final long nbr;
        final long pwr;

        if (!number.getText().toString().matches(".*\\d.*"))
        {
            nbr = 0;
        }
        else
        {
            nbr = Integer.parseInt(number.getText().toString());
        }
        if (!power.getText().toString().matches(".*\\d.*"))
        {
            pwr = 0;
        }
        else
        {
            pwr = Integer.parseInt(power.getText().toString());
        }

        final PowerParams params = new PowerParams(nbr, pwr, type);

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... p) {
                try {
                    long totalTime = SystemClock.uptimeMillis();
                    PowerResult result = MainActivity.this.service.pow(params);
                    totalTime = SystemClock.uptimeMillis() - totalTime;
                    return String.format(
                            "%d^%d=%d\n%d ms", nbr, pwr,
                            result.getResult(), result.getTimeInMillis());
                } catch (RemoteException e) {
                    Log.e("MainActivity", "Failed to communicate with the service", e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                if (result == null) {
                    return;
                } else {
                    MainActivity.this.result.setText(result);
                }
            }
        }.execute();
    }

    // Create an explicit intent to the service for android version >= 5
       public static Intent explicitIntent(Context context, Intent implicitIntent) {
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);
        Intent explicitIntent = new Intent(implicitIntent);
        explicitIntent.setComponent(component);
        return explicitIntent;
    }

}