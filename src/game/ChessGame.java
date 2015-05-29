package game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;



public class ChessGame extends StateBasedGame {
	public ChessGame(String title) {
		super(title);
	}
	
	
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
//		addState(new IntroState(0));
		addState(new MainState(0));
	}
}
