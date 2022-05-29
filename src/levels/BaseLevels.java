package levels;

import java.io.IOException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public abstract class BaseLevels {

	protected Canvas canvas;
	protected GraphicsContext gc;
	
	public BaseLevels(Canvas canvas) {
		this.canvas = canvas;
		gc = canvas.getGraphicsContext2D();
	}
	
	public abstract void paint() throws IOException;
	

	public abstract void onKey(KeyEvent e);
}
