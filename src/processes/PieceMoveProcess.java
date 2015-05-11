package processes;

import board.ISquare;
import pieces.IPiece;
import pieces.MoveType;

public class PieceMoveProcess extends Process {

	private IPiece piece;
	private ISquare target;

	public PieceMoveProcess(IPiece piece, ISquare target) {
		super();
		this.piece = piece;
		this.target = target;
	}
	
	@Override
	public void update(long nanoSeconds) {
		MoveType result = piece.move(target);
		if (result == MoveType.FAIL) {
			fail();
		} else {
			finish();
		}
	}

	@Override
	protected void onInit() {

	}

	@Override
	protected void onAbort() {

	}

	@Override
	protected void onFail() {

	}

	@Override
	protected void onFinish() {
		if (target != null) {
			target.getBoard().changeActivePlayer();
		}
	}

}
