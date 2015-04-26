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

	public static Tile breakSpike(int x, int y) {
		return new Tile(TileType.breakSpike, x, y);
	}

	public static Tile bullet(int x, int y) {
		return new Tile(TileType.bullet, x, y);
	}

	public static Tile falseDoor(int x2, int y2) {
		return new Tile(TileType.falseDoor, x2, y2);
	}

	public static Tile door(int x2, int y2) {
		return new Tile(TileType.door, x2, y2);
	}

	public boolean isDoor() {
		return type.isDoor();
	}

	public boolean isBullet() {
		return type.isBullet();
	}

	public boolean isBroken() {
		return type.isBroken();
	}

	public boolean doesDamage() {
		return type.doesDamage();
	}

	public Tile(TileType type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
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
