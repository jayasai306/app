package org.SmileDetector;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCamera2View;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class Smile implements CameraBridgeViewBase.CvCameraViewListener2 {

	public JavaCamera2View mCamera = null;

	public void detectSmile() {
		if (mCamera != null)
			mCamera.enableView();

	}

	private Mat takePic(Mat img) {

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat image = img;
		CascadeClassifier faceDetector = new CascadeClassifier();
		faceDetector.load("haarcascade_frontalface_alt.xml");

		CascadeClassifier smileDetector = new CascadeClassifier();
		smileDetector.load("haarcascade_smile.xml");

		Mat gray = new Mat();
		Imgproc.cvtColor(image, gray, Imgproc.COLOR_RGB2GRAY);

		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(image, faceDetections);

		for (Rect rect : faceDetections.toArray()) {
			Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
		}

		MatOfRect smileDetections = new MatOfRect();
		smileDetector.detectMultiScale(image, smileDetections);

		if (smileDetections.toArray().length == 0) {
			System.out.println("smile please!!!");
		} else {
			String filename = "Ouput.jpg";
			Imgcodecs.imwrite("E:\\" + filename, img);
		}

		return image;
	}

	@Override
	public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
		// Get edge from the image
		Mat result = new Mat();
		result = inputFrame.rgba();

		// Return result
		return takePic(result);
	}

	@Override
	public void onCameraViewStarted(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCameraViewStopped() {
		// TODO Auto-generated method stub

	}
}
