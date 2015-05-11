package players;

import board.IMove;

public class MiniMaxMove {
	private IMove move;
	private float score;
	
	public MiniMaxMove(IMove move, float score) {
		this.move = move;
		this.score = score;
	}
	
	public IMove getMove() {
		return move;
	}
	public void setMove(IMove move) {
		this.move = move;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
}
