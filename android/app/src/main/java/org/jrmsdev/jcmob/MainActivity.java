package org.jrmsdev.jcmob;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;
import android.util.Log;

// bind service
import android.os.IBinder;
import android.content.Context;
import android.content.ComponentName;
import android.content.ServiceConnection;

public class MainActivity extends Activity {

    private JcmobService mService;
    private boolean mBound;

    @Override
    protected void onCreate (Bundle b) {
        Log.d ("Jcmob", "OnCreate");
        super.onCreate (b);
    }

    @Override
    protected void onStart () {
        Log.d ("Jcmob", "OnStart");
        super.onStart ();
        this.doBindService ();
        this.webViewStart ();
    }

    @Override
    protected void onResume () {
        Log.d ("Jcmob", "OnResume");
        super.onResume ();
    }

    @Override
    protected void onPause () {
        Log.d ("Jcmob", "OnPause");
        super.onPause ();
    }

    @Override
    protected void onStop () {
        Log.d ("Jcmob", "OnStop");
        super.onStop ();
        this.doUnbindService ();
    }

    @Override
    protected void onDestroy () {
        Log.d ("Jcmob", "OnDestroy");
        super.onDestroy ();
    }

    // Web View

    private void webViewStart () {
        Log.d ("Jcmob", "start web view activity");
        Intent wv = new Intent (this, WebViewActivity.class);
        this.startActivity (wv);
    }

    // Bind Service

    private ServiceConnection mConnection = new ServiceConnection () {

        @Override
        public void onServiceConnected (ComponentName className, IBinder service) {
            JcmobService.LocalBinder binder = (JcmobService.LocalBinder) service;
            mService = binder.getService ();
            mBound = true;
            Log.d ("Jcmob", "service connected");
        }

        @Override
        public void onServiceDisconnected (ComponentName className) {
            mBound = false;
            Log.d ("Jcmob", "service disconnected");
        }

    };

    protected void doBindService () {
        Log.d ("Jcmob", "bind service");
        mBound = false;
        Intent intent = new Intent (this, JcmobService.class);
        bindService (intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    private void doUnbindService() {
        if (mBound) {
            Log.d ("Jcmob", "unbind service");
            unbindService (mConnection);
            mBound = false;
        } else {
            Log.d ("Jcmob", "unbind service: not bound!");
        }
    }

}
