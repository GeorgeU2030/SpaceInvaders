package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Bullet {

	private Canvas canvas;
	private GraphicsContext gc;
	
	private double x, y;
	private Image textureBullet;

	private int size;
	private int speed;
	
	public Bullet(Canvas canvas,double x, double y, Image textureBullet, int speed) {
		this.setCanvas(canvas);
		this.gc = canvas.getGraphicsContext2D();
		
		this.x=x+20;
		this.y=y-20;
		
		this.size=0;
		this.speed=speed;
		this.textureBullet= textureBullet;
	}
	
	

	public void paint() {
			gc.drawImage(textureBullet, x, y,10,40);
		y-=speed;
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
