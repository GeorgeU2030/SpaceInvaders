package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.Bullet;
import java.util.ArrayList;

public class EnemyShip extends Thread{
	//Graphics
	private Canvas canvas;
	private GraphicsContext gc;
	private int countBullet=0;
	private double x;
	private double y;
	private double speedY;
	private double speedX;
	//private final int NUMBERENEMYSONE=10;
	private Image texture;
	
	private ArrayList<Bullet> bulletsEnemyes;
	
	private boolean isAlive = true; //Variable para saber si el enemigo sigue vivo o no.


	public EnemyShip(Canvas canvas, double x, double y, Image texture, double speedY,double speedX) {
		bulletsEnemyes= new ArrayList<Bullet>();
		this.canvas=canvas;
		gc=canvas.getGraphicsContext2D();
		this.x=x;
		this.y=y;
		this.texture= texture;
		this.speedY=speedY;
		this.speedX=speedX;
		
	}
	//Hilo
	@Override
	public void run() {
		
		
		while(isAlive) {
			
			x+=speedX;
			y+=speedY;
			
			
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	public void paint() {
		
			gc.drawImage(texture, x, y,50,50);
		
		
	}

	
	public void move() {
		// TODO Auto-generated method stub
		
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
	public Image getTexture() {
		return texture;
	}
	public void setTexture(Image texture) {
		this.texture = texture;
	}
	public boolean getAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	

	

}
