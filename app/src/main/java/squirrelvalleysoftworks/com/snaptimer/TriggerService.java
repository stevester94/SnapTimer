package squirrelvalleysoftworks.com.snaptimer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by smackey2 on 5/31/2016.
 */
public class TriggerService extends Service {
    @Override
    public void onCreate() {
        Log.d("Trigger", "Trigger service created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Trigger", "Trigger service started");
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
