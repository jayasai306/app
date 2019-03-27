package org.SmileDetector;

import org.opencv.R;
import org.opencv.android.JavaCamera2View;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceView;

public class SmileDetector extends Activity {

	private Smile mSmile = null;

	private void init() {
		mSmile = new Smile();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera);
		init();
		mSmile.mCamera = (JavaCamera2View) findViewById(R.id.show_camera_activity_java_surface_view);
		mSmile.mCamera.setVisibility(SurfaceView.VISIBLE);
		mSmile.mCamera.setCvCameraViewListener(mSmile);
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
