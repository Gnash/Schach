package pieces.implementations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pieces.Piece;
import pieces.PieceColor;
import pieces.PieceType;
import board.ISquare;

public class Bishop extends Piece {

	public Bishop(PieceColor color, ISquare square) {
		super(color, square);
	}

	@Override
	public PieceType getType() {
		return PieceType.BISHOP;
	}

	@Override
	public Iterator<ISquare> getPossibleTargets(boolean checkForCheck) {
		List<ISquare> squares = new ArrayList<ISquare>();
		ISquare temp = currentSquare.getUpLeft();
		while (temp != null && (!temp.isBlocked() || temp.getPiece().getColor() != this.color)) {
			boolean collision = addIfAvailable(squares, temp, checkForCheck);
			if (collision) {
				break;
			} else {
			temp = temp.getUpLeft();
			}
		}
		temp = currentSquare.getUpRight();
		while (temp != null && (!temp.isBlocked() || temp.getPiece().getColor() != this.color)) {
			boolean collision = addIfAvailable(squares, temp, checkForCheck);
			if (collision) {
				break;
			} else {
			temp = temp.getUpRight();
			}
		}
		temp = currentSquare.getDownLeft();
		while (temp != null && (!temp.isBlocked() || temp.getPiece().getColor() != this.color)) {
			boolean collision = addIfAvailable(squares, temp, checkForCheck);
			if (collision) {
				break;
			} else {
			temp = temp.getDownLeft();
			}
		}
		temp = currentSquare.getDownRight();
		while (temp != null && (!temp.isBlocked() || temp.getPiece().getColor() != this.color)) {
			boolean collision = addIfAvailable(squares, temp, checkForCheck);
			if (collision) {
				break;
			} else {
			temp = temp.getDownRight();
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
		result += "B";
		return result;
	}
}
