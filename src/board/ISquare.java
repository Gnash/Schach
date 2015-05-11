package board;
import org.newdawn.slick.Graphics;

import pieces.IPiece;


public interface ISquare extends Cloneable {
	public abstract int getRow();
	public abstract int getCol();
	public abstract boolean isBlocked();
	public abstract IPiece getPiece();
	public abstract ISquare getLeft();
	public abstract ISquare getRight();
	public abstract ISquare getUp();
	public abstract ISquare getDown();
	public abstract ISquare getUpLeft();
	public abstract ISquare getDownLeft();
	public abstract ISquare getUpRight();
	public abstract ISquare getDownRight();
	public abstract void setPiece(IPiece piece);
	public abstract void draw(Graphics g);
	public abstract boolean isHighlighted();
	void setHighlighted(boolean highlighted);
	public abstract ISquare clone() throws CloneNotSupportedException;
	public abstract IBoard getBoard();
	public abstract void clear(boolean killPiece);
	
}
