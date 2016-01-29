package edu.up.cs371.carstereo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.SeekBar;


public class MainActivity extends ActionBarActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, View.OnLongClickListener {

    private Button powerButton;
    private Button amButton;
    private Button fmButton;
    private Button presetButton1;
    private Button presetButton2;
    private Button presetButton3;
    private Button presetButton4;
    private Button presetButton5;
    private Button presetButton6;

    private int dS[] = {550,600,650,700,750,800,909, 929,949,969,989,1009};

    private TextView volume;
    private TextView tuner;
    private TextView station;
    private TextView presets;
    private TextView stationName;
    private SeekBar volumeBar;
    private SeekBar tunerBar;
    private int onColor = 0xFF000000;
    private int offColor = 0xFF9f9f9f;
    private int Progress = 0;
    private boolean isOn;
    private boolean am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        powerButton = (Button)findViewById(R.id.powerButton);
        amButton = (Button)findViewById(R.id.amButton);
        fmButton = (Button)findViewById(R.id.fmButton);

        presetButton1 = (Button)findViewById(R.id.presetOneButton);
        presetButton2 = (Button)findViewById(R.id.presetTwoButton);
        presetButton3 = (Button)findViewById(R.id.presetThreeButton);
        presetButton4 = (Button)findViewById(R.id.presetFourButton);
        presetButton5 = (Button)findViewById(R.id.presetFiveButton);
        presetButton6 = (Button)findViewById(R.id.presetSixButton);



        volume = (TextView)findViewById(R.id.volume);
        tuner = (TextView)findViewById(R.id.tuner);
        station = (TextView)findViewById(R.id.station);
        stationName = (TextView)findViewById(R.id.stationName);
        tunerBar = (SeekBar)findViewById(R.id.tunerBar);
        presets = (TextView)findViewById(R.id.presets);

        volume.setTextColor(offColor);
        tuner.setTextColor(offColor);
        station.setTextColor(offColor);
        presets.setTextColor(offColor);
        stationName.setTextColor(offColor);
        isOn = false;
        powerButton.setOnClickListener(this);
        amButton.setOnClickListener(this);
        fmButton.setOnClickListener(this);
        tunerBar.setOnSeekBarChangeListener(this);
        presetButton1.setOnClickListener(this);
        presetButton2.setOnClickListener(this);
        presetButton3.setOnClickListener(this);
        presetButton4.setOnClickListener(this);
        presetButton5.setOnClickListener(this);
        presetButton6.setOnClickListener(this);
        presetButton1.setOnLongClickListener(this);
        presetButton2.setOnLongClickListener(this);
        presetButton3.setOnLongClickListener(this);
        presetButton4.setOnLongClickListener(this);
        presetButton5.setOnLongClickListener(this);
        presetButton6.setOnLongClickListener(this);
        am = false;
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

    @Override
    public void onClick(View view){
        if (view.getId() == R.id.powerButton) {
            if (isOn) {
                volume.setTextColor(offColor);
                tuner.setTextColor(offColor);
                station.setTextColor(offColor);
                stationName.setTextColor(offColor);
                presets.setTextColor(offColor);
            } else {
                volume.setTextColor(onColor);
                tuner.setTextColor(onColor);
                station.setTextColor(onColor);
                stationName.setTextColor(onColor);
                presets.setTextColor(onColor);
            }
            isOn = !isOn;
        }
        else if (view.getId() == R.id.amButton)
        {
            if(isOn) {
                am = true;
                setStationName();
            }
        }
        else if (view.getId() == R.id.fmButton)
        {
            if(isOn) {
                am = false;
                setStationName();
            }
        }
        if (isOn) {
            if (view.getId() == R.id.presetOneButton) {
                setStationValue(dS[0], dS[6 + 0]);
            } else if (view.getId() == R.id.presetTwoButton) {
                setStationValue(dS[1], dS[6 + 1]);
            } else if (view.getId() == R.id.presetThreeButton) {
                setStationValue(dS[2], dS[6 + 2]);
            } else if (view.getId() == R.id.presetFourButton) {
                setStationValue(dS[3], dS[6 + 3]);
            } else if (view.getId() == R.id.presetFiveButton) {
                setStationValue(dS[4], dS[6 + 4]);
            } else if (view.getId() == R.id.presetSixButton) {
                setStationValue(dS[5], dS[6 + 5]);
            }
        }
    }

    private void setStationName()
    {
        if (isOn) {
            double val = 0.0;
            int amVal = 0;
            if (am) {
                val = (Progress / 1000.0) * 117.0;

                amVal = (int) val;
                amVal = 530 + amVal * 10;
                stationName.setText("" + amVal + " AM");
            } else {
                val = (Progress / 1000.0) * 99.0;
                amVal = (int) val;
                amVal = 881 + amVal * 2;
                stationName.setText("" + amVal / 10.0 + " FM");
            }
        }
    }

    private void setStationValue(int amVal, int fmVal){
        if(am){
            stationName.setText("" + amVal + " AM");
        }
        else{
            stationName.setText("" + fmVal/10.0 + " FM");
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar s)
    {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Progress = progress;
        setStationName();

    }

    @Override
    public boolean onLongClick(View view)
    {
        if (isOn) {
            if (view.getId() == R.id.presetOneButton) {
                overridePreset(0);
            } else if (view.getId() == R.id.presetTwoButton) {
                overridePreset(1);
            } else if (view.getId() == R.id.presetThreeButton) {
                overridePreset(2);
            } else if (view.getId() == R.id.presetFourButton) {
                overridePreset(3);
            } else if (view.getId() == R.id.presetFiveButton) {
                overridePreset(4);
            } else if (view.getId() == R.id.presetSixButton) {
                overridePreset(5);
            }
        }
        return false;
    }

    private void overridePreset(int button){
        double val;
        int amVal;
        if (am){
            val = (Progress / 1000.0) * 117.0;
            amVal = (int) val;
            amVal = 530 + amVal * 10;
            dS[button] = amVal;
            stationName.setText("" + amVal  + " AM");
        } else {
            val = (Progress / 1000.0) * 99.0;
            amVal = (int) val;
            amVal = 881 + amVal * 2;
            dS[6+button] = amVal;
            stationName.setText("" + amVal / 10.0 + " FM");
        }
    }

}
