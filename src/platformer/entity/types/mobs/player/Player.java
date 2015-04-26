package platformer.entity.types.mobs.player;

import org.lwjgl.opengl.*;

import platformer.*;
import platformer.entity.types.*;
import platformer.entity.types.mobs.player.states.jump.*;
import platformer.entity.types.mobs.player.states.walk.*;
import platformer.graphics.*;
import platformer.level.*;
import platformer.tile.*;

public class Player extends Mob {

	private static Sprite dead = new Sprite("assets/dead.png");

	private static Sprite[] shootLeft = { new Sprite("assets/shoot1.png"), new Sprite("assets/shoot2.png") };
	private static Sprite[] shootRight = { new Sprite("assets/shootr1.png"), new Sprite("assets/shootr2.png") };

	private static Sprite[] idle = { new Sprite("assets/idle1.png"), new Sprite("assets/idle2.png") };

	private static Sprite[] jumpLeft = { new Sprite("assets/jump1.png"), new Sprite("assets/jump2.png") };
	private static Sprite[] jumpRight = { new Sprite("assets/jumpr1.png"), new Sprite("assets/jumpr2.png") };

	private static Sprite[] runningRight = { new Sprite("assets/run1.png"), new Sprite("assets/run2.png"), new Sprite("assets/run3.png"),
			new Sprite("assets/run4.png"), new Sprite("assets/run5.png") };
	private static Sprite[] runningLeft = { new Sprite("assets/runl1.png"), new Sprite("assets/runl2.png"), new Sprite("assets/runl3.png"),
			new Sprite("assets/runl4.png"), new Sprite("assets/runl5.png") };

	private static Sprite xsprt = new Sprite("assets/x.png");

	public PlayerState walkState;
	public PlayerState jumpState;
	private boolean toJump;
	private double stoppingAcc = 20;
	private double topSpeed = 50;
	private double walkAcc = 20;

	private double counter = 0;

	public boolean shooting;
	private double shootCounter = 0;

	private boolean printx = false;

	public Player(Map map) {
		super(map);

		walkState = new IdleWalkState();
		jumpState = new IdleJumpState();
		width = 28;
		height = 64;

		Point pos = map.currentArea().playerStart;

		x = pos.x * Tile.size + 1;
		y = pos.y * Tile.size + 1;

		jumpSpd = 100;

		currentSprite = new Sprite("assets/picture.png");
	}

	public void render() {
		currentSprite.render(Display.getWidth() / 2, Display.getHeight() / 2);

		if (printx) {
			xsprt.render(Display.getWidth() / 2, Display.getHeight() / 2 - 200);
		}

	}

	public void handleInput(Input input) {

		PlayerState newState = walkState.handleInput(input, this);
		if (newState != null) {
			walkState = newState;
			walkState.init(this);
		}

		newState = jumpState.handleInput(input, this);
		if (newState != null) {
			jumpState = newState;
			jumpState.init(this);
		}

	}

	public void update(double delta) {
		walkState.update(this, delta);
		jumpState.update(this, delta);
		counter += delta;
		if (toJump) {
			yspd = jumpSpd;
			toJump = false;
		}

		x += xspd * delta;
		handleHoriCollision(delta);

		y += yspd * delta;
		handleVertCollision(delta);

		if (!isOnGround) {
			if (xspd > 0) {
				int index = ((int) counter) % jumpRight.length;
				currentSprite = jumpRight[index];
			} else {
				int index = ((int) counter) % jumpLeft.length;
				currentSprite = jumpLeft[index];
			}
		} else if (xspd > 0) {
			int index = ((int) counter) % runningRight.length;
			currentSprite = runningRight[index];
		} else if (xspd < 0) {
			int index = ((int) counter) % runningLeft.length;
			currentSprite = runningLeft[index];
		} else {
			int index = ((int) counter) % idle.length;
			currentSprite = idle[index];
		}
		if (remove) {
			currentSprite = dead;
		}
		if (shooting) {
			if ((int) shootCounter >= shootLeft.length) {
				shooting = false;
				shootCounter = 0;
			}
			int index = (int) shootCounter;

			currentSprite = xspd > 0 ? shootLeft[index] : shootRight[index];
			shootCounter += delta;
		}
		if (map.pickedUp && map.pickupCounter < 10) {
			printx = true;
			map.pickupCounter += delta;
		} else {
			printx = false;
		}
	}

	public void hitGround() {
		yspd = 0;
	}

	public void stopWalking(double delta) {
		if (xspd > 0) {
			xspd -= stoppingAcc * delta;
		} else if (xspd < 0) {
			xspd += stoppingAcc * delta;
		}
		if (xspd < 1 && xspd > -1) {
			xspd = 0;
		}
	}

	public void walkLeft(double delta) {
		if (xspd > -topSpeed) {
			xspd -= walkAcc * delta;
		}
	}

	public void walkRight(double delta) {
		if (xspd < topSpeed) {
			xspd += walkAcc * delta;
		}
	}

	public void jump() {
		toJump = true;
	}

	public void fall(double delta) {
		yspd -= 50 * delta;
	}

	public int ypos() {
		// TODO Auto-generated method stub
		return (int) y;
	}

	public int xpos() {
		// TODO Auto-generated method stub
		return (int) x;
	}

	public boolean isMoving() {
		return xspd > 0 || xspd < 0;
	}

	public double xspd() {
		// TODO Auto-generated method stub
		return xspd;
	}

	public void shot() {
		shooting = true;
		bullets--;
	}

}
