package org.jrmsdev.jcmob;

import android.app.Activity;
import android.os.Bundle;
import android.net.Uri;
import android.content.Intent;

public class MainActivity extends Activity {

    @Override
    protected void onCreate (Bundle b) {
        super.onCreate (b);
    }

    @Override
    protected void onResume () {
        super.onResume ();
        Uri uri = Uri.parse ("https://slashdot.com/");
        Intent intent = new Intent (Intent.ACTION_VIEW, uri);
        startActivity (intent);
    }

    @Override
    protected void onPause () {
        super.onPause ();
    }

    @Override
    protected void onDestroy () {
        super.onDestroy ();
    }

}