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
import levels.BaseLevels;
import levels.FinalScore;
import levels.LevelOne;
import levels.LevelThree;
import levels.LevelTwo;
import model.Bullet;
import model.Player;
import model.Ship;


public class MainWindow  implements Initializable,Runnable {
	
	public static LoginWindow lw;
	private static Player player;
	private ArrayList<Bullet> bullets;

	private Ship ship;
	public BaseLevels bl;

	@FXML
	private Canvas canvas;
	private boolean hilo=false;;
	private Thread thread;
	
   
	public MainWindow(Player player) {
		this.setPlayer(player);
	}

	public boolean isHilo() {
		return hilo;
	}

	public void setHilo(boolean hilo) {
		this.hilo = hilo;
	}


	private GraphicsContext gc;
	// Niveles
	private ArrayList<BaseLevels> levels;
	public static int LEVELS = 0;

	public static int getLEVELS() {
		return LEVELS;
	}

	public static void setLEVELS(int lEVELS) {
		LEVELS = lEVELS;
	}


    public static long FRAMES = 0;
  	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Levels
		 levels= new ArrayList<>();
		 String name=getPlayer().username;
		 
		try {
			levels.add(new LevelOne(canvas,getPlayer()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Player player1=new Player(name,100);
			setPlayer(player1);
			levels.add(new LevelTwo(canvas,getPlayer()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Player player2=new Player(name,420);
			setPlayer(player2);
			levels.add(new LevelThree(canvas,getPlayer()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		levels.add(new FinalScore(canvas,getPlayer()));

		setGc(canvas.getGraphicsContext2D());
		canvas.setFocusTraversable(true);

		start();
		// Eventos teclas
		initEvents();
	}
	@Override
	public void run() {
		while (hilo) {
			Platform.runLater(()->{
				paint();
			});
			pause(50);
			FRAMES++;
		}
		try {
			stop();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void start() {
		thread = new Thread(this);
		thread.start();
		hilo=true;
	}
	
	
	private void stop() throws InterruptedException {
		thread.join();
		setHilo(false);
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

	
	public void update() {

	}

	public static Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		MainWindow.player = player;
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	public GraphicsContext getGc() {
		return gc;
	}

	public void setGc(GraphicsContext gc) {
		this.gc = gc;
	}

	

}
