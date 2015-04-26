package platformer.entity;

import platformer.*;
import platformer.level.*;
import platformer.tile.*;

public abstract class Entity {

	public Map map;

	public Entity(Map map) {
		this.map = map;
	}

	public boolean remove = false;

	protected double x, y, xspd, yspd;

	public abstract void handleInput(Input input);

	public abstract void update(double delta);

	public abstract void render();

	public boolean collided(Tile t) {
		return left() <= t.right() && right() >= t.left() && bot() <= t.top() && top() >= t.bot();
	}

	public int bot() {
		return (int) y;
	}

	public int top() {
		return (int) y;
	}

	public int left() {
		return (int) x;
	}

	public int right() {
		return (int) x;
	}

}
