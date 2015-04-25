package platformer.entity.types;

import platformer.*;
import platformer.entity.*;
import platformer.graphics.*;
import platformer.level.*;

public abstract class Mob extends Entity {

	public Mob(Map map) {
		super(map);
		// TODO Auto-generated constructor stub
	}

	public Sprite currentSprite;

	protected int width, height;

	protected int health, jumpSpd;

	public boolean isOnGround() {
		return y <= 100;
	}
}
