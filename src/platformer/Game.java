package platformer;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.*;
import org.lwjgl.opengl.*;

import platformer.entity.types.mobs.player.*;
import platformer.graphics.*;

public class Game implements Runnable {

	Sprite sprite;

	Player player;

	private void init() {

		sprite = new Sprite("assets/picture.png");

		player = new Player(null);
	}

	private void handleInput(Input input) {
		player.handleInput(input);
	}

	private void update(double delta) {
		player.update(delta);
	}

	private void render() {
		player.render();
	}

	private Thread thread;

	private int width = 1280;
	private int height = 720;
	private int frequency = 144;

	private boolean running = false;
	private boolean fullscreen = false;

	public double getTime() {
		return System.nanoTime() / 100000000.0;
	}

	@Override
	public void run() {

		glInit();

		Input input = new Input();
		init();

		double now = getTime();

		while (running && !Display.isCloseRequested()) {
			double delta = getTime() - now;
			now = getTime();
			handleInput(input);
			input.update();
			update(delta);
			render();
			Display.sync(frequency);
			Display.update();
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		}
		Display.destroy();
	}

	// Startar spelet
	public void start() {
		running = true;
		thread = new Thread(this, "Main thread");
		thread.start();
	}

	// Initierar fönstret och grafikbiblioteket
	public void glInit() {
		DisplayMode[] displayModes;

		try {
			displayModes = Display.getAvailableDisplayModes();
			/*
			 * ArrayList<DisplayMode> validModes = new ArrayList<DisplayMode>();
			 * 
			 * for (int i = 0; i < displayModes.length; i++) { int width =
			 * displayModes[i].getWidth(); int height =
			 * displayModes[i].getHeight(); int freq =
			 * displayModes[i].getFrequency(); if (freq == 60 && height > 800) {
			 * validModes.add(displayModes[i]); } //System.out.println(i + " = "
			 * + width + "x" + height + " " + freq + "Hz"); }
			 * 
			 * for(DisplayMode d : validModes) { int width = d.getWidth(); int
			 * height = d.getHeight(); int freq = d.getFrequency();
			 * System.out.println(width + "x" + height + " " + freq + "Hz"); }
			 */

			// FIX PLS
			DisplayMode displayMode = new DisplayMode(width, height);

			if (fullscreen) {
				Display.setDisplayModeAndFullscreen(displayMode);
			} else {
				Display.setDisplayMode(displayMode);
			}

			Display.create();
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, width, 0, height, -1, 1);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			glLoadIdentity();
			glEnable(GL_TEXTURE_2D);

		} catch (LWJGLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String... args) {
		Game game = new Game();
		game.start();
	}

}
