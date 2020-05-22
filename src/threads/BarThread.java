package threads;

import javafx.application.Platform;
import model.Bar;
import ui.ProgressBarsGUI;

public class BarThread extends Thread {

	private Bar b;
	private ProgressBarsGUI pBarGUI;

	public BarThread(Bar b, ProgressBarsGUI pBarGUI) {
		this.b = b;
		this.pBarGUI = pBarGUI;
	}

	public void run() {

		while (b.isActive()) {

			b.advance();

			Platform.runLater(new Thread() {
				public void run() {
					pBarGUI.updateBar(b.getNumber());
				}
			});

			try {
				Thread.sleep(b.getTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		
		
		
	}

}
