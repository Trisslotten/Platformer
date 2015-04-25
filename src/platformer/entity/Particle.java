package platformer.entity;

import platformer.graphics.*;
import platformer.level.*;

public abstract class Particle extends Entity {
	
	protected Sprite currentSprite; 

	public Particle(Map map) {
		super(map);
	}

}
