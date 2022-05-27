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
import model.EnemyShip;
import model.Ship;

public class LevelOne extends BaseLevels {
	// Objetos sobre el escenario
	private ArrayList<Bullet> bullets;
	private ArrayList<EnemyShip> enemyShip1;
	
	private Ship ship;

	public LevelOne(Canvas canvas) throws FileNotFoundException {
		super(canvas);

		ship = new Ship(canvas, 500, canvas.getHeight() - 100);

		// Balas
		bullets = new ArrayList<Bullet>();
		enemyShip1 = new ArrayList<EnemyShip>();
		
		initializingEnemyes();

	}

	
	public void initializingEnemyes() throws FileNotFoundException {
		File file =new File("src/image/enemyBlue2.png");
		
			Image textureEnemy = new Image (new FileInputStream(file));
			
		EnemyShip enemy1 = new EnemyShip(canvas, 500, canvas.getHeight() - 600,textureEnemy);
		EnemyShip enemy2 = new EnemyShip(canvas, 400, canvas.getHeight() - 600,textureEnemy);
		EnemyShip enemy3 = new EnemyShip(canvas, 300, canvas.getHeight() - 600,textureEnemy);
		EnemyShip enemy4 = new EnemyShip(canvas, 600, canvas.getHeight() - 600,textureEnemy);
		EnemyShip enemy5 = new EnemyShip(canvas, 700, canvas.getHeight() - 600,textureEnemy);
		EnemyShip enemy6 = new EnemyShip(canvas, 800, canvas.getHeight() - 600,textureEnemy);

		enemyShip1.add(enemy1);
		enemyShip1.add(enemy2);
		enemyShip1.add(enemy3);
		enemyShip1.add(enemy4);
		enemyShip1.add(enemy5);
		enemyShip1.add(enemy6);

		enemy1.start();
		enemy2.start();
		enemy3.start();
		enemy4.start();
		enemy5.start();
		enemy6.start();

	}

	@Override
	public void paint() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		ship.paint();
		//Condiciones por si el player sale de la pantalla
		if(ship.getX()>canvas.getWidth()) {
			ship.setX(0);
		}
		if(ship.getX()<0) {
			ship.setX(canvas.getWidth());
		}
		paintEnemyesOneAndTwo();

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).paint();
			if (bullets.get(i).getY() > canvas.getHeight()) {
				bullets.remove(i);
				i--;
			}
		}

	}

	public void collision() {
		// Calcular distancia
		for (int i = 0; i < enemyShip1.size(); i++) {
			for (int j = 0; j < bullets.size(); j++) {

				// Comparar
				EnemyShip enemy = enemyShip1.get(i);
				Bullet p = bullets.get(j);
				//Distance euclidea
				double D = Math.sqrt(Math.pow(enemy.getX() - p.getX(), 2) + Math.pow(enemy.getY() - p.getY(), 2));

				if (D <= 10) {
					EnemyShip deletedEnemy = enemyShip1.remove(i);
					deletedEnemy.setAlive(false);
					enemyShip1.remove(j);
					

					return;
				}

				System.out.println("Distancia:" + D);
			}
		}
	}

	public void paintEnemyesOneAndTwo() {
		// Enemigos version 1
		for (int i = 0; i < enemyShip1.size(); i++) {
			enemyShip1.get(i).paint();
		}

	}

	@Override
	public void onKey(KeyEvent e) {

		if (e.getCode().equals(KeyCode.LEFT)) {
			ship.moveXLeft(15);
		}
		if (e.getCode().equals(KeyCode.RIGHT)) {
			ship.moveXRight(15);
		}
		if (e.getCode().equals(KeyCode.SPACE)) {
			File fileBullet =new File("src/image/laserRed07.png");
			Image textureBullet = null;
			try {
				textureBullet = new Image (new FileInputStream(fileBullet));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			bullets.add(new Bullet(canvas, ship.getX(), ship.getY(),textureBullet));
		}

	}

}
