package pieces.implementations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pieces.Piece;
import pieces.PieceColor;
import pieces.PieceType;
import board.ISquare;

public class Pawn extends Piece {

	public Pawn(PieceColor color, ISquare square) {
		super(color, square);
	}

	@Override
	public PieceType getType() {
		return PieceType.PAWN;
	}

	@Override
	public Iterator<ISquare> getPossibleTargets(boolean checkForCheck) {
		if (currentSquare == null) {
			return null;
		}
		List<ISquare> squares = new ArrayList<ISquare>();
		if (color == PieceColor.WHITE) {
			// forward movement only if there's no other piece blocking
			ISquare up = currentSquare.getUp();
			if (up != null && !up.isBlocked()) {
				addIfAvailable(squares, up, checkForCheck);
				// double forward movement only if it's the Pawn's first move
				if (!hasMovedYet()) {
					ISquare twoUp = up.getUp();
					if (twoUp != null && !twoUp.isBlocked()) {
						addIfAvailable(squares, twoUp, checkForCheck);
					}
				}
			}
			// diagonal movement only if there's an enemy piece right there
			ISquare topLeft = currentSquare.getUpLeft();
			if (topLeft != null && topLeft.isBlocked()
					&& topLeft.getPiece().getColor() != this.color) {
				addIfAvailable(squares, topLeft, checkForCheck);
			}
			ISquare topRight = currentSquare.getUpRight();
			if (topRight != null && topRight.isBlocked()
					&& topRight.getPiece().getColor() != this.color) {
				addIfAvailable(squares, topRight, checkForCheck);
			}

		} else if (color == PieceColor.BLACK) {
			// forward movement only if there's no other piece blocking
			ISquare down = currentSquare.getDown();
			if (down != null && !down.isBlocked()) {
				addIfAvailable(squares, down, checkForCheck);
				// double forward movement only if it's the Pawn's first move
				if (!hasMovedYet()) {
					ISquare twoDown = down.getDown();
					if (twoDown != null && !twoDown.isBlocked()) {
						addIfAvailable(squares, twoDown, checkForCheck);
					}
				}
			}
			// diagonal movement only if there's an enemy piece right there
			ISquare downLeft = currentSquare.getDownLeft();
			if (downLeft != null && downLeft.isBlocked()
					&& downLeft.getPiece().getColor() != this.color) {
				addIfAvailable(squares, downLeft, checkForCheck);
			}
			ISquare downRight = currentSquare.getDownRight();
			if (downRight != null && downRight.isBlocked()
					&& downRight.getPiece().getColor() != this.color) {
				addIfAvailable(squares, downRight, checkForCheck);
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
		result += "P";
		return result;
	}
}
