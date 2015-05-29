package game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class IntroState extends BasicGameState {
	
	StateBasedGame game;
	int timeElapsed;
	int id;

	IntroState(int id) {
		this.id = id;
		timeElapsed = 0;
		game = null;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = game;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.drawString("Test", 300, 300);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		timeElapsed += delta;
		
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public void keyPressed(int key, char c) {
		game.enterState(1);
	}

}
