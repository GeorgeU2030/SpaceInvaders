package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class EnemyShip {

	public EnemyShip(Canvas canvas) {
		super();
		this.canvas = canvas;
	}


	protected Canvas canvas;
	protected GraphicsContext gc;
	private int x=300;
	private int y=350;
	
	
	public abstract void paint();
}
