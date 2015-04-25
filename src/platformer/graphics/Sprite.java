package platformer.graphics;

import static org.lwjgl.opengl.GL11.*;

public class Sprite {

	Texture texture;

	public Sprite(String path) {
		texture = new Texture(path);
	}

	public void render(int x, int y, double size) {
		texture.bind();

		int hwidth = texture.width / 2;
		int hheight = texture.height / 2;

		glBegin(GL_QUADS);

		glTexCoord2f(0, 1);
		glVertex2f(x - hwidth, y - hheight);

		glTexCoord2f(1, 1);
		glVertex2f(x + hwidth, y - hheight);

		glTexCoord2f(1, 0);
		glVertex2f(x + hwidth, y + hheight);

		glTexCoord2f(0, 0);
		glVertex2f(x - hwidth, y + hheight);

		glEnd();
	}

}
