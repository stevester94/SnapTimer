package squirrelvalleysoftworks.com.snaptimer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Button startButton;

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

    }

    private void startButtonHandler() {
        System.out.println("Clicked");

        Intent intent = new Intent(MainActivity.this, TriggerService.class);
        PendingIntent pending = PendingIntent.getService(MainActivity.this, 0, intent, 0);

        AlarmManager manager = (AlarmManager)

    }
}

