package control;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import model.Player;

public class LoginWindow {
	
	public Player player;
	@FXML
	private TextField inputName;
	
	@FXML
	void playBTN(ActionEvent event) throws IOException {
		player=new Player(inputName.getText(),0,0);
		Stage stageClose = (Stage) inputName.getScene().getWindow();
    	stageClose.close();
		
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/MainWindow.fxml"));
		loader.setController(new MainWindow());
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		
		stage.setTitle("GALAGA");
		stage.setResizable(false);
		stage.show();
		
		
	}
}
