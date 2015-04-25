package platformer.entity.types.mobs.player;

import platformer.*;
import platformer.entity.types.*;
import platformer.entity.types.mobs.player.states.jump.*;
import platformer.entity.types.mobs.player.states.walk.*;
import platformer.graphics.*;
import platformer.level.*;

public class Player extends Mob {

	public PlayerState walkState;
	public PlayerState jumpState;
	private boolean toJump;
	private double stoppingAcc = 10;
	private double topSpeed = 50;
	private double walkAcc = 10;

	public Player(Map map) {
		super(map);
		health = 99;

		walkState = new IdleWalkState();
		jumpState = new IdleJumpState();

		x = 300;
		y = 300;

		jumpSpd = 100;

		currentSprite = new Sprite("assets/picture.png");
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

		if (toJump) {
			yspd = jumpSpd;
			toJump = false;
		}
		x += xspd * delta;
		y += yspd * delta;
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

	public void render() {
		currentSprite.render(x + height / 2, y);
	}

}
