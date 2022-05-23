package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Bullet {

	private Canvas canvas;
	private GraphicsContext gc;
	private int x,y;
	private int size;
	private int speed;
	
	public Bullet(Canvas canvas,int x, int y) {
		this.canvas=canvas;
		this.gc = canvas.getGraphicsContext2D();
		this.x=x;
		this.y=y;
		this.size=10;
		this.speed=3;
	}
	public void paint() {
		File file =new File("src/image/bullet.png");
		try {
			Image image = new Image (new FileInputStream(file));
			gc.drawImage(image, x, y,30,30);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		y-=speed;
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
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
}
