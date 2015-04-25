package platformer.entity.types;

import platformer.entity.*;
import platformer.graphics.*;
import platformer.level.*;
import platformer.tile.*;

public abstract class Mob extends Entity {

	public Mob(Map map) {
		super(map);
		// TODO Auto-generated constructor stub
	}

	public Sprite currentSprite;

	protected int width, height;

	protected int health, jumpSpd;

	public boolean nextToWall() {
		for (Tile t : map.currentArea().tiles) {
			if (left() <= t.right() && right() >= t.left() && bot() <= t.top() && top() >= t.bot() && xspd != 0) {
				if(xspd>0) {
					xspd = -2;
				} else {
					xspd = 2;
				}
				return true;
			}
		}
		return false;
	}

	public boolean isOnGround() {
		for (Tile t : map.currentArea().tiles) {
			if (left() <= t.right() && right() >= t.left() && bot() <= t.top() && top() >= t.bot() && yspd <= 0) {
				return true;
			}
		}
		return false;
	}

	public int bot() {
		return (int) y;
	}

	public int top() {
		return (int) y + height;
	}

	public int left() {
		return (int) x;
	}

	public int right() {
		return (int) x + width;
	}

}