package platformer.entity.types.mobs.player.states.walk;

import platformer.*;
import platformer.entity.types.mobs.player.*;

public class WalkRightState extends PlayerState {

	private double acceleration = 1;

	@Override
	public void init(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public PlayerState handleInput(Input input, Player player) {
		if (!input.down(Input.WALK_RIGHT) || player.nextToWall()) {
			return new IdleWalkState();
		}
		return null;
	}

	@Override
	public void update(Player player, double delta) {
		player.walkRight(delta);

	}

}
