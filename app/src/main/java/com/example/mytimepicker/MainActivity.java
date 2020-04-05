package com.example.mytimepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView  textView2;
    TimePicker timePicker;
    Button button;
    int hour,min;
    static final int TimeDialogId=99;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCurrentTimeOnView();
        addListenerOnButton();
    }
    public void setCurrentTimeOnView(){
        textView2 = (TextView) findViewById(R.id.textView2);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        final Calendar calender = Calendar.getInstance();
        hour = calender.get(Calendar.HOUR_OF_DAY);
        min = calender.get(Calendar.MINUTE);
        textView2.setText(pad(hour)+":"+pad(min));
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(min);
    }
    public void addListenerOnButton(){
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TimeDialogId);
            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id){
        switch (id){
            case TimeDialogId:
                return new TimePickerDialog(this,timePickerListener,hour,min,false);
        }
        return null;
    }
    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour = hourOfDay;
            min = minute;
            textView2.setText(pad(hour)+":"+pad(min));
            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(min);
        }
    };
    private static String pad(int c){
        if (c>=10)
            return String.valueOf(c);
        else
            return "0"+String.valueOf(c);
    }
}
