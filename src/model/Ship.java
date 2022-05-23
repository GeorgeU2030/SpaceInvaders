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
	private int x=300;
	private int y=350;
	
	public Ship(Canvas canvas) {
		this.canvas=canvas;
		gc=canvas.getGraphicsContext2D();
		
	}
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
	public void moveX(int m) {
		this.x+=m;
	}
	public void moveY(int m) {
	    this.y+=m;	
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}
