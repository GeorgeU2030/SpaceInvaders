package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class EnemyOne extends EnemyShip implements Runnable{
	
	private double x;
	private double y;
	private final int NUMBERENEMYSONE=10;
	private Thread threads;
	private boolean isAlive = false;; //Variable para saber si el enemigo sigue vivo o no.


	public EnemyOne(Canvas canvas, double x, double y) {
		super(canvas);
		this.canvas=canvas;
		gc=canvas.getGraphicsContext2D();
		this.x=x;
		this.y=y;
		
	}
	//Hilo
	@Override
	public void run() {
		while(isAlive) {
			int randX = (int)(7*Math.random()) - 3;
			int randY = (int)(7*Math.random()) - 3;
			x+=randX;
			y+=randY;
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	//Para iniciar el hilo
		public void start() {
			threads = new Thread(this);
			threads.start();
			isAlive=true;
		}
		
		//Para apagar el hilo
		public void stop() {
			try {
				threads.join();
				isAlive=false;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	@Override
	public void paint() {
		File file =new File("src/image/spaceShips_001.png");
		try {
			Image image = new Image (new FileInputStream(file));
			gc.drawImage(image, x, y,50,50);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
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
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	

	

}
