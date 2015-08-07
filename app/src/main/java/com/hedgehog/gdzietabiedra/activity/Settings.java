package com.hedgehog.gdzietabiedra.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.hedgehog.gdzietabiedra.R;
import com.hedgehog.gdzietabiedra.utils.Biedra;
import com.hedgehog.gdzietabiedra.utils.Const;
import com.hedgehog.gdzietabiedra.utils.Notify;
import com.rey.material.widget.Button;
import com.rey.material.widget.CheckBox;
import com.rey.material.widget.Slider;

public class Settings extends AppCompatActivity {

    CheckBox notificationCheckbox;
    Slider radiusSlider;
    TextView radiusValue;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        notificationCheckbox = (CheckBox) findViewById(R.id.notificationsCheck);
        radiusSlider = (Slider) findViewById(R.id.radiusSlider);
        radiusValue = (TextView) findViewById(R.id.radiusValue);
        saveBtn = (Button) findViewById(R.id.saveBtn);

        //set saved values or default ones
        radiusSlider.setValue(Integer.parseInt(Biedra.readFromPreferences(this, "radiusValue", Const.radiusDefault)), true);
        notificationCheckbox.setChecked(Biedra.readBoolean(this, "radar", Const.radiusCheckDefault));

        radiusValue.setText(Biedra.readFromPreferences(this, "radiusValue", radiusSlider.getValue() + ""));

        radiusSlider.setOnPositionChangeListener(new Slider.OnPositionChangeListener() {
            @Override
            public void onPositionChanged(Slider slider, boolean b, float v, float v1, int i, int i1) {
                radiusValue.setText(slider.getValue() + " m");
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings();
            }
        });
    }

    private void saveSettings() {
        Biedra.saveBoolean(this, "radar", notificationCheckbox.isChecked());
        Biedra.saveToPreferences(this, "radiusValue", radiusSlider.getValue() + "");

        Log.d("data ",Biedra.readFromPreferences(this, "radiusValue", Const.radiusDefault) + " / " + Biedra.readBoolean(this, "radar", Const.radiusCheckDefault));
        if(notificationCheckbox.isChecked()){
            Log.d("TAG", "uruchamiam notyfikacje");
            startService(new Intent(this, Notify.class));
        }else{
            stopService(new Intent(this, Notify.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_settings, menu);
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
