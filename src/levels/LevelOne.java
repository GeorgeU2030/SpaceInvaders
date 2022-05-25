package levels;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import model.Bullet;
import model.Ship;

public class LevelOne extends BaseLevels{
	//Objetos sobre el escenario
	private ArrayList<Bullet> bullets;
	private Ship ship;
	
	public LevelOne(Canvas canvas) {
		super(canvas);
		//Avatar
		ship = new Ship(canvas);
		//Balas
		bullets = new ArrayList<Bullet>();
		
	}


	@Override
	public void paint() {
		
	}

	@Override
	public void onKey(KeyEvent e) {
		
		
	}

}
