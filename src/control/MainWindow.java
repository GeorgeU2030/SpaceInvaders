package control;

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

public class MainWindow implements Initializable, Runnable{

	private Thread mainThread;
	
	private boolean runningFlag=false;
	
	private ArrayList<Bullet> bullets;
	
	private Ship ship;
    @FXML
    private Canvas canvas;

    private GraphicsContext gc;
    //Niveles
    private ArrayList<BaseLevels> levels;

    
  //Fotograma por segundos
  	private final int FPS=60;
  	private double TARGETTIME =1000000000/FPS; //Nanosegundos/Número de fotogramas
  	private double delta=0; //Almacena temporalmente el tiempo que ha pasado.
  	private int AVERAGEFPS=FPS; //Fotograma por segundos en promedio .
  	
  	
  	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		//Levels
		 levels= new ArrayList<>();
		
		levels.add(new LevelOne(canvas));
		levels.add(new LevelTwo(canvas));
		levels.add(new LevelThree(canvas));
		
		
		gc=canvas.getGraphicsContext2D();
		canvas.setFocusTraversable(true);
		ship=new Ship(canvas);
		bullets = new ArrayList<Bullet>();
		start();
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
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		ship.paint();
System.out.println(AVERAGEFPS);
		 for(int i=0;i<bullets.size();i++) {
			  bullets.get(i).paint();
			  if(bullets.get(i).getX()>canvas.getWidth()) {
				  bullets.remove(i);
				  i--;
			  }
		  }
	}
	
	
	public void initEvents() {
		canvas.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.LEFT)) {
				ship.moveX(-10);
			}
			if(e.getCode().equals(KeyCode.RIGHT)) {
				ship.moveX(10);
			}
			/*if(e.getCode().equals(KeyCode.W)) {
				ship.moveY(-10);
			}
			if(e.getCode().equals(KeyCode.S)) {
				ship.moveY(10);
			}
			*/
			if(e.getCode().equals(KeyCode.SPACE)) {
			bullets.add(new Bullet(canvas,ship.getX()+10,ship.getY()-20));
			}
			
			
		});
		
	}

	
	public void update() {
		
		
	}
	//Hilo
	@Override
	public void run() {
		
		long now =0;
		//Obtiene la hora actual del sistema en nanosegundos para ser más precisos
		long lastTime = System.nanoTime(); 
		int frames = 0;
		long time =0;
		
		
		while(runningFlag) {
			now = System.nanoTime();
			//Cuando sea 1
			delta+=(now-lastTime)/TARGETTIME;
			time += (now-lastTime);
			lastTime= now;
			
			if(delta>=1) {
				Platform.runLater(()->{
					paint();
					
				});
				
				delta--;
				frames++;
				
				System.out.println(frames);
			}
			if(time>=1000000000) {
				
				AVERAGEFPS = frames;
				System.out.println(frames);
				frames = 0;
				time = 0;
				
			}
			
		}
		
		
		stop();
	}
	
	//Para iniciar el hilo
	private void start() {
		mainThread = new Thread(this);
		runningFlag=true;
	}
	
	//Para apagar el hilo
	private void stop() {
		try {
			mainThread.join();
			runningFlag=false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    
}
