package platformer.entity.types.mobs.player.states.walk;

import org.lwjgl.input.*;

import platformer.*;
import platformer.entity.types.mobs.player.*;

public class IdleWalkState extends PlayerState {

	@Override
	public void init(Player player) {

	}

	@Override
	public PlayerState handleInput(Input input, Player player) {
		if (input.down(Input.WALK_LEFT)) {
			return new WalkLeftState();
		}
		if (input.down(Input.WALK_RIGHT)) {
			return new WalkRightState();
		}

		return null;
	}

	@Override
	public void update(Player player, double delta) {
		player.stopWalking(delta);

	}

}
