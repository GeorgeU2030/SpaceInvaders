package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Bullet;
import model.Ship;

public class MainWindow implements Initializable {

	private ArrayList<Bullet> bullets;
	
	private Ship ship;
    @FXML
    private Canvas canvas;

    private GraphicsContext gc;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gc=canvas.getGraphicsContext2D();
		canvas.setFocusTraversable(true);
		ship=new Ship(canvas);
		bullets = new ArrayList<Bullet>();
		new Thread(() -> {
			while (true) {
				//Code
				Platform.runLater(()->{
					paint();
				});
				
				pause(50);
			}
		}).start();
		initEvents();
	}
	private void pause(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void paint() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		ship.paint();
		 for(int i=0;i<bullets.size();i++) {
			  bullets.get(i).paint();
			  if(bullets.get(i).getX()>canvas.getWidth()) {
				  bullets.remove(i);
				  i--;
			  }
		  }
	}
	public void initEvents() {
		canvas.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.A)) {
				ship.moveX(-10);
			}
			if(e.getCode().equals(KeyCode.D)) {
				ship.moveX(10);
			}
			if(e.getCode().equals(KeyCode.W)) {
				ship.moveY(-10);
			}
			if(e.getCode().equals(KeyCode.S)) {
				ship.moveY(10);
			}
			if(e.getCode().equals(KeyCode.SPACE)) {
			bullets.add(new Bullet(canvas,ship.getX()+10,ship.getY()-20));
			}
			
			
		});
		
	}
    
    
}
