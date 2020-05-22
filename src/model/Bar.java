package model;

public class Bar {

	public final int MAX_LEVEL = 400;

	private int progressLevel;
	private boolean active;
	private int number;
	private int time;
	
	public Bar(int number, int time) {
		progressLevel = 0;
		active = true;
		this.number = number;
		this.time = time;
	}

	public int getProgressLevel() {
		return progressLevel;
	}

	public void advance() {

		if (progressLevel < MAX_LEVEL) {
			progressLevel++;
		}else {
			active = false;
		}

	}

	public int getTime() {
		return time;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}

	public int getNumber() {
		return number;
	}
	
	public void setProgressLevel(int progressLvel) {
		this.progressLevel = progressLvel;
	}
	
}
