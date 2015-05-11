package players;

import pieces.PieceColor;

public interface IPlayer {
	static final int moveTimeLimit = 3000;
	
	public abstract void update(int msElapsed);
	public abstract void notifyActivity(PieceColor color);
	public abstract PieceColor getColor();
	public abstract boolean isHuman();
}
