package board;

import pieces.IPiece;
import pieces.PieceColor;
import pieces.PieceType;
import pieces.implementations.Bishop;
import pieces.implementations.King;
import pieces.implementations.Knight;
import pieces.implementations.Pawn;
import pieces.implementations.Queen;
import pieces.implementations.Rook;

public class PieceFactory {

	public static IPiece createPiece(PieceType type, PieceColor color,
			ISquare square) {
		switch (type) {
		case KING:
			return new King(color, square);
		case BISHOP:
			return new Bishop(color, square);
		case KNIGHT:
			return new Knight(color, square);
		case PAWN:
			return new Pawn(color, square);
		case QUEEN:
			return new Queen(color, square);
		case ROOK:
			return new Rook(color, square);
		default:
			break;
		}
		return null;
	}

}
