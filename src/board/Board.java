package board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import pieces.IPiece;
import pieces.PieceColor;
import pieces.PieceType;
import players.IPlayer;

public class Board implements IBoard {

	ISquare[][] squares;
	private PieceColor activePlayer;
	Map<PieceType, List<IPiece>> whitePieces;
	Map<PieceType, List<IPiece>> blackPieces;
	List<IMove> lastMoves;
	Map<PieceColor, IPlayer> players;

	public Board() {
		players = new HashMap<PieceColor, IPlayer>();
		lastMoves = new ArrayList<IMove>();
		squares = new ISquare[8][8];
		activePlayer = PieceColor.WHITE;
		initMaps();
		initSquares();
		initPieces();
	}

	public Board(List<IPiece> whitePieceClones, List<IPiece> blackPieceClones,
			PieceColor activePlayer) {
		lastMoves = new ArrayList<IMove>();
		this.activePlayer = activePlayer;
		squares = new ISquare[8][8];
		initMaps();
		initSquares();
		for (IPiece p : whitePieceClones) {
			whitePieces.get(p.getType()).add(
					PieceFactory.createPiece(
							p.getType(),
							p.getColor(),
							getSquare(p.getCurrentSquare().getRow(), p
									.getCurrentSquare().getCol())));
		}
		for (IPiece p : blackPieceClones) {
			blackPieces.get(p.getType()).add(
					PieceFactory.createPiece(
							p.getType(),
							p.getColor(),
							getSquare(p.getCurrentSquare().getRow(), p
									.getCurrentSquare().getCol())));
		}
	}

	@Override
	public IBoard clone() throws CloneNotSupportedException {
		List<IPiece> whitePieceClones = new ArrayList<IPiece>();
		List<IPiece> blackPieceClones = new ArrayList<IPiece>();
		for (PieceType p : whitePieces.keySet()) {
			whitePieceClones.addAll(whitePieces.get(p));
		}
		for (PieceType p : blackPieces.keySet()) {
			blackPieceClones.addAll(blackPieces.get(p));
		}
		return new Board(whitePieceClones, blackPieceClones, this.activePlayer);
	}

	private void initMaps() {
		whitePieces = new HashMap<PieceType, List<IPiece>>();
		blackPieces = new HashMap<PieceType, List<IPiece>>();

		whitePieces.put(PieceType.ROOK, new ArrayList<IPiece>());
		whitePieces.put(PieceType.KNIGHT, new ArrayList<IPiece>());
		whitePieces.put(PieceType.BISHOP, new ArrayList<IPiece>());
		whitePieces.put(PieceType.QUEEN, new ArrayList<IPiece>());
		whitePieces.put(PieceType.KING, new ArrayList<IPiece>());
		whitePieces.put(PieceType.PAWN, new ArrayList<IPiece>());
		blackPieces.put(PieceType.ROOK, new ArrayList<IPiece>());
		blackPieces.put(PieceType.KNIGHT, new ArrayList<IPiece>());
		blackPieces.put(PieceType.BISHOP, new ArrayList<IPiece>());
		blackPieces.put(PieceType.QUEEN, new ArrayList<IPiece>());
		blackPieces.put(PieceType.KING, new ArrayList<IPiece>());
		blackPieces.put(PieceType.PAWN, new ArrayList<IPiece>());
	}

	private void initPieces() {
		// Add white pieces to the board
		whitePieces.get(PieceType.ROOK).add(
				PieceFactory.createPiece(PieceType.ROOK, PieceColor.WHITE,
						getSquare(1, 1)));
		whitePieces.get(PieceType.KNIGHT).add(
				PieceFactory.createPiece(PieceType.KNIGHT, PieceColor.WHITE,
						getSquare(1, 2)));
		whitePieces.get(PieceType.BISHOP).add(
				PieceFactory.createPiece(PieceType.BISHOP, PieceColor.WHITE,
						getSquare(1, 3)));
		whitePieces.get(PieceType.QUEEN).add(
				PieceFactory.createPiece(PieceType.QUEEN, PieceColor.WHITE,
						getSquare(1, 4)));
		whitePieces.get(PieceType.KING).add(
				PieceFactory.createPiece(PieceType.KING, PieceColor.WHITE,
						getSquare(1, 5)));
		whitePieces.get(PieceType.BISHOP).add(
				PieceFactory.createPiece(PieceType.BISHOP, PieceColor.WHITE,
						getSquare(1, 6)));
		whitePieces.get(PieceType.KNIGHT).add(
				PieceFactory.createPiece(PieceType.KNIGHT, PieceColor.WHITE,
						getSquare(1, 7)));
		whitePieces.get(PieceType.ROOK).add(
				PieceFactory.createPiece(PieceType.ROOK, PieceColor.WHITE,
						getSquare(1, 8)));

		for (int col = 1; col <= 8; col++) {
			whitePieces.get(PieceType.PAWN).add(
					PieceFactory.createPiece(PieceType.PAWN, PieceColor.WHITE,
							getSquare(2, col)));
		}

		// Add black pieces to the board
		for (int col = 1; col <= 8; col++) {
			blackPieces.get(PieceType.PAWN).add(
					PieceFactory.createPiece(PieceType.PAWN, PieceColor.BLACK,
							getSquare(7, col)));
		}
		blackPieces.get(PieceType.ROOK).add(
				PieceFactory.createPiece(PieceType.ROOK, PieceColor.BLACK,
						getSquare(8, 1)));
		blackPieces.get(PieceType.KNIGHT).add(
				PieceFactory.createPiece(PieceType.KNIGHT, PieceColor.BLACK,
						getSquare(8, 2)));
		blackPieces.get(PieceType.BISHOP).add(
				PieceFactory.createPiece(PieceType.BISHOP, PieceColor.BLACK,
						getSquare(8, 3)));
		blackPieces.get(PieceType.QUEEN).add(
				PieceFactory.createPiece(PieceType.QUEEN, PieceColor.BLACK,
						getSquare(8, 4)));
		blackPieces.get(PieceType.KING).add(
				PieceFactory.createPiece(PieceType.KING, PieceColor.BLACK,
						getSquare(8, 5)));
		blackPieces.get(PieceType.BISHOP).add(
				PieceFactory.createPiece(PieceType.BISHOP, PieceColor.BLACK,
						getSquare(8, 6)));
		blackPieces.get(PieceType.KNIGHT).add(
				PieceFactory.createPiece(PieceType.KNIGHT, PieceColor.BLACK,
						getSquare(8, 7)));
		blackPieces.get(PieceType.ROOK).add(
				PieceFactory.createPiece(PieceType.ROOK, PieceColor.BLACK,
						getSquare(8, 8)));
	}

	private void initSquares() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				squares[row][col] = new Square(this, 8 - row, col + 1);
			}
		}
	}

	@Override
	public String toString() {
		String result = "";
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (squares[row][col] == getSquare(row + 1, col + 1)) {
					result += "FAILED: " + row + ", " + col + "\n";
				}
			}
		}

		result += "  A  B  C  D  E  F  G  H" + "\n";
		for (int row = 0; row < 8; row++) {
			String line = "";
			line += (8 - row) + " ";
			for (int col = 0; col < 8; col++) {
				ISquare sq = squares[row][col];
				line += sq.toString() + " ";
			}
			result += (line) + "\n";
		}
		return result;
	}

	public ISquare getSquare(int row, int col) {
		if (row > 0 && row < 9 && col > 0 && col < 9) {
			return squares[8 - row][col - 1];
		} else {
			return null;
		}
	}

	@Override
	public Iterator<IPiece> getPieces(PieceColor color, PieceType type) {
		if (color == PieceColor.WHITE && whitePieces != null) {
			List<IPiece> pieces = whitePieces.get(type);
			if (pieces == null) {
				return null;
			} else {
				return pieces.iterator();
			}
		} else if (color == PieceColor.BLACK && blackPieces != null) {
			List<IPiece> pieces = blackPieces.get(type);
			if (pieces == null) {
				return null;
			} else {
				return pieces.iterator();
			}
		} else {
			return null;
		}
	}

	@Override
	public void removePiece(IPiece piece) {
		if (piece == null) {
			return;
		}
		if (piece.getColor() == PieceColor.WHITE) {
			List<IPiece> pieces = whitePieces.get(piece.getType());
			if (pieces != null) {
				pieces.remove(piece);
			}
		} else if (piece.getColor() == PieceColor.BLACK) {
			List<IPiece> pieces = blackPieces.get(piece.getType());
			if (pieces != null) {
				pieces.remove(piece);
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		g.setBackground(Color.darkGray);
		for (int row = 1; row <= 8; row++) {
			for (int col = 1; col <= 8; col++) {
				getSquare(row, col).draw(g);
			}
		}
	}

	@Override
	public boolean isInCheck(PieceColor color) {
		if (color == PieceColor.WHITE) {
			for (PieceType type : blackPieces.keySet()) {
				List<IPiece> pieces = blackPieces.get(type);
				if (pieces == null) {
					continue;
				}
				for (IPiece p : pieces) {
					Iterator<ISquare> possibleTargets = p
							.getPossibleTargets(false);
					while (possibleTargets.hasNext()) {
						ISquare s = possibleTargets.next();
						if (s.getPiece() != null
								&& s.getPiece().getType() == PieceType.KING) {
							return true;
						}
					}
				}
			}
		} else {
			for (PieceType type : whitePieces.keySet()) {
				List<IPiece> pieces = whitePieces.get(type);
				if (pieces == null) {
					continue;
				}
				for (IPiece p : pieces) {
					Iterator<ISquare> possibleTargets = p
							.getPossibleTargets(false);
					while (possibleTargets.hasNext()) {
						ISquare s = possibleTargets.next();
						if (s.getPiece() != null
								&& s.getPiece().getType() == PieceType.KING) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public PieceColor getActivePlayerColor() {
		return this.activePlayer;
	}

	@Override
	public void changeActivePlayer() {
		if (activePlayer == PieceColor.WHITE) {
			activePlayer = PieceColor.BLACK;
		} else {
			activePlayer = PieceColor.WHITE;
		}
		checkForCheckMate();
	}

	private void checkForCheckMate() {
		boolean checkMate = true;
		Map<PieceType, List<IPiece>> activePieces = null;
		if (activePlayer == PieceColor.WHITE) {
			activePieces = whitePieces;
		} else {
			activePieces = blackPieces;
		}
		for (List<IPiece> l : activePieces.values()) {
			for (IPiece piece : l) {
				Iterator<ISquare> temp = piece.getPossibleTargets(true);
				int count = 0;
				while (temp.hasNext()) {
					temp.next();
					count++;
				}
				if (count > 0) {
					checkMate = false;
					break;
				}
			}
			if (!checkMate) {
				break;
			}
		}
		if (checkMate) {
			System.out.println(activePlayer + " hat verloren!");
		}
	}

	@Override
	public IMove getLastMove() {
		if (lastMoves == null) {
			return null;
		}
		return lastMoves.get(lastMoves.size() - 1);
	}

	@Override
	public Iterator<IMove> getLastMoves(int amount) {
		if (amount > lastMoves.size()) {
			amount = lastMoves.size();
		}
		return lastMoves.subList(lastMoves.size() - 1 - amount,
				lastMoves.size() - 1).iterator();
	}

	@Override
	public void setPlayer(PieceColor color, IPlayer player) {
		if (players != null) {
			players.put(color, player);
		}
	}

	@Override
	public IPlayer getActivePlayer() {
		return players.get(activePlayer);
	}

	@Override
	public void addMove(IPiece piece, ISquare start, ISquare target,
			IPiece killedPiece) {
		IMove m = new Move(piece, start, target, killedPiece);
		this.lastMoves.add(m);
	}

	@Override
	public Iterator<IPiece> getAllPieces(PieceColor color) {
		List<IPiece> result = new ArrayList<IPiece>();
		Map<PieceType, List<IPiece>> pieceMap = null;
		if (color == PieceColor.WHITE) {
			pieceMap = whitePieces;
		} else {
			pieceMap = blackPieces;
		}
		for (List<IPiece> list : pieceMap.values()) {
			result.addAll(list);
		}
		return result.iterator();
	}

	@Override
	public float getCurrentStandings() {
		float result = 0;
		for (PieceType type : whitePieces.keySet()) {
			result += type.getPoints() * whitePieces.get(type).size();
		}
		for (PieceType type : blackPieces.keySet()) {
			result -= type.getPoints() * blackPieces.get(type).size();
		}
		return result;
	}

	@Override
	public void undoLastMove() {
		IMove lastMove = lastMoves.remove(lastMoves.size() - 1);
		lastMove.getPiece().setCurrentSquare(lastMove.getStart());
		lastMove.getPiece().decreaseMoveCount();
		if (lastMove.killedPiece() != null) {
			IPiece removedPiece = PieceFactory.createPiece(
					lastMove.killedPiece(), lastMove.getColor().getOpposite(),
					lastMove.getTarget());
			if (removedPiece.getColor() == PieceColor.BLACK) {
				blackPieces.get(removedPiece.getType()).add(removedPiece);
			} else {
				whitePieces.get(removedPiece.getType()).add(removedPiece);
			}
		}
	}

}
