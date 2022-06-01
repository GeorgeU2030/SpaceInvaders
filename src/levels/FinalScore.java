package levels;


import java.io.IOException;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Player;

public class FinalScore extends BaseLevels {

	public Player player;
	
	public FinalScore(Canvas canvas,Player player) {
		super(canvas, player);
		this.player=player;
	          
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint() throws IOException {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill( Color.WHITE );
        gc.setFont( Font.font( "Bauhaus 93",50));
       // gc.fillText("Your score in this level is:"+, 250, 250 );
    }

	@Override
	public void onKey(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
