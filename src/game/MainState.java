package game;
import handlers.ResourceManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import pieces.IPiece;
import pieces.PieceColor;
import players.EasyBot;
import players.MediumBot;
import processes.PieceMoveProcess;
import processes.ProcessManager;

import board.Board;
import board.IBoard;
import board.ISquare;


public class MainState extends BasicGameState {

	private int id;
	private IBoard board;
	private IPiece activePiece;
	private ProcessManager processManager;
	
	public MainState(int id) {
		this.id = id;
		board = new Board();
		processManager = new ProcessManager();
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		ResourceManager.loadPieces();
		ResourceManager.loadBoard();
		board.setPlayer(PieceColor.BLACK, new MediumBot(PieceColor.BLACK, board, processManager));
		board.setPlayer(PieceColor.WHITE, new MediumBot(PieceColor.WHITE, board, processManager));
//		board.setPlayer(PieceColor.WHITE, new HumanPlayer());
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		board.draw(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		processManager.update(delta);
		board.getActivePlayer().update(delta);
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		int row = 8 - (y / 50);
		int col = (x / 50) + 1;
		if (row >= 1 && row <= 8 && col >= 1 && col <= 8) {
			ISquare clickedSquare = board.getSquare(row, col);
			if (activePiece == null) {
				activePiece = clickedSquare.getPiece();
				if (activePiece != null && activePiece.getColor() == board.getActivePlayerColor()) {
					activePiece.setActive(true);
				} else {
					activePiece = null;
				}
			} else {
				processManager.addProcess(new PieceMoveProcess(activePiece, clickedSquare));
				activePiece.setActive(false);
				activePiece = null;
			}
		}
	}

}
