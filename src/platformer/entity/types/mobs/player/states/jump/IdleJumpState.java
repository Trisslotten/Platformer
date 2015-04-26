package platformer.entity.types.mobs.player.states.jump;

import platformer.*;
import platformer.entity.types.mobs.player.*;

public class IdleJumpState extends PlayerState {

	@Override
	public void init(Player player) {
		player.hitGround();

	}

	@Override
	public PlayerState handleInput(Input input, Player player) {
		if (input.down(Input.JUMP) && player.isOnGround()) {
			return new JumpState();
		}
		if (!player.isOnGround()) {
			return new FallingState();
		}
		return null;
	}

	@Override
	public void update(Player player, double delta) {

	}

}
