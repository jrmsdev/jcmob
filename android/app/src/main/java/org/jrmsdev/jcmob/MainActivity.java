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

// webview
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceError;

public class MainActivity extends Activity {

    private boolean ENABLE_JS = false;
    private WebView wv;
    private JcmobService mService;
    private boolean mBound;

    @Override
    protected void onCreate (Bundle b) {
        Log.d ("Jcmob", "OnCreate");
        super.onCreate (b);
        this.wv = this.newWV ();
        this.setContentView (this.wv);
    }

    @Override
    protected void onStart () {
        Log.d ("Jcmob", "OnStart");
        super.onStart ();
        this.doBindService ();
    }

    @Override
    protected void onResume () {
        super.onResume ();
        if (mBound) {
            Log.d ("Jcmob", "OnResume");
            try {
                this.wv.loadUrl (mService.startServer ());
            } catch (Exception e) {
                Toast.makeText (this, e.toString (), Toast.LENGTH_LONG).show ();
                e.printStackTrace ();
                this.finish ();
            }
        } else {
            Log.d ("Jcmob", "OnResume: service not bound");
            this.wv.loadData ("<html> <head> <title>JCMob loading...</title> </head> <body> <p> <a href='http://127.0.0.1:7666/'>JCMob</a> </p> </body> </html>",
                    "text/html", null);
        }
    }

    @Override
    protected void onPause () {
        super.onPause ();
        if (mBound) {
            Log.d ("Jcmob", "OnPause");
            mService.stopServer ();
        } else {
            Log.d ("Jcmob", "OnPause: service not bound");
        }
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

    //~ @Override
    //~ protected boolean dispatchKeyEvent (KeyEvent event) {
        //~ if (event.getKeyCode () == KeyEvent.KEYCODE_BACK) {
            //~ this.finish();
            //~ return true;
        //~ }
        //~ return super.dispatchKeyEvent(event);
    //~ }

    // Web View

    private WebView newWV () {
        Log.d ("Jcmob", "new WV");
        WebView wv = new WebView (this);
        this.wvSettings (wv);
        return wv;
    }

    private void wvSettings (WebView wv) {
        Log.d ("Jcmob", "WV settings");
        WebSettings ws = wv.getSettings ();
        ws.setJavaScriptEnabled (this.ENABLE_JS);
        this.wvClient (wv);
    }

    private void wvClient (WebView wv) {
        Log.d ("Jcmob", "WV client");
        final Activity activity = this;
        wv.setWebViewClient (new WebViewClient () {
            public void onReceivedError (WebView view, WebResourceRequest req, WebResourceError error) {
                Toast.makeText (activity, error.getDescription (), Toast.LENGTH_SHORT).show ();
            }
        });
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
