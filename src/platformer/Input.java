package platformer;

import org.lwjgl.input.*;

public class Input {

	private boolean[] keys = new boolean[Keyboard.KEYBOARD_SIZE];

	public void update() {
		for (int i = 0; i < keys.length; i++) {
			keys[i] = Keyboard.isKeyDown(i);
		}
	}

	public boolean down(int i) {
		return Keyboard.isKeyDown(i);
	}

	public boolean pressed(int i) {
		return (Keyboard.isKeyDown(i) && !keys[i]);
	}

	public boolean released(int i) {
		return (!Keyboard.isKeyDown(i) && keys[i]);
	}

	public static int WALK_LEFT = Keyboard.KEY_LEFT;
	public static int WALK_RIGHT = Keyboard.KEY_RIGHT;
	public static int JUMP = Keyboard.KEY_UP;
	public static final int SHOOT = Keyboard.KEY_X;

}
