package players;

import java.util.Iterator;
import java.util.Random;

import board.IBoard;
import board.ISquare;

import pieces.IPiece;
import pieces.PieceColor;
import pieces.PieceType;
import processes.PieceMoveProcess;
import processes.ProcessManager;

public class EasyBot implements IPlayer {

	private PieceColor color;
	private int timer = 0;
	private IBoard board;
	private ProcessManager processManager;

	public EasyBot(PieceColor color, IBoard board, ProcessManager processManager) {
		this.color = color;
		this.timer = 0;
		this.board = board;
		this.processManager = processManager;
	}

	@Override
	public void update(int msElapsed) {
		timer += msElapsed;
		ISquare target = null;
		IPiece toMove = null;
		while (target == null) {
			Iterator<IPiece> randomPiece = board.getPieces(color,
					PieceType.randomPiece());
			while (randomPiece.hasNext()) {
				IPiece next = randomPiece.next();
				Iterator<ISquare> targets = next.getPossibleTargets(true);
				while (targets.hasNext()) {
					if (Math.random() < 0.25) {
						target = targets.next();
						toMove = next;
						break;
					}
				}
			}
		}
		processManager.addProcess(new PieceMoveProcess(toMove, target));
	}

	@Override
	public void notifyActivity(PieceColor color) {

	}

	@Override
	public PieceColor getColor() {
		return color;
	}

	@Override
	public boolean isHuman() {
		return false;
	}

}
