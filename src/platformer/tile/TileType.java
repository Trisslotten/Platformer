package platformer.tile;

import platformer.graphics.*;

public class TileType {

	public static TileType falseDoor = falseDoor();
	public static TileType rock = rock();
	public static TileType spike = spike();
	public static TileType breakSpike = breakSpike();
	public static TileType door = door();
	public static TileType bullet = bullet();

	private Sprite sprite;

	private boolean broken = false;
	private boolean doesDamage = false;
	private boolean isDoor = false;
	private boolean isBullet = false;

	private static TileType falseDoor() {
		TileType type = new TileType(new Sprite("assets/door.png"));

		return type;
	}

	private static TileType rock() {
		TileType type = new TileType(new Sprite("assets/tile.png"));

		return type;
	}

	private static TileType spike() {
		TileType type = new TileType(new Sprite("assets/spike.png"));
		type.doesDamage = true;
		return type;
	}

	private static TileType breakSpike() {
		TileType type = new TileType(new Sprite("assets/brokenSpike.png"));
		type.broken = true;
		type.doesDamage = true;
		return type;
	}

	private static TileType door() {
		TileType type = new TileType(new Sprite("assets/door.png"));
		type.isDoor = true;
		return type;
	}

	private static TileType bullet() {
		TileType type = new TileType(new Sprite("assets/bullet.png"));
		type.isBullet = true;
		return type;
	}

	public TileType(Sprite sprite) {
		this.sprite = sprite;
	}

	public void render(int x, int y) {
		sprite.render(x, y);
	}

	public boolean doesDamage() {
		return doesDamage;
	}

	public boolean isBroken() {
		return broken;
	}

	public boolean isDoor() {
		return isDoor;
	}

	public boolean isBullet() {
		// TODO Auto-generated method stub
		return isBullet;
	}

}
