package platformer.entity.types.mobs.player.states.jump;

import platformer.*;
import platformer.entity.types.mobs.player.*;

public class FallingState extends PlayerState {

	@Override
	public void init(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public PlayerState handleInput(Input input, Player player) {
		if (player.isOnGround()) {
			return new IdleJumpState();
		}
		return null;
	}

	@Override
	public void update(Player player, double delta) {
		player.fall(delta);

	}

}
