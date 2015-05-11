package pieces.implementations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pieces.Piece;
import pieces.PieceColor;
import pieces.PieceType;
import board.ISquare;

public class Rook extends Piece {

	public Rook(PieceColor color, ISquare square) {
		super(color, square);
	}

	@Override
	public PieceType getType() {
		return PieceType.ROOK;
	}

	@Override
	public Iterator<ISquare> getPossibleTargets(boolean checkForCheck) {
		List<ISquare> squares = new ArrayList<ISquare>();
		ISquare temp = currentSquare.getUp();
		while (temp != null) {
			boolean collision = addIfAvailable(squares, temp, checkForCheck);
			if (collision) {
				break;
			} else {
				temp = temp.getUp();
			}
		}
		temp = currentSquare.getDown();
		while (temp != null
				&& (!temp.isBlocked() || temp.getPiece().getColor() != this.color)) {
			boolean collision = addIfAvailable(squares, temp, checkForCheck);
			if (collision) {
				break;
			} else {
				temp = temp.getDown();
			}
		}
		temp = currentSquare.getLeft();
		while (temp != null
				&& (!temp.isBlocked() || temp.getPiece().getColor() != this.color)) {
			boolean collision = addIfAvailable(squares, temp, checkForCheck);
			if (collision) {
				break;
			} else {
				temp = temp.getLeft();
			}
		}
		temp = currentSquare.getRight();
		while (temp != null
				&& (!temp.isBlocked() || temp.getPiece().getColor() != this.color)) {
			boolean collision = addIfAvailable(squares, temp, checkForCheck);
			if (collision) {
				break;
			} else {
				temp = temp.getRight();
			}
		}
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
		result += "R";
		return result;
	}
}
