package es.cice.mycustomeventreceiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import es.cice.mycustomeventreceiver.receivers.MyCustomAlarmReceiver;
import es.cice.mycustomeventreceiver.receivers.MyCustomEventReceiver;

public class MainActivity extends AppCompatActivity {

    private MyCustomEventReceiver rcv1;
    private EditText alarmET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcv1=new MyCustomEventReceiver();
        alarmET= (EditText) findViewById(R.id.myET);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter iFilter=new IntentFilter(MyCustomEventReceiver.MY_CUSTOM_EVENT);
        registerReceiver(rcv1,iFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(rcv1);
    }

    public void sendMyEvent1(View v){
        Intent intent=new Intent(MyCustomEventReceiver.MY_CUSTOM_EVENT);
        sendBroadcast(intent);
    }

    public void sendMyEvent2(View v){
        Intent intent=new Intent(this, MyCustomAlarmReceiver.class);
        PendingIntent pIntent=PendingIntent.getBroadcast(this,22222,intent,0);
        AlarmManager alarmService= (AlarmManager) getSystemService(ALARM_SERVICE);
        int elapsedTime=Integer.parseInt(alarmET.getText().toString());
        alarmService.set(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + elapsedTime*1000,pIntent);

    }

}
