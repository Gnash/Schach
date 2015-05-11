package board;

import pieces.IPiece;
import pieces.MoveType;
import pieces.PieceColor;
import pieces.PieceType;

public class Move implements IMove {

	private IPiece piece;
	private ISquare target;
	private ISquare start;
	private MoveType type;
	private PieceType killedPiece;
	private float boardStandings;
	
	public Move(IPiece piece, ISquare start, ISquare target, IPiece killedPiece) {
		this.piece = piece;
		this.start = start;
		this.target = target;
		if (killedPiece == null) {
			this.type = MoveType.MOVE;
			this.killedPiece = null;
		} else {
			this.type = MoveType.KILL;
			this.killedPiece = killedPiece.getType();
		}
		this.boardStandings = this.start.getBoard().getCurrentStandings();
	}
	
	@Override
	public IPiece getPiece() {
		return piece;
	}

	@Override
	public ISquare getTarget() {
		return target;
	}

	@Override
	public ISquare getStart() {
		return start;
	}

	@Override
	public MoveType getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Move " + piece.getType() + " from (" + start.getRow() + ", " + start.getCol() + ") to (" + target.getRow() + ", " + target.getCol() +").";
	}

	@Override
	public PieceType killedPiece() {
		return killedPiece;
	}

	@Override
	public PieceColor getColor() {
		return piece.getColor();
	}

	@Override
	public float getBoardStandings(PieceColor player) {
		float result = target.getBoard().getCurrentStandings();
		if (player == PieceColor.BLACK) {
			result *= -1;
		}
		return result;
	}

}
