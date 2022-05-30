package levels;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import control.MainWindow;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import model.Bullet;
import model.EnemyShip;
import model.Ship;

public class LevelTwo extends BaseLevels {

	private final static int NUM_ENEMY = 10;
	private ArrayList<Bullet> bullets;
	// private ArrayList<Bullet> enemyBullets;
	private ArrayList<EnemyShip> enemyShip1;
	private ArrayList<Image> exploImages;
	private int frameExplo = 0;

	private Ship ship;
	private static int score;

	private MainWindow main;

	public LevelTwo(Canvas canvas) throws FileNotFoundException {
		super(canvas);
		ship = new Ship(canvas, 500, canvas.getHeight() - 100);

		// Balas
		bullets = new ArrayList<Bullet>();
		// enemyBullets = new ArrayList<Bullet>();
		enemyShip1 = new ArrayList<EnemyShip>();
		exploImages = new ArrayList<>();
		setScore(LevelOne.getScore());

		initializingEnemyes();
	}

	public void initializingEnemyes() throws FileNotFoundException {
		int contx = 200;
		int conty = 1000;
		;
		int i = 1;
		try {
			for (int j = 0; j < NUM_ENEMY; j++) {
				if (j == 5) {
					i = 1;
					// conty=700;
					conty -= 100;
					contx = 200;
				}
				File file = new File("image/enemy" + i + ".png");
				i++;
				Image textureEnemy = new Image(new FileInputStream(file));
				contx += 100;
				// conty-=100;
				double speedY=11;
				double speedX=0;
				EnemyShip enemy = new EnemyShip(canvas, contx, canvas.getHeight() - conty, textureEnemy,speedY,speedX);
				enemyShip1.add(enemy);
				// shotsAgainst(enemy);
				enemy.start();
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void paint() throws IOException {

		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

		gc.setFill(Color.grayRgb(20));
		gc.setTextAlign(TextAlignment.CENTER);

		gc.setFill(Color.WHITE);
		gc.fillText("Score: " + getScore(), 60, 20);

		if (ship.getAlive() == true) {
			ship.paint();
			// Condiciones por si el player sale de la pantalla
			if (ship.getX() > canvas.getWidth()) {
				ship.setX(canvas.getWidth()-50);
			}
			if (ship.getX() < 0) {
				ship.setX(0);
			}

			// Balas
			for (int i = 0; i < bullets.size(); i++) {
				bullets.get(i).paint();
				if (bullets.get(i).getY() > canvas.getHeight()) {
					bullets.remove(i);
					i--;
				}
			}
			
		}

		paintEnemyesOneAndTwo();

		// Colision balas vs enemigo

		// Colision enemigo vs player
		new Thread(() -> {
			while (ship.getAlive() == true) {
				try {
					collision();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				collisionPlayer();
				pause(10);

			}

			gc.setFill(Color.grayRgb(20));
			gc.setTextAlign(TextAlignment.CENTER);

			gc.setFill(Color.WHITE);
			gc.fillText("GAME OVER", 500, canvas.getHeight() - 400);

			enemyShip1.removeAll(enemyShip1);
			bullets.removeAll(bullets);
		}).start();

		if (condicionEnemigosVivos() == true && ship.getAlive() == true) {
			paintNextLevel();

		}

	}

	private void pause(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void collision() throws IOException {
		if (ship.getAlive() == true) {

			// Calcular distancia
			for (int i = 0; i < bullets.size(); i++) {
				for (int j = 0; j < enemyShip1.size(); j++) {

					// Comparar
					EnemyShip enemy = enemyShip1.get(j);
					Bullet p = bullets.get(i);
					// Distance euclidea
					double D = Math.sqrt(Math.pow(enemy.getX() - p.getX(), 2) + Math.pow(enemy.getY() - p.getY(), 2));
					if (D <= 30) {
						EnemyShip deletedEnemy = enemyShip1.remove(j);
						deletedEnemy.setAlive(false);
						bullets.remove(i);
						explosion(deletedEnemy);
						frameExplo = 0;
						setScore(getScore() + 10);

						return;
					}

					System.out.println("Distancia: " + "  enemigo #" + j + " bala # " + i + "  " + D);

				}
			}
		}
	}

	public boolean condicionEnemigosVivos() {
		if (enemyShip1.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public void collisionPlayer() {
		if (ship.getAlive() == true) {
			for (int i = 0; i < enemyShip1.size(); i++) {
				if (enemyShip1.get(i).getAlive() == true) {
					EnemyShip enemy = enemyShip1.get(i);
					Ship shipd = ship;
					double D = Math
							.sqrt(Math.pow(shipd.getX() - enemy.getX(), 2) + Math.pow(shipd.getY() - enemy.getY(), 2));
					if (D <= 40) {
						EnemyShip deletedEnemy = enemyShip1.remove(i);

						explosion(shipd);
						frameExplo = 0;
						shipd.setAlive(false);
						deletedEnemy.setAlive(false);
						System.out.println("PERDISTE");
					}
					System.out.println("Distancia: " + "  enemigo #" + shipd + " enemigo # " + i + "  " + D);

				}
			}
		}
	}

	public void explosion(EnemyShip deletedEnemy) {
		try {
			for (int i = 1; i <= 14; i++) {
				File file = new File("image/Explosion/explosion - copia (" + i + ").png");
				Image image = new Image(new FileInputStream(file));
				exploImages.add(image);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		gc.drawImage(exploImages.get(frameExplo), deletedEnemy.getX(), deletedEnemy.getY(), 50, 80);
		frameExplo++;
	}

	public void explosion(Ship player) {
		try {
			for (int i = 1; i <= 14; i++) {
				File file = new File("image/Explosion/explosion - copia (" + i + ").png");
				Image image = new Image(new FileInputStream(file));
				exploImages.add(image);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		gc.drawImage(exploImages.get(frameExplo), player.getX(), player.getY(), 50, 80);
		frameExplo++;
	}

	public void paintEnemyesOneAndTwo() {
		// Enemigos version 1
		for (int i = 0; i < enemyShip1.size(); i++) {
			enemyShip1.get(i).paint();

			if (enemyShip1.get(i).getX() > canvas.getWidth()) {
				enemyShip1.get(i).setX(0);
			}
			if (enemyShip1.get(i).getX() < 0) {
				enemyShip1.get(i).setX(canvas.getWidth());
			}
			if (enemyShip1.get(i).getY() > canvas.getHeight()) {
				enemyShip1.get(i).setY(0);
			}

		}

	}

	public void paintNextLevel() {

		gc.setFill(Color.WHITE);
		gc.fillText("Press enter to continue to the next level ", 500,
				canvas.getHeight() - 400);
	}

	

	@Override
	public void onKey(KeyEvent e) {

		if (ship.getAlive() == true) {

			if (e.getCode().equals(KeyCode.LEFT)) {
				ship.moveXLeft(15);
			}
			if (e.getCode().equals(KeyCode.RIGHT)) {
				ship.moveXRight(15);
			}
			if (e.getCode().equals(KeyCode.SPACE)) {
				File fileBullet = new File("image/laserRed07.png");
				Image textureBullet = null;
				try {
					textureBullet = new Image(new FileInputStream(fileBullet));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				bullets.add(new Bullet(canvas, ship.getX(), ship.getY(), textureBullet, 8));
			}
			if(e.getCode().equals(KeyCode.ENTER)) {
			if (condicionEnemigosVivos() == true) {

				MainWindow.LEVELS = (MainWindow.LEVELS + 1);

			}
			}
		}
	}

	public static int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
