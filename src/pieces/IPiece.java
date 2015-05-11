package pieces;

import java.util.Iterator;

import org.newdawn.slick.Graphics;

import board.ISquare;


public interface IPiece extends Cloneable {
	public abstract ISquare getCurrentSquare();
	public abstract PieceType getType();
	public abstract MoveType move(ISquare target);
	public abstract PieceColor getColor();
	public abstract boolean isWhite();
	public abstract boolean isBlack();
	public abstract void draw(Graphics g);
	public abstract boolean isActive();
	public abstract void setActive(boolean active);
	public abstract void setCurrentSquare(ISquare square);
	public abstract Iterator<ISquare> getPossibleTargets(boolean checkForCheck);
	public abstract boolean hasMovedYet();
	public abstract void decreaseMoveCount();
}
