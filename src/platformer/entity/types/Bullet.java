package platformer.entity.types;

import platformer.*;
import platformer.entity.*;
import platformer.entity.types.mobs.player.*;
import platformer.graphics.*;
import platformer.level.*;
import platformer.tile.*;

public class Bullet extends Particle {

	private static Sprite bulletLeft = new Sprite("assets/firedBulletL.png");
	private static Sprite bulletRight = new Sprite("assets/firedBullet.png");

	private Sprite sprite;

	public Bullet(Map map, Player player) {
		super(map);
		y = player.ypos() + 10;
		boolean dir = player.xspd() > 0;
		x = player.xpos() + (dir ? 23 : -23);
		double speed = 75;
		xspd = dir ? speed : -speed;
		sprite = dir ? bulletRight : bulletLeft;
	}

	@Override
	public void handleInput(Input input) {
		int i = 0;
		int index = -1;
		for (Tile t : map.currentArea().tiles) {
			if (collided(t) && !t.isBullet()) {
				this.remove = true;
				if (t.isBroken()) {
					System.out.println("remove " + i);
					index = i;
				}
			}
			i++;
		}
		if (index >= 0) {
			map.currentArea().tiles.remove(index);
		}

	}

	public int bot() {
		return (int) y + 32;
	}

	public int top() {
		return (int) y + 32;
	}

	public int left() {
		return (int) x + 32;
	}

	public int right() {
		return (int) x + 32;
	}

	@Override
	public void update(double delta) {
		x += xspd * delta;
	}

	public void render(int xoffset, int yoffset) {
		sprite.render(x - xoffset, y - yoffset);
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}
}
