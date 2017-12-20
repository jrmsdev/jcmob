package org.jrmsdev.jcmob;

import android.app.Activity;
import android.os.Bundle;

// externalBrowser
import android.net.Uri;
import android.content.Intent;

import go.jcmob.Jcmob;

public class MainActivity extends Activity {

    @Override
    protected void onCreate (Bundle b) {
        super.onCreate (b);
    }

    @Override
    protected void onResume () {
        super.onResume ();
        this.externalBrowser ();
    }

    @Override
    protected void onPause () {
        super.onPause ();
    }

    @Override
    protected void onDestroy () {
        super.onDestroy ();
    }

    private void externalBrowser () {
        Uri uri = Uri.parse (Jcmob.start ());
        Intent intent = new Intent (Intent.ACTION_VIEW, uri);
        startActivity (intent);
    }

}
