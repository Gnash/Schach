package pieces;

public enum PieceColor {
	BLACK,
	WHITE,
	BOTH;
	
	public PieceColor getOpposite() {
		if (this == BLACK) {
			return WHITE;
		} else {
			return BLACK;
		}
	}
}
