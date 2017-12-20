package org.jrmsdev.jcmob;

import android.app.Activity;
import android.os.Bundle;

// external browser
//~ import android.net.Uri;
//~ import android.content.Intent;

// webview
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceError;
import android.widget.Toast;

import go.jcmob.Jcmob;

public class MainActivity extends Activity {

    private boolean ENABLE_JS = false;
    private WebView wv;

    @Override
    protected void onCreate (Bundle b) {
        super.onCreate (b);
        this.wv = this.newWV ();
        setContentView (this.wv);
    }

    @Override
    protected void onResume () {
        super.onResume ();
        try {
            this.wv.loadUrl (Jcmob.start ());
        } catch (Exception e) {
            Toast.makeText (this, e.toString (), Toast.LENGTH_LONG).show ();
            e.printStackTrace ();
            this.finish ();
        }
    }

    @Override
    protected void onPause () {
        Jcmob.stop ();
        super.onPause ();
    }

    @Override
    protected void onDestroy () {
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

    //~ private void externalBrowserStart () {
        //~ Uri uri = Uri.parse (Jcmob.start ());
        //~ Intent intent = new Intent (Intent.ACTION_VIEW, uri);
        //~ startActivity (intent);
    //~ }

    private WebView newWV () {
        WebView wv = new WebView (this);
        this.wvSettings (wv);
        return wv;
    }

    private void wvSettings (WebView wv) {
        WebSettings ws = wv.getSettings ();
        ws.setJavaScriptEnabled (this.ENABLE_JS);
        this.wvClient (wv);
    }

    private void wvClient (WebView wv) {
        final Activity activity = this;
        wv.setWebViewClient (new WebViewClient () {
            public void onReceivedError (WebView view, WebResourceRequest req, WebResourceError error) {
                Toast.makeText (activity, error.getDescription (), Toast.LENGTH_SHORT).show ();
            }
        });
    }
}
