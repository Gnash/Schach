package pieces.implementations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pieces.Piece;
import pieces.PieceColor;
import pieces.PieceType;
import board.ISquare;

public class Knight extends Piece {

	public Knight(PieceColor color, ISquare square) {
		super(color, square);
	}

	@Override
	public PieceType getType() {
		return PieceType.KNIGHT;
	}

	@Override
	public Iterator<ISquare> getPossibleTargets(boolean checkForCheck) {
		List<ISquare> squares = new ArrayList<ISquare>();
		// Check the upper 2 squares
		ISquare temp = currentSquare.getUp();
		if (temp != null) {
			temp = temp.getUp();
			if (temp != null) {
				ISquare target1 = temp.getLeft();
				ISquare target2 = temp.getRight();
				addIfAvailable(squares, target1, checkForCheck);
				addIfAvailable(squares, target2, checkForCheck);
			}
		}
		// Check the lower 2 squares
		temp = currentSquare.getDown();
		if (temp != null) {
			temp = temp.getDown();
			if (temp != null) {
				ISquare target1 = temp.getLeft();
				ISquare target2 = temp.getRight();
				addIfAvailable(squares, target1, checkForCheck);
				addIfAvailable(squares, target2, checkForCheck);
			}
		}

		// Check the lower 2 squares
		temp = currentSquare.getDown();
		if (temp != null) {
			temp = temp.getDown();
			if (temp != null) {
				ISquare target1 = temp.getLeft();
				ISquare target2 = temp.getRight();
				addIfAvailable(squares, target1, checkForCheck);
				addIfAvailable(squares, target2, checkForCheck);
			}
		}

		// Check the left 2 squares
		temp = currentSquare.getLeft();
		if (temp != null) {
			temp = temp.getLeft();
			if (temp != null) {
				ISquare target1 = temp.getUp();
				ISquare target2 = temp.getDown();
				addIfAvailable(squares, target1, checkForCheck);
				addIfAvailable(squares, target2, checkForCheck);
			}
		}
		// Check the right 2 squares
		temp = currentSquare.getRight();
		if (temp != null) {
			temp = temp.getRight();
			if (temp != null) {
				ISquare target1 = temp.getUp();
				ISquare target2 = temp.getDown();
				addIfAvailable(squares, target1, checkForCheck);
				addIfAvailable(squares, target2, checkForCheck);
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
		result += "N";
		return result;
	}
}
