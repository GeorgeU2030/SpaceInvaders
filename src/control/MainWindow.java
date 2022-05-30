package control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import levels.BaseLevels;
import levels.FinalScore;
import levels.LevelOne;
import levels.LevelThree;
import levels.LevelTwo;
import model.Bullet;
import model.EnemyShip;
import model.Ship;

public class MainWindow implements Initializable {

	private ArrayList<Bullet> bullets;

	private Ship ship;
	@FXML
	private Canvas canvas;

	private GraphicsContext gc;
	// Niveles
	private ArrayList<BaseLevels> levels;
	public static int LEVELS = 0;
	private LevelOne levelOnee;

	public static int getLEVELS() {
		return LEVELS;
	}

	public static void setLEVELS(int lEVELS) {
		LEVELS = lEVELS;
	}

	public static long FRAMES = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Levels
		levels = new ArrayList<>();

		try {
			levels.add(new LevelOne(canvas));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			levels.add(new LevelTwo(canvas));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		levels.add(new LevelThree(canvas));
		levels.add(new FinalScore(canvas));

		gc = canvas.getGraphicsContext2D();
		canvas.setFocusTraversable(true);

		new Thread(() -> {
			while (true) {
				Platform.runLater(() -> {
					paint();
				});
				pause(50);
				FRAMES++;
			}
		}).start();
		// Eventos teclas
		initEvents();
	}

	private void pause(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void paint() {
		try {
			levels.get(LEVELS).paint();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initEvents() {
		canvas.setOnKeyPressed(e -> {
			levels.get(LEVELS).onKey(e);
		});

		

	}

	public void eventsEnemyesIs0() {

	}

	public void update() {

	}

}
