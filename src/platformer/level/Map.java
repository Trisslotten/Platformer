package platformer.level;

import java.util.*;

import platformer.*;
import platformer.tile.*;

public class Map {

	private ArrayList<Area> areas = new ArrayList<Area>();

	public double pickupCounter = 0;
	public boolean pickedUp = false;
	

	public int level = 3;

	public boolean changedLevel = false;

	public Area currentArea() {
		return getArea();
	}

	public void acknowledgeLevelChange() {
		changedLevel = false;
	}

	public void nextLevel() {
		level++;
		changedLevel = true;
	}

	public Area getArea() {
		for (Area a : areas) {
			if (level == a.getLevel()) {
				return a;
			}
		}
		return null;
	}

	public Map() {

		String map = Filer.stringFromFile("assets/Maps.txt");
		String[] areas = map.split(";");
		for (int i = 0; i < areas.length; i++) {

			Area area = new Area();

			String[] lines = areas[i].split("\n");
			for (int j = 0; j < lines.length; j++) {
				parse(area, lines, j);
			}
			area.setBullets();
			this.areas.add(area);
		}
	}

	private void parse(Area area, String[] lines, int j) {
		if (lines[j].startsWith("#")) {
			lines[j] = lines[j].trim();
			lines[j] = lines[j].substring(1);
			area.setLevel(Integer.parseInt(lines[j]));
		} else if (lines[j].startsWith(":")) {
			lines[j] = lines[j].trim();
			lines[j] = lines[j].substring(1);
			String[] doorData = lines[j].split(" ");
			// DO STUFF HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		} else {
			for (int k = 0; k < lines[j].length(); k++) {
				int x = k;
				int y = -j;
				switch (lines[j].charAt(k)) {
				case 'R':
					area.add(Tile.rock(x, y));
					break;
				case 'S':
					area.add(Tile.spike(x, y));
					break;
				case 'F':
					area.add(Tile.falseDoor(x, y));
					break;
				case 'D':
					area.add(Tile.door(x, y));
					break;
				case 'B':
					area.add(Tile.bullet(x, y));
					break;
				case 'T':
					area.add(Tile.breakSpike(x, y));
					break;
				case 'P':
					area.playerStart = new Point(x, y);
					break;
				default:
					break;
				}
			}

		}
	}

}
