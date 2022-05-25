package levels;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
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
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		ship.paint();

		 for(int i=0;i<bullets.size();i++) {
			  bullets.get(i).paint();
			  if(bullets.get(i).getX()>canvas.getWidth()) {
				  bullets.remove(i);
				  i--;
			  }
		  }
	}

	@Override
	public void onKey(KeyEvent e) {
		
			if(e.getCode().equals(KeyCode.LEFT)) {
				ship.moveXLeft(10);
			}
			if(e.getCode().equals(KeyCode.RIGHT)) {
				ship.moveXRight(10);
			}
			if(e.getCode().equals(KeyCode.SPACE)) {
				bullets.add(new Bullet(canvas,ship.getX()+10,ship.getY()-20));
			}
		
		
	}

}
