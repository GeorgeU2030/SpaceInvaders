package levels;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import model.Bullet;
import model.EnemyOne;
import model.EnemyShip;
import model.EnemyTwo;
import model.Ship;

public class LevelOne extends BaseLevels {
	// Objetos sobre el escenario
	private ArrayList<Bullet> bullets;
	private ArrayList<EnemyOne> enemyOne;
	private ArrayList<EnemyTwo> enemyTwo;
	private Ship ship;
	

	public LevelOne(Canvas canvas) {
		super(canvas);
		
			ship = new Ship(canvas, 500, canvas.getHeight() - 100);
		
		// Balas
		bullets = new ArrayList<Bullet>();
		enemyOne = new ArrayList<EnemyOne>();
		enemyTwo = new ArrayList<EnemyTwo>();
		
		initializingEnemyes();
		
		
	}

	
	public void initializingEnemyes() {
		EnemyOne enemy1 = new EnemyOne(canvas, 100, canvas.getHeight()-100);
		EnemyOne enemy2 = new EnemyOne(canvas, 400, canvas.getHeight()-300);
		EnemyOne enemy3 = new EnemyOne(canvas, 300, canvas.getHeight()-300);
		EnemyOne enemy4 = new EnemyOne(canvas, 200, canvas.getHeight()-300);
		EnemyOne enemy5 = new EnemyOne(canvas, 250, canvas.getHeight()-300);
		EnemyOne enemy6 = new EnemyOne(canvas, 450, canvas.getHeight()-300);
		
		
		enemyOne.add(enemy1);
		enemyOne.add(enemy2);
		enemyOne.add(enemy3);
		enemyOne.add(enemy4);
		enemyOne.add(enemy5);
		enemyOne.add(enemy6);
		
		enemy1.start();
		enemy2.start();
		enemy3.start();
		enemy4.start();
		enemy5.start();
		enemy6.start();
		
		/*
		EnemyTwo enemy7 = new EnemyTwo(canvas, 700, canvas.getHeight()+200);
		EnemyTwo enemy8 = new EnemyTwo(canvas, 700, canvas.getHeight()+200);
		EnemyTwo enemy9 = new EnemyTwo(canvas, 700, canvas.getHeight()+200);
		EnemyTwo enemy10 = new EnemyTwo(canvas, 700, canvas.getHeight()+200);
		EnemyTwo enemy11 = new EnemyTwo(canvas, 700, canvas.getHeight()+200);
		EnemyTwo enemy12 = new EnemyTwo(canvas, 700, canvas.getHeight()+200);
		*/
		//enemy7.start();
		//enemy8.start();
		//enemy9.start();
		//enemy10.start();
		//enemy11.start();
		//enemy12.start();
		
		
		
		
		
	}
	@Override
	public void paint() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		ship.paint();
		
		
		paintEnemyesOneAndTwo();
		

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).paint();
			if (bullets.get(i).getX() > canvas.getWidth()) {
				bullets.remove(i);
				i--;
			}
		}

	}
	
	public void paintEnemyesOneAndTwo() {
		//Enemigos version 1
		for(int i=0;i<enemyOne.size();i++) {
			enemyOne.get(i).paint();
			
			
		}
		//Enemigos version 2
		//for(int i=0;i<enemyTwo.size();i++) {
			//enemyTwo.get(i).paint();
			
			
		//}
	}

	@Override
	public void onKey(KeyEvent e) {

		if (e.getCode().equals(KeyCode.LEFT)) {
			ship.moveXLeft(10);
		}
		if (e.getCode().equals(KeyCode.RIGHT)) {
			ship.moveXRight(10);
		}
		if (e.getCode().equals(KeyCode.SPACE)) {
			bullets.add(new Bullet(canvas, ship.getX(), ship.getY()));
		}

	}

}
