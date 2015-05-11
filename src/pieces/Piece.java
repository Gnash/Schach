package pieces;

import handlers.ResourceManager;

import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import board.IBoard;
import board.ISquare;
import board.Square;

public abstract class Piece implements IPiece {

	protected ISquare currentSquare;
	protected PieceColor color;
	private boolean active;
	private int moveCount;

	protected Piece(PieceColor color, ISquare square) {
		this.moveCount = 0;
		this.active = false;
		this.color = color;
		this.currentSquare = square;
		square.setPiece(this);
	}

	protected boolean addIfAvailable(List<ISquare> squareList, ISquare square,
			boolean checkForCheck) {
		if (square != null
				&& (!square.isBlocked() || square.getPiece().getColor() != this.color)) {
			IBoard b = null;
			try {
				b = getCurrentSquare().getBoard().clone();

				if (checkForCheck && b != null) {
					IPiece clonedPiece = b.getSquare(currentSquare.getRow(),
							currentSquare.getCol()).getPiece();
					ISquare clonedTarget = b.getSquare(square.getRow(),
							square.getCol());
					clonedTarget.clear(true);
					clonedPiece.setCurrentSquare(clonedTarget);
					if (!b.isInCheck(getColor())) {
						squareList.add(square);
					}
				} else {
					squareList.add(square);
				}
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		return (square == null || square.isBlocked());
	}

	@Override
	public MoveType move(ISquare target) {
		if (currentSquare == null) {
			return MoveType.FAIL;
		}
		Iterator<ISquare> possibleTargets = getPossibleTargets(true);
		while (possibleTargets.hasNext()) {
			ISquare next = possibleTargets.next();
			if (next == target) {
				MoveType result;
				if (target.getPiece() != null) {
					result = MoveType.KILL;
				} else {
					result = MoveType.MOVE;
				}
				currentSquare.getBoard().addMove(this, currentSquare, target, target.getPiece());
				target.clear(target.getPiece() != null);
				currentSquare.clear(false);
				target.setPiece(this);
				currentSquare = target;
				moveCount++;
				return result;
			}
		}
		return MoveType.FAIL;
	}

	@Override
	public void setCurrentSquare(ISquare square) {
		if (this.currentSquare != null) {
			this.currentSquare.setPiece(null);
		}
		this.currentSquare = square;
		if (square != null) {
			square.setPiece(this);
		}
	}

	@Override
	public void draw(Graphics g) {
		String color = "";
		String type = "";
		if (getColor() == PieceColor.WHITE) {
			color = "white";
		} else {
			color = "black";
		}
		switch (getType()) {
		case BISHOP:
			type = "bishop";
			break;
		case KING:
			type = "king";
			break;
		case KNIGHT:
			type = "knight";
			break;
		case PAWN:
			type = "pawn";
			break;
		case QUEEN:
			type = "queen";
			break;
		case ROOK:
			type = "rook";
			break;
		default:
			type = "";
			break;
		}
		Image i = ResourceManager.getImage(color + type);
		if (i != null) {
			if (isActive()) {
				g.drawImage(i, (currentSquare.getCol() - 1)
						* Square.SQUARE_WIDTH, (8 - currentSquare.getRow())
						* Square.SQUARE_HEIGHT, Color.red);
			} else {
				g.drawImage(i, (currentSquare.getCol() - 1)
						* Square.SQUARE_WIDTH, (8 - currentSquare.getRow())
						* Square.SQUARE_HEIGHT);
			}
		}
	}

	@Override
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {

		this.active = active;
		Iterator<ISquare> targets = getPossibleTargets(true);
		while (targets.hasNext()) {
			ISquare s = targets.next();
			s.setHighlighted(active);
		}
	}

	@Override
	public ISquare getCurrentSquare() {
		return currentSquare;
	}

	@Override
	public void decreaseMoveCount() {
		if (moveCount > 0) {
			moveCount--;
		}
	}

	@Override
	public PieceColor getColor() {
		return color;
	}

	@Override
	public boolean isWhite() {
		return (color == PieceColor.WHITE);
	}

	@Override
	public boolean isBlack() {
		return (color == PieceColor.BLACK);
	}

	@Override
	public boolean hasMovedYet() {
		return moveCount > 0;
	}
}
