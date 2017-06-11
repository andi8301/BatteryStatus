package android.asr.batterystatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView txtBatteryStatus,txtBatteryPlug,txtBatteryHealth,txtBatteryVolt,txtBatteryTemp,txtBatteryLevel,txtBatteryTech;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtBatteryStatus =(TextView) findViewById(R.id.txtStat);
        txtBatteryPlug = (TextView) findViewById(R.id.txtPlug);
        txtBatteryLevel = (TextView) findViewById(R.id.txtLevel);
        txtBatteryHealth = (TextView) findViewById(R.id.txtHealth);
        txtBatteryVolt = (TextView) findViewById(R.id.txtVolt);
        txtBatteryTemp = (TextView) findViewById(R.id.txtTemp);
        txtBatteryTech =(TextView) findViewById(R.id.txtTech);

        intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

        final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                //Status
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
                boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status ==BatteryManager.BATTERY_STATUS_FULL;
                if (isCharging)
                    txtBatteryStatus.setText("  Battery Status            :  Charging");
                else
                    txtBatteryStatus.setText("  Battery Status            :  No Charged ");

                //Power Plug
                int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,-1);
                boolean isUSBCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB || chargePlug ==BatteryManager.BATTERY_PLUGGED_AC;
                if (isUSBCharge)
                    txtBatteryPlug.setText("  Power Source             :  USB");
                else
                    txtBatteryPlug.setText("  Power Source             :  AC");

                //Level Batre
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
                float batteryPct = (level/(float)scale)*100;
                txtBatteryLevel.setText("  Battery Level               :  "+batteryPct+"%");

                //batre volt
                int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE,-1);
                txtBatteryVolt.setText("  Battery Voltage           :  "+voltage+ "mV");

                //Temperature
                int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,-1);
                txtBatteryTemp.setText("  Battery Temperature  :  " +temperature+"*F");

                //Technology
                String tech = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
                txtBatteryTech.setText("  Battery Technology     : "  +tech);

                //Health
                int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH,-1);
                switch (health){
                    case BatteryManager.BATTERY_HEALTH_COLD:
                        txtBatteryHealth.setText("  Battery Health             :  COLD");
                        break;
                    case BatteryManager.BATTERY_HEALTH_DEAD:
                        txtBatteryHealth.setText("  Battery Health             :  DEAD");
                        break;
                    case BatteryManager.BATTERY_HEALTH_GOOD:
                        txtBatteryHealth.setText("  Battery Health             :  GOOD");
                        break;
                    case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                        txtBatteryHealth.setText("  Battery Health             :  OVERHEAT");
                        break;
                    case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                        txtBatteryHealth.setText("  Battery Health             :  UNKNOWN");
                        break;
                    default:
                        break;
                }


            }
        };
        //Memanggil fungsi
        HomeActivity.this.registerReceiver(broadcastReceiver,intentFilter);
    }
}
