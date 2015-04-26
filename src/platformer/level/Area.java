package platformer.level;

import java.util.*;

import platformer.*;
import platformer.graphics.*;
import platformer.tile.*;

public class Area {

	private int level;

	private int x, y;

	private int width;
	private int height;

	private Sprite background = new Sprite("assets/background.png");

	public Point playerStart;

	public ArrayList<Point> bullets = new ArrayList<Point>();

	public ArrayList<Point> spikes = new ArrayList<Point>();

	public ArrayList<Tile> tiles = new ArrayList<Tile>();

	public void add(Tile tile) {
		tiles.add(tile);
	}

	public void render(int xoffset, int yoffset) {
		for (int i = -10; i < 30; i++) {
			for (int j = -10; j < 30; j++) {

				background.render(j * 128 - xoffset / 2, i * 128 - yoffset / 2);

			}
		}
		for (Tile t : tiles) {
			t.render(xoffset, yoffset);
		}

	}

	public void resetArea() {
		for (Point p : bullets) {
			boolean canAdd = true;
			for (Tile t : tiles) {
				if ((t.x() == p.x && t.y() == p.y)) {
					canAdd = false;
				}
			}
			if (canAdd)
				tiles.add(new Tile(TileType.bullet, p.x, p.y));
		}
		for (Point p : spikes) {
			boolean canAdd = true;
			for (Tile t : tiles) {
				if ((t.x() == p.x && t.y() == p.y)) {
					canAdd = false;
				}
			}
			if (canAdd)
				tiles.add(new Tile(TileType.breakSpike, p.x, p.y));
		}
	}

	public void setBullets() {
		for (Tile t : tiles) {
			if (t.isBullet()) {
				bullets.add(new Point(t.x(), t.y()));
			} else if (t.isBroken()) {
				spikes.add(new Point(t.x(), t.y()));
			}
		}
	}

	public Area() {

	}

	public void setLevel(int i) {
		level = i;
	}

	public int getLevel() {
		return level;
	}

}
