package org.facerecognizer;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import android.app.Activity;

public class FaceDetector extends Activity {

	/**
	 * @param args
	 */
	@Override
	public void onResume() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		CascadeClassifier faceDetector = new CascadeClassifier();
		faceDetector.load("haarcascade_frontalface_alt.xml");

		Mat image = Imgcodecs.imread("E:\\input.jpg");

		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(image, faceDetections);

		for (Rect rect : faceDetections.toArray()) {
			Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
		}

		String filename = "Ouput.jpg";
		Imgcodecs.imwrite("E:\\" + filename, image);
	}

}
