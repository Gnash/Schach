package board;

import java.util.Iterator;

import org.newdawn.slick.Graphics;

import pieces.IPiece;
import pieces.PieceColor;
import pieces.PieceType;
import players.IPlayer;


public interface IBoard extends Cloneable {
	abstract public ISquare getSquare(int row, int col);
	public abstract Iterator<IPiece> getPieces(PieceColor color, PieceType type);
	public abstract Iterator<IPiece> getAllPieces(PieceColor color);
	public abstract void removePiece(IPiece piece);
	public abstract void draw(Graphics g);
	public abstract IBoard clone() throws CloneNotSupportedException;
	public abstract boolean isInCheck(PieceColor color);
	public abstract PieceColor getActivePlayerColor();
	public abstract IPlayer getActivePlayer();
	public abstract void changeActivePlayer();
	public abstract IMove getLastMove();
	public abstract Iterator<IMove> getLastMoves(int amount);
	public abstract void setPlayer(PieceColor color, IPlayer player);
	public abstract void addMove(IPiece piece, ISquare currentSquare,
			ISquare target, IPiece piece2);
	/**
	 * Returns the difference of current net piece worth between both players.
	 * The result is positive if white has the advantage and negative if not.
	 */
	public abstract float getCurrentStandings();
	public abstract void undoLastMove();
}