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

	public int bullets = 0;

	protected boolean leftColl, isOnGround, rightColl, topColl;

	protected int width, height;

	protected int health, jumpSpd;

	public void handleVertCollision(double delta) {
		topColl = false;
		isOnGround = false;
		int i = 0;
		int index = -1;
		for (Tile t : map.currentArea().tiles) {
			if (collided(t) && !t.isBullet()) {
				if (t.doesDamage()) {
					remove = true;
					return;
				}
				if (t.isDoor()) {
					map.nextLevel();
				}

				int xo = getXoverlap(t);
				int yo = getYoverlap(t);

				y -= yspd * delta;
				if (yspd > 0) {
					topColl = true;
					yspd = -10;
				} else {
					isOnGround = true;
					if (yspd != 0)
						y = t.top();

				}
			} else if (collided(t) && t.isBullet()) {
				index = i;
			}
			i++;
		}
		if (index >= 0) {
			map.currentArea().tiles.remove(index);
			bullets++;
		}
	}

	public void handleHoriCollision(double delta) {
		rightColl = false;
		leftColl = false;
		int i = 0;
		int index = -1;
		for (Tile t : map.currentArea().tiles) {
			if (collided(t) && !t.isBullet()) {
				if (t.doesDamage()) {
					remove = true;
					return;
				}
				if (t.isDoor()) {
					map.nextLevel();
				}

				int xo = getXoverlap(t);
				int yo = getYoverlap(t);
				if (yo > xo) {
					x -= xspd * delta;
					xspd = 0;
				}

				if (xspd > 0) {
					rightColl = true;
				} else if (xspd < 0) {
					leftColl = true;
				}

			} else if (collided(t) && t.isBullet()) {
				index = i;
			}
			i++;
		}
		if (index >= 0) {
			map.currentArea().tiles.remove(index);
			bullets++;
			map.pickedUp = true;
		}
	}

	private int getYoverlap(Tile t) {
		int side1 = Math.abs(top() - t.bot());
		int side2 = Math.abs(bot() - t.top());
		if (side1 > side2) {
			return side2;
		} else {
			return side1;
		}
	}

	private int getXoverlap(Tile t) {
		int side1 = Math.abs(left() - t.right());
		int side2 = Math.abs(right() - t.left());
		if (side1 > side2) {
			return side2;
		} else {
			return side1;
		}
	}

	public boolean collidesLeft() {
		// TODO Auto-generated method stub
		return leftColl;
	}

	public boolean collidesRight() {
		// TODO Auto-generated method stub
		return rightColl;
	}

	public boolean isOnGround() {
		return isOnGround;
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