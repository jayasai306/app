package org.SmileDetector;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class Smile {
	public void detectSmile() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		CascadeClassifier faceDetector = new CascadeClassifier();
		faceDetector.load("haarcascade_frontalface_alt.xml");

		CascadeClassifier smileDetector = new CascadeClassifier();
		smileDetector.load("haarcascade_smile.xml");

		Mat image = Imgcodecs.imread("E:\\input.jpg");
		Mat gray = new Mat();
		Imgproc.cvtColor(image, gray, Imgproc.COLOR_RGB2GRAY);

		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(image, faceDetections);

		for (Rect rect : faceDetections.toArray()) {
			Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
		}

		MatOfRect smileDetections = new MatOfRect();
		smileDetector.detectMultiScale(image, smileDetections);

		for (Rect rect : faceDetections.toArray()) {
			Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(255, 0, 0));
		}

		String filename = "Ouput.jpg";
		Imgcodecs.imwrite("E:\\" + filename, image);
	}
}
