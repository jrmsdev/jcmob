// https://developer.android.com/guide/webapps/webview.html
package org.jrmsdev.jcmob;

import android.os.Bundle;
import android.app.Activity;
//~ import android.R;
import android.util.Log;
import android.widget.Toast;

import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceError;

// bind service
import android.os.IBinder;
import android.content.Context;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.content.Intent;

public class WebViewActivity extends Activity {

    private boolean ENABLE_JS = false;
    private WebView wv;
    private JcmobService mService;
    private boolean mBound;

    @Override
    protected void onCreate (Bundle b) {
        Log.d ("JcmobWebView", "OnCreate");
        super.onCreate (b);
        this.wv = this.newWV ();
        this.setContentView (this.wv);
    }

    @Override
    protected void onStart () {
        Log.d ("JcmobWebView", "OnStart");
        super.onStart ();
        this.doBindService ();
    }

    @Override
    protected void onResume () {
        Log.d ("JcmobWebView", "OnResume");
        super.onResume ();
        this.wv.loadUrl ("http://127.0.0.1:7666/");
    }

    @Override
    protected void onPause () {
        Log.d ("JcmobWebView", "OnPause");
        super.onPause ();
    }

    @Override
    protected void onStop () {
        Log.d ("JcmobWebView", "OnStop");
        super.onStop ();
        this.doUnbindService ();
    }

    @Override
    protected void onDestroy () {
        Log.d ("JcmobWebView", "OnDestroy");
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

    // web view

    private WebView newWV () {
        Log.d ("JcmobWebView", "new WV");
        //~ WebView wv = (WebView) findViewById (R.id.jcmob_webview);
        WebView wv = new WebView (this);
        this.wvSettings (wv);
        return wv;
    }

    private void wvSettings (WebView wv) {
        Log.d ("JcmobWebView", "settings");
        WebSettings ws = wv.getSettings ();
        ws.setJavaScriptEnabled (this.ENABLE_JS);
        this.wvClient (wv);
    }

    private void wvClient (WebView wv) {
        Log.d ("JcmobWebView", "set client");
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
            Log.d ("JcmobWebView", "service connected");
        }

        @Override
        public void onServiceDisconnected (ComponentName className) {
            mBound = false;
            Log.d ("JcmobWebView", "service disconnected");
        }

    };

    protected void doBindService () {
        Log.d ("JcmobWebView", "bind service");
        mBound = false;
        Intent intent = new Intent (this, JcmobService.class);
        bindService (intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    private void doUnbindService() {
        if (mBound) {
            Log.d ("JcmobWebView", "unbind service");
            unbindService (mConnection);
            mBound = false;
        } else {
            Log.d ("JcmobWebView", "unbind service: not bound!");
        }
    }

}
