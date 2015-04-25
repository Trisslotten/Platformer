package platformer.level;

import java.util.*;

import platformer.*;
import platformer.tile.*;

public class Map {

	private ArrayList<Area> areas = new ArrayList<Area>();

	private Point playerPos;

	public Area currentArea() {
		return getArea("0");
	}

	public Area getArea(String name) {
		for (Area a : areas) {
			if (a.getName().equals(name)) {
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
			this.areas.add(area);
		}
	}

	public Point playerStart() {
		return playerPos;
	}

	private void parse(Area area, String[] lines, int j) {
		if (lines[j].startsWith("#")) {
			lines[j] = lines[j].trim();
			lines[j] = lines[j].substring(1);
			area.setName(lines[j]);
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
				case 'P':
					playerPos = new Point(x, y);
					break;
				default:
					break;
				}
			}
			
		}
	}

}
