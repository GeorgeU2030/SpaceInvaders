package main;

import control.LoginWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static final int WIDTH=600, HEIGHT=400;
	

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/Login.fxml"));
		loader.setController(new LoginWindow());
		Parent parent = loader.load();
		parent.setStyle("-fx-background-image: url('https://fondosmil.com/fondo/53645.jpg'); ");
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		
		stage.setTitle("GALAGA");
		stage.getIcons().add(new Image("/image/playerShip3_red.png"));
		stage.setResizable(false);
		stage.show();
		
	}
	/**/

}
