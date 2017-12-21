// https://developer.android.com/guide/components/bound-services.html#Binder
package org.jrmsdev.jcmob;

import java.lang.String;
import android.os.Binder;
import android.os.IBinder;
import android.content.Intent;
import android.app.Service;
import android.util.Log;

import go.jcmob.Jcmob;

public class JcmobService extends Service {

    public class LocalBinder extends Binder {
        JcmobService getService () {
            Log.d ("JcmobService.Binder", "getService");
            return JcmobService.this;
        }
    }

    @Override
    public IBinder onBind (Intent intent) {
        Log.d ("JcmobService", "onBind");
        return new LocalBinder ();
    }

    protected String startServer () {
        Log.d ("JcmobService", "startServer");
        return Jcmob.start ();
    }

    protected void stopServer () {
        Log.d ("JcmobService", "stopServer");
        Jcmob.stop ();
    }
}
