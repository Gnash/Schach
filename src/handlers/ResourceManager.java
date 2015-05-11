package handlers;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class ResourceManager {
	private static final String PATH_PIECES = "res/pieces/";
	private static final String PATH_BOARD = "res/board/";
	private static Map<String, Image> images = new HashMap<String, Image>();

	public static void loadPieces() {
		try {
			images.put("whitepawn", new Image(PATH_PIECES + "whitepawn.png"));
			images.put("whiteking", new Image(PATH_PIECES + "whiteking.png"));
			images.put("whiteknight", new Image(PATH_PIECES + "whiteknight.png"));
			images.put("whitequeen", new Image(PATH_PIECES + "whitequeen.png"));
			images.put("whitebishop", new Image(PATH_PIECES + "whitebishop.png"));
			images.put("whiterook", new Image(PATH_PIECES + "whiterook.png"));
			images.put("blackpawn", new Image(PATH_PIECES + "blackpawn.png"));
			images.put("blackking", new Image(PATH_PIECES + "blackking.png"));
			images.put("blackknight", new Image(PATH_PIECES + "blackknight.png"));
			images.put("blackqueen", new Image(PATH_PIECES + "blackqueen.png"));
			images.put("blackbishop", new Image(PATH_PIECES + "blackbishop.png"));
			images.put("blackrook", new Image(PATH_PIECES + "blackrook.png"));
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadBoard() {
		try {
			images.put("whitesquare", new Image(PATH_BOARD + "whitesquare.png"));
			images.put("blacksquare", new Image(PATH_BOARD + "blacksquare.png"));
			images.put("whitesquarehl", new Image(PATH_BOARD + "whitesquarehl.png"));
			images.put("blacksquarehl", new Image(PATH_BOARD + "blacksquarehl.png"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static Image getImage(String index) {
		return images.get(index);
	}
}
