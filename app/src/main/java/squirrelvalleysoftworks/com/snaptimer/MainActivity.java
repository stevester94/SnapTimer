package squirrelvalleysoftworks.com.snaptimer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    private Button startButton;
    private EditText delayTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Process p = Runtime.getRuntime().exec("su");
        } catch (Exception e) {
            System.err.println("Error requesting root privileges");
        }

        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {startButtonHandler();}
        });

        delayTextField = (EditText) findViewById(R.id.delayTextField);

    }

    private void startButtonHandler() {
        System.out.println("Clicked!");
        Intent intent = new Intent(this, TriggerService.class);
        PendingIntent pending = PendingIntent.getService(this, 0, intent, 0);

        long timeOut = Long.parseLong(delayTextField.getText().toString());
        timeOut = timeOut * 1000; // Seconds to milliseconds
        timeOut = timeOut + System.currentTimeMillis(); // Actual time to be triggered

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC, timeOut, pending);

    }
}

