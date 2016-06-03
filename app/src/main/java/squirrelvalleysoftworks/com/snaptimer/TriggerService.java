package squirrelvalleysoftworks.com.snaptimer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by smackey2 on 5/31/2016.
 */
public class TriggerService extends Service {
    TriggerService() {
        super();
        System.out.println("Constructed");
    }
    @Override
    public void onCreate() {
        Log.d("Trigger", "Trigger service created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Trigger", "Trigger service started");
        try {
            Process p = Runtime.getRuntime().exec("su");
            OutputStream stdIn = p.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdIn));

            writer.write("input keyevent 24\n");
            writer.flush();
        } catch (Exception e) {
            System.err.println("Error triggering");
        }
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Trigger", "Trigger service destroyed");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
