package control;

import java.io.FileNotFoundException;
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
import levels.LevelOne;
import levels.LevelThree;
import levels.LevelTwo;
import model.Bullet;
import model.Ship;

public class MainWindow implements Initializable{

	private Thread mainThread;
	
	private boolean runningFlag=false;
	
	private ArrayList<Bullet> bullets;
	
	private Ship ship;
    @FXML
    private Canvas canvas;

    private GraphicsContext gc;
    //Niveles
    private ArrayList<BaseLevels> levels;
    private static int LEVELS=0;

    public static long FRAMES = 0;
  	
  	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		//Levels
		 levels= new ArrayList<>();
		
		try {
			levels.add(new LevelOne(canvas));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		levels.add(new LevelTwo(canvas));
		levels.add(new LevelThree(canvas));
		
		
		gc=canvas.getGraphicsContext2D();
		canvas.setFocusTraversable(true);
		
		new Thread(() -> {
			while (true) {
				Platform.runLater(()->{
					paint();
				});
				pause(50);
				FRAMES++;
			}
		}).start();
		//Eventos teclas
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
		levels.get(LEVELS).paint();
	}
	
	
	public void initEvents() {
		
		canvas.setOnKeyPressed(e -> {
			levels.get(LEVELS).onKey(e);
		});
	
	}

	
	public void update() {
		
		
	}
	
	
	
    
    
}
