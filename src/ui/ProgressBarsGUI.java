package ui;

import java.util.ArrayList;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.Rectangle;
import model.Bar;
import threads.BarThread;

public class ProgressBarsGUI {

	private ArrayList<Bar> bars;

	private ArrayList<Rectangle> recBars;

	@FXML
	private Button butStart;

	@FXML
	private Button butStop;

	@FXML
	private TextField txtTime;

	@FXML
	private VBox vBox;

	public ProgressBarsGUI() {
		bars = new ArrayList<Bar>();
		recBars = new ArrayList<Rectangle>();
	}

	public void initialize() {

		butStop.setDisable(true);

	}

	@FXML
	void start(ActionEvent event) {

		butStart.setDisable(true);
		butStop.setDisable(false);

		for (int i = 0; i < bars.size(); i++) {

			Bar b = bars.get(i);

			b.setActive(true);
			BarThread barThread = new BarThread(b, this);
			barThread.start();
		}

	}

	@FXML
	void stop(ActionEvent event) {

		butStart.setDisable(false);
		butStop.setDisable(true);

		for (int i = 0; i < bars.size(); i++) {

			Bar b = bars.get(i);
			b.setActive(false);
		}

	}
	
	@FXML
    void reset(ActionEvent event) {
		
		for(int i=0; i<bars.size(); i++) {
			Bar bar = bars.get(i);
			Rectangle rect = recBars.get(i);
			
			bar.setProgressLevel(0);
			bar.setActive(false);
			rect.setWidth(0);
			
		}
		
		butStart.setDisable(false);
		butStop.setDisable(true);
		
    }
	
	@FXML
	void newBar(ActionEvent event) {

		try {
			int time = Integer.parseInt(txtTime.getText());
			HBox hbox = new HBox();
			hbox.setMinHeight(50);

			Pane pane = new Pane();

			// Create White bar
			Rectangle whiteBar = new Rectangle();
			whiteBar.setWidth(400);
			whiteBar.setHeight(35);
			whiteBar.setFill(Color.WHITE);
			whiteBar.setStroke(Color.BLACK);
			whiteBar.setX(164);

			// Create Color Bar
			Rectangle colorBar = new Rectangle();
			colorBar.setWidth(0);
			colorBar.setHeight(35);
			Random r = new Random();
			Color c = Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255));
			colorBar.setFill(c);
			colorBar.setStroke(Color.BLACK);
			colorBar.setX(164);
			
			//Create Time Level
			Label timeLabel = new Label(time + "");
			timeLabel.setLayoutX(180);
			timeLabel.setLayoutY(10);

			// Add rectangles
			pane.getChildren().add(whiteBar);
			pane.getChildren().add(colorBar);
			pane.getChildren().add(timeLabel);

			hbox.getChildren().add(pane);

			vBox.getChildren().add(hbox);

			// Add bar and rectangle to ArrayList
			bars.add(new Bar(bars.size(), time));
			recBars.add(colorBar);
			
		} catch (NumberFormatException e) {
			ButtonType bt = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
			Alert alert = new Alert(AlertType.INFORMATION, "Digit a valid number", bt);
			alert.setTitle("Progress Bar");
			alert.setHeaderText("Warning");
			alert.showAndWait();
		}

		

	}

	public void updateBar(int number) {

		Bar b = bars.get(number);
		Rectangle recBar = recBars.get(number);

		int pixel = b.getProgressLevel();
		recBar.setWidth(pixel);
		
		boolean finished = true;
		
		for(int i=0; i<bars.size() && finished == true; i++) {
			
			Bar bar = bars.get(i);
			
			if(bar.isActive()) {
				finished = false;
			}
			
		}
		
		if (finished == true) {
			butStop.setDisable(true);
			butStart.setDisable(false);
		}

	}

}