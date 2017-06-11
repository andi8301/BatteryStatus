package android.asr.batterystatus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MenuPilihan extends Activity implements View.OnClickListener {
    Button home, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pilihan);

        home = (Button) findViewById(R.id.btnHome);
        about = (Button) findViewById(R.id.btnAbout);


        home.setOnClickListener(this);
        about.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnHome:
                Intent a = new Intent(MenuPilihan.this, HomeActivity.class);
                startActivity(a);
                break;
            case R.id.btnAbout:
                Intent d = new Intent(MenuPilihan.this, AboutActivity.class);
                startActivity(d);
                break;

        }
    }



}