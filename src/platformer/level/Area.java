package platformer.level;

import java.util.*;

import platformer.entity.*;
import platformer.graphics.*;
import platformer.tile.*;

public class Area {

	private String name;

	private int x, y;

	private int width;
	private int height;

	private Sprite background = new Sprite("assets/background.png");

	public ArrayList<Tile> tiles = new ArrayList<Tile>();

	public void add(Tile tile) {
		tiles.add(tile);
	}

	public void render(int xoffset, int yoffset) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {

				background.render(j * 128, i * 128);

			}
		}
		for (Tile t : tiles) {
			t.render(xoffset, yoffset);
		}

	}

	public Area() {

	}

	public void setName(String string) {
		name = string;
	}

	public String getName() {
		return name;
	}

}
