package org.jrmsdev.jcmob;

import java.lang.String;
import java.io.File;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Toast;
import android.util.Log;

import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceError;

import go.jcmob.Jcmob;

public class MainActivity extends Activity {

	private WebView wv;
	private static boolean ENABLE_JS = false;
	private String serverUri;
	private String baseDir;
	private String dataDir;

	@Override
	protected void onCreate (Bundle b) {
		Log.d ("JcmobMain", "OnCreate");
		super.onCreate (b);

		this.baseDir = getDir ("jcmob", 0).getAbsolutePath ();
		Log.d ("JcmobMain baseDir", this.baseDir);
		Jcmob.setBaseDir (this.baseDir);

		this.dataDir = getFilesDir ().getAbsolutePath ();
		Log.d ("JcmobMain dataDir", this.dataDir);
		Jcmob.setDataDir (this.dataDir);

		this.serverUri = Jcmob.start ();
		Log.d ("JcmobMain serverUri", this.serverUri);
	}

	@Override
	protected void onStart () {
		Log.d ("JcmobMain", "OnStart");
		super.onStart ();
		this.wv = this.newWV ();
		this.setContentView (this.wv);
	}

	@Override
	protected void onResume () {
		Log.d ("JcmobMain OnResume", this.serverUri);
		super.onResume ();
		this.wv.loadUrl (this.serverUri);
	}

	@Override
	protected void onPause () {
		Log.d ("JcmobMain", "OnPause");
		super.onPause ();
	}

	@Override
	protected void onStop () {
		Log.d ("JcmobMain", "OnStop");
		super.onStop ();
	}

	@Override
	protected void onDestroy () {
		Log.d ("JcmobMain", "OnDestroy");
		Jcmob.stop ();
		super.onDestroy ();
	}

	// web view

	private WebView newWV () {
		Log.d ("JcmobMain", "new webview");
		WebView wv = new WebView (this);
		this.wvSettings (wv);
		return wv;
	}

	private void wvSettings (WebView wv) {
		Log.d ("JcmobMain", "webview settings");
		WebSettings ws = wv.getSettings ();
		ws.setJavaScriptEnabled (this.ENABLE_JS);
		this.wvClient (wv);
	}

	private void wvClient (WebView wv) {
		Log.d ("JcmobMain", "set webview client");
		final Activity activity = this;
		wv.setWebViewClient (new WebViewClient () {
			public void onReceivedError (WebView view, WebResourceRequest req, WebResourceError error) {
				Toast.makeText (activity, error.getDescription (), Toast.LENGTH_SHORT).show ();
			}
		});
	}

}
