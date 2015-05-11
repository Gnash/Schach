package pieces;

import java.util.Random;

public enum PieceType {
	KING(10000),
	QUEEN(9),
	ROOK(4.5f),
	BISHOP(3),
	KNIGHT(3),
	PAWN(1);
	
	PieceType(float points) {
		this.points = points;
	}
	
	private final float points;

  public static PieceType randomPiece()  {
    return PieceType.values()[(int) Math.round(Math.random() * 6)];
  }

public float getPoints() {
	return points;
}
}
