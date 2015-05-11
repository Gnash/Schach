package game;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;


public class Main {

	public static void main(String[] args) {
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new ChessGame("Simple Slick Game"));
			appgc.setDisplayMode(400, 400, false);
			appgc.setShowFPS(false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(ChessGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
