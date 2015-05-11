package players;

import java.util.Iterator;
import board.IBoard;
import board.IMove;
import board.ISquare;

import pieces.IPiece;
import pieces.PieceColor;
import processes.PieceMoveProcess;
import processes.ProcessManager;

public class MediumBot implements IPlayer {

	private PieceColor color;
	private int timer = 0;
	private IBoard board;
	private ProcessManager processManager;
	private static final int MAX_DEPTH = 2;

	public MediumBot(PieceColor color, IBoard board,
			ProcessManager processManager) {
		this.color = color;
		this.timer = 0;
		this.board = board;
		this.processManager = processManager;
	}

	@Override
	public void update(int msElapsed) {
		timer += msElapsed;
		if (timer > 1000) {
			timer = 0;
			Iterator<IPiece> bla = board.getAllPieces(color);
			IMove bestMove = minMax(board, color, 0).getMove();
			processManager.addProcess(new PieceMoveProcess(bestMove.getPiece(),
					bestMove.getTarget()));
			timer = 0;
		}
	}

	private MiniMaxMove minMax(IBoard board, PieceColor player, int depth) {
		MiniMaxMove bestMove = null;
		float bestScore = 0;
		if (depth == MAX_DEPTH) {
			return new MiniMaxMove(null, board.getLastMove().getBoardStandings(player));
		}
		Iterator<IPiece> availablePieces = board.getAllPieces(board
				.getActivePlayerColor());
		while (availablePieces.hasNext()) {
			IPiece currentPiece = availablePieces.next();
			Iterator<ISquare> availableMoves = currentPiece
					.getPossibleTargets(true);
			while (availableMoves.hasNext()) {
				ISquare currentTarget = availableMoves.next();
				currentPiece.move(currentTarget);

				MiniMaxMove currentMove = minMax(board, player.getOpposite(),
						depth + 1);
				float score = currentMove.getScore();
				currentMove.setMove(board.getLastMove());
				board.undoLastMove();
				if (bestMove == null
						|| score > bestScore && player == color || score < bestScore && player != color) {
					bestMove = currentMove;
					bestScore = score;
				}
			}
		}
		return bestMove;
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
