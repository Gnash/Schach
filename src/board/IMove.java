package board;

import pieces.IPiece;
import pieces.MoveType;
import pieces.PieceColor;
import pieces.PieceType;

public interface IMove {
	public abstract IPiece getPiece();
	public abstract ISquare getTarget();
	public abstract ISquare getStart();
	public abstract MoveType getType();
	public abstract PieceType killedPiece();
	public abstract PieceColor getColor();
	public abstract float getBoardStandings(PieceColor player);
}
