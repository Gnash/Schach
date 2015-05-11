package pieces.implementations;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pieces.Piece;
import pieces.PieceColor;
import pieces.PieceType;
import board.ISquare;


public class King extends Piece {

	public King(PieceColor color, ISquare square) {
		super(color, square);
	}

	@Override
	public PieceType getType() {
		return PieceType.KING;
	}


	@Override
	public Iterator<ISquare> getPossibleTargets(boolean checkForCheck) {
		List<ISquare> squares = new ArrayList<ISquare>();
		addIfAvailable(squares, currentSquare.getUp(), checkForCheck);
		addIfAvailable(squares, currentSquare.getDown(), checkForCheck);
		addIfAvailable(squares, currentSquare.getLeft(), checkForCheck);
		addIfAvailable(squares, currentSquare.getRight(), checkForCheck);
		addIfAvailable(squares, currentSquare.getUpLeft(), checkForCheck);
		addIfAvailable(squares, currentSquare.getUpRight(), checkForCheck);
		addIfAvailable(squares, currentSquare.getDownLeft(), checkForCheck);
		addIfAvailable(squares, currentSquare.getDownRight(), checkForCheck);
		return squares.iterator();
	}
	
	@Override
	public String toString() {
		String result = "";
		if (color == PieceColor.WHITE) {
			result += "W";
		} else if (color == PieceColor.BLACK) {
			result += "B";
		} else {
			result += "X";
		}
		result += "K";
		return result;
	}
}
