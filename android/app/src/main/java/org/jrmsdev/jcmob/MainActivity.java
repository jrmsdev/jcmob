package org.jrmsdev.jcmob;

import android.app.Activity;
import android.os.Bundle;

// external browser
import android.net.Uri;
import android.content.Intent;

// webview
import android.webkit.WebView;

import go.jcmob.Jcmob;

public class MainActivity extends Activity {

    private WebView wv;

    @Override
    protected void onCreate (Bundle b) {
        super.onCreate (b);
        //~ this.externalBrowserStart ();
    }

    @Override
    protected void onResume () {
        super.onResume ();
        this.webviewResume ();
    }

    @Override
    protected void onPause () {
        super.onPause ();
    }

    @Override
    protected void onDestroy () {
        super.onDestroy ();
    }

    private void externalBrowserStart () {
        Uri uri = Uri.parse (Jcmob.start ());
        Intent intent = new Intent (Intent.ACTION_VIEW, uri);
        startActivity (intent);
    }

    private void webviewResume () {
        this.wv = new WebView (this);
        setContentView (this.wv);
        this.wv.loadData ("<html><body><pre>YEAH!!!</pre></body></html>", "text/html", null);
    }

}
