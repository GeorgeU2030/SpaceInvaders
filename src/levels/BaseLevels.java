package levels;

import java.io.IOException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import model.Player;

public abstract class BaseLevels {

	protected Canvas canvas;
	protected GraphicsContext gc;
	protected static Player player;
	
	public BaseLevels(Canvas canvas, Player player) {
		this.canvas = canvas;
		gc = canvas.getGraphicsContext2D();
	}
	
	public abstract void paint() throws IOException;
	

	public abstract void onKey(KeyEvent e);


}
