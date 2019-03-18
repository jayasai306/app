package org.SmileDetector;

import android.app.Activity;
import android.os.Bundle;

public class SmileDetector extends Activity {

	private Smile mSmile = null;

	private void init() {
		mSmile = new Smile();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		init();
	}

	/**
	 * @param args
	 */
	@Override
	public void onResume() {
		if (mSmile != null) {
			mSmile.detectSmile();
		}
	}
}
