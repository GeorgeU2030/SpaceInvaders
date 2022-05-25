package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class EnemyShip {

	private Canvas canvas;
	private GraphicsContext gc;
	private int x=300;
	private int y=350;
	
	
	public void paint() {
		File file =new File("src/image/nave.png");
		try {
			Image image = new Image (new FileInputStream(file));
			gc.drawImage(image, x, y,50,50);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
