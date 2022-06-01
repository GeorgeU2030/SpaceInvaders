package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Ship {

	private Canvas canvas;
	private GraphicsContext gc;
	
	private boolean isAlive=true;

	double x, y;

	public Ship(Canvas canvas, double x, double y) {
		this.setCanvas(canvas);
		gc = canvas.getGraphicsContext2D();
		this.x = x;
		this.y = y;

	}
	
	public boolean getAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	


	public void paint() {
		File file = new File("image/playerShip3_red.png");
		try {
			Image image = new Image(new FileInputStream(file));
			gc.drawImage(image, x, y, 50, 50);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void limitWindow() {

	}

	public void moveXRight(int m) {

		x += m;
	}

	public void moveXLeft(int m) {

		x -= m;

	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

}
