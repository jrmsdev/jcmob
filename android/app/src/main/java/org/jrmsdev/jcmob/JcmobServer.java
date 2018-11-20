package org.jrmsdev.jcmob;

import java.lang.Thread;
import android.util.Log;

import go.jcmob.Jcmob;

public class JcmobServer extends Thread {
	@Override
	public void run () {
		Log.d ("JcmobServer", "run");
		Jcmob.serve ();
	}
}
