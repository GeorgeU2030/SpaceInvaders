package levels;


import java.io.IOException;
import levels.LevelOne;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class FinalScore extends BaseLevels {

	
	public FinalScore(Canvas canvas) {
		super(canvas);
	          
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint() throws IOException {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill( Color.WHITE );
        gc.setFont( Font.font( "Candara",50));
       // gc.fillText("Your score in this level is:"+, 250, 250 );
    }

	@Override
	public void onKey(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
