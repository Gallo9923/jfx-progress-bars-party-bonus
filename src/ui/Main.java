package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{
	
	public Main() {
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("progress-panel.fxml"));
		fxmlLoader.setController(new ProgressBarsGUI());
		BorderPane bp = fxmlLoader.load();
		
		Scene scene = new Scene(bp);
		stage.setScene(scene);
		stage.setTitle("Progress Bars");
		stage.show();
		
		
	}
	
	
		
}
