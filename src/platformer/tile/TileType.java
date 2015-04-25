package platformer.tile;

import platformer.graphics.*;

public class TileType {

	public static TileType rock = new TileType(new Sprite("assets/tile.png"));
	public static TileType spike = new TileType(new Sprite("assets/spike.png"));

	private Sprite sprite;
	
	public TileType(Sprite sprite) {
		this.sprite = sprite;
	}

	public void render(int x, int y) {
		sprite.render(x, y);
	}

}
