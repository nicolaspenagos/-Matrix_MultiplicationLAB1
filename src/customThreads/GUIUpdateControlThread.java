package customThreads;

import javafx.application.Platform;
import userinterface.GUIController;

public class GUIUpdateControlThread extends Thread{
	private final static long UPDATE_SLEEP_TIME = 5;
	private GUIController guiC;
	
	public GUIUpdateControlThread(GUIController gC) {
		guiC = gC;
	}
	
	public void run() {
		while (true) {
			GUIUpdateRunnable gur = new GUIUpdateRunnable(guiC);
			Platform.runLater(gur);

			try {
				sleep(UPDATE_SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
