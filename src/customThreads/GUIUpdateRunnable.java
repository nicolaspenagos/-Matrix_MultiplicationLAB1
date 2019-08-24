package customThreads;

import userinterface.GUIController;

public class GUIUpdateRunnable implements Runnable{
	private GUIController guiC;
	public GUIUpdateRunnable(GUIController gc) {
		guiC = gc;
	}
	
	@Override
	public void run() {
		guiC.update();
	}
}
