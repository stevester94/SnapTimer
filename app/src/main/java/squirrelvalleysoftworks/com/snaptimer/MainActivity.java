package squirrelvalleysoftworks.com.snaptimer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    private Button startButton;
    private EditText delayTextField;
    private Button hideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Process p = Runtime.getRuntime().exec("su");
        } catch (Exception e) {
            System.err.println("Error requesting root privileges");
        }


        startButton = (Button) findViewById(R.id.showButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                delayTextField = (EditText) findViewById(R.id.delayTextField);
                long timeout = System.currentTimeMillis();

                if(delayTextField.getText().toString().compareTo("") == 0) {
                    timeout = timeout + 7000; //Default 7 seconds
                } else {
                    timeout = Long.parseLong(delayTextField.getText().toString());
                    timeout = timeout * 1000; // Seconds to milliseconds
                    timeout = timeout + System.currentTimeMillis(); // Actual time to be triggered
                }

                Intent newI = new Intent(MainActivity.this, Floater.class);
                Bundle extras = new Bundle();
                extras.putLong("timeout", timeout);
                newI.putExtras(extras);
                startService(newI);
            }
        });

        hideButton = (Button) findViewById(R.id.hideButton);
        hideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, Floater.class));
            }
        });



    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopService(new Intent(MainActivity.this, Floater.class));
    }

}

