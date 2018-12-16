package application;

import gui.Desktop;
import gui.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import json.JsonObjectReader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			Pane redButton = createButtonPane("redBackground");
			Pane greenButton = createButtonPane("greenBackground");
			Pane blueButton = createButtonPane("blueBackground");
			ViewFactory.getInstance().addRegion("redButton", redButton);
			ViewFactory.getInstance().addRegion("greenButton", greenButton);
			ViewFactory.getInstance().addRegion("blueButton", blueButton);


			String jsonFilePath = "config/desktop.json";
			Desktop desktop = Desktop.createDesktopFromJsonFile("Desktop4", jsonFilePath);			
			Region region = desktop.getRegion(); 
			Scene scene = new Scene(region, 1000, 800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Pane createButtonPane(String cssClass) {
		Button button = new Button(); 
		button.setText(String.format(".%s", cssClass));
		button.getStyleClass().add(cssClass);		

		final StackPane stackPane = new StackPane();
		stackPane.getChildren().add(button);

		button.setMaxWidth(Double.MAX_VALUE);
		button.setMaxHeight(Double.MAX_VALUE);

		return stackPane;		
	}

}
