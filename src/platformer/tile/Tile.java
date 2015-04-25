package platformer.tile;

import platformer.graphics.*;

public class Tile {

	public static int size = 64;

	private TileType type;

	private int x, y;

	public static Tile rock(int x, int y) {
		return new Tile(TileType.rock, x, y);
	}

	public static Tile spike(int x, int y) {
		return new Tile(TileType.spike, x, y);
	}

	public Tile(TileType type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}

	public void render(int xoffset, int yoffset) {

		type.render(x * size - xoffset, y * size - yoffset);

	}

	public void setX(int x2) {
		x = x2;
	}

	public void setY(int y2) {
		y = y2;
	}

	public int bot() {
		return (int) y * size;
	}

	public int top() {
		return (int) (y + 1) * size;
	}

	public int left() {
		return (int) x * size;
	}

	public int right() {
		return (int) (x + 1) * size;
	}

}
