package platformer.entity.types.mobs.player;

import platformer.*;

public abstract class PlayerState {

	public abstract void init(Player player);

	public abstract PlayerState handleInput(Input input, Player player);

	public abstract void update(Player player, double delta);

}
