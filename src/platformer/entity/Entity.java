package platformer.entity;

import platformer.*;
import platformer.level.*;

public abstract class Entity {

	public Map map;

	public Entity(Map map) {
		this.map = map;
	}

	protected double x, y, xspd, yspd;

	public abstract void handleInput(Input input);

	public abstract void update(double delta);

	public abstract void render();

}
