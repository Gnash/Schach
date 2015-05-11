package board;

import handlers.ResourceManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import pieces.IPiece;

public class Square implements ISquare {

	public static final int SQUARE_WIDTH = 50;
	public static final int SQUARE_HEIGHT = 50;
	private IBoard board;
	private int row;
	private int col;
	private IPiece piece;
	private boolean highlighted;

	public Square(IBoard board, int row, int col) {
		this.board = board;
		this.row = row;
		this.col = col;
	}

	@Override
	public ISquare clone() throws CloneNotSupportedException {
		return null;
	}

	@Override
	public int getRow() {
		return row;
	}

	@Override
	public int getCol() {
		return col;
	}

	@Override
	public boolean isBlocked() {
		return (this.piece != null);
	}

	@Override
	public IPiece getPiece() {
		return this.piece;
	}

	@Override
	public ISquare getLeft() {
		if (this.board != null && this.col > 1) {
			return board.getSquare(this.row, this.col - 1);
		} else {
			return null;
		}
	}

	@Override
	public ISquare getRight() {
		if (this.board != null && this.col < 8) {
			return board.getSquare(this.row, this.col + 1);
		} else {
			return null;
		}
	}

	@Override
	public ISquare getUp() {
		if (this.board != null && this.row < 8) {
			return board.getSquare(this.row + 1, this.col);
		} else {
			return null;
		}
	}

	@Override
	public ISquare getDown() {
		if (this.board != null && this.row > 1) {
			return board.getSquare(this.row - 1, this.col);
		} else {
			return null;
		}
	}

	@Override
	public ISquare getUpLeft() {
		if (this.board != null && this.row < 8 && this.col > 1) {
			return board.getSquare(this.row + 1, this.col - 1);
		} else {
			return null;
		}
	}

	@Override
	public ISquare getDownLeft() {
		if (this.board != null && this.row > 1 && this.col > 1) {
			return board.getSquare(this.row - 1, this.col - 1);
		} else {
			return null;
		}
	}

	@Override
	public ISquare getUpRight() {
		if (this.board != null && this.row < 8 && this.col < 8) {
			return board.getSquare(this.row + 1, this.col + 1);
		} else {
			return null;
		}
	}

	@Override
	public ISquare getDownRight() {
		if (this.board != null && this.row > 1 && this.col < 8) {
			return board.getSquare(this.row - 1, this.col + 1);
		} else {
			return null;
		}
	}

	@Override
	public String toString() {

		if (this.piece == null) {
			return "[]";
		} else {
			return this.piece.toString();
		}
	}

	@Override
	public void setPiece(IPiece piece) {
		this.piece = piece;
	}

	@Override
	public void clear(boolean killPiece) {
		if (killPiece) {
			this.board.removePiece(this.piece);
		}
		this.piece = null;
	}

	@Override
	public void draw(Graphics g) {
		if ((row + col) % 2 == 0) {
			String imageName = "blacksquare";
			if (isHighlighted()) {
				imageName += "hl";
			}
			g.drawImage(ResourceManager.getImage(imageName), (col - 1)
					* SQUARE_WIDTH, (8 - row) * SQUARE_HEIGHT);
		} else {
			String imageName = "whitesquare";
			if (isHighlighted()) {
				imageName += "hl";
			}
			g.drawImage(ResourceManager.getImage(imageName), (col - 1)
					* SQUARE_WIDTH, (8 - row) * SQUARE_HEIGHT);
		}
		if (piece != null) {
			piece.draw(g);
		}
	}

	@Override
	public boolean isHighlighted() {
		return this.highlighted;
	}

	@Override
	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}

	@Override
	public IBoard getBoard() {
		return board;
	}
}
