package platformer;

import static org.lwjgl.opengl.GL11.*;

import java.util.*;

import org.lwjgl.*;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;

import platformer.entity.*;
import platformer.entity.types.*;
import platformer.entity.types.mobs.player.*;
import platformer.graphics.*;
import platformer.level.Map;

public class Game implements Runnable {

	private Sprite[] num;

	private Sprite count;

	boolean dead = false;

	ArrayList<Bullet> bullets = new ArrayList<Bullet>();

	Sprite r;
	Player player;
	Map map;

	private void init() {
		r = new Sprite("assets/r.png");
		map = new Map();
		player = new Player(map);
		count = new Sprite("assets/count.png");
		num = new Sprite[10];
		for (int i = 0; i < num.length; i++) {
			num[i] = new Sprite("assets/" + i + ".png");
		}
	}

	private void handleInput(Input input) {
		player.handleInput(input);
		if (map.changedLevel) {
			System.out.println("asdasd");
			player = new Player(map);
			map.acknowledgeLevelChange();
		}
		if (input.pressed(Keyboard.KEY_R)) {
			player = new Player(map);
			map.currentArea().resetArea();
			dead = false;
		}

		if (input.pressed(Input.SHOOT) && player.isMoving() && !dead && player.bullets > 0) {
			bullets.add(new Bullet(map, player));
			player.shot();
		}

		for (Bullet b : bullets) {
			b.handleInput(input);
		}

		ArrayList<Bullet> toRemove = new ArrayList<Bullet>();
		for (Bullet b : bullets) {
			if (b.remove) {
				toRemove.add(b);
			}
		}
		for (Bullet b : toRemove) {
			bullets.remove(b);
		}
	}

	private void update(double delta) {

		for (Bullet b : bullets) {
			b.update(delta);
		}

		if (!dead) {
			player.update(delta);
			if (player.remove) {
				dead = true;
			}
		}

	}

	private void render() {

		int xoffset = player.xpos() - width / 2 - 12;
		int yoffset = player.ypos() - height / 2;

		map.getArea().render(xoffset, yoffset);

		for (Bullet b : bullets) {
			b.render(xoffset, yoffset);
		}
		player.render();

		num[player.bullets % num.length].render(100, 620);

		count.render(150, 605);

		if (dead) {
			r.render(width / 2, height / 2 + 150);
		}

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

		GL11.glClearColor(0, 0, 0, 0.75f);

		while (running && !Display.isCloseRequested() && !input.down(Keyboard.KEY_ESCAPE)) {

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
		// game.godmode(true);
	}

}
