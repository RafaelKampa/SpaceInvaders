package br.com.battle;

import br.pucpr.jge.GameObject;
import br.pucpr.jge.InputManager;
import br.pucpr.jge.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Background extends GameObject {


	public int frames = 0, maxFrames = 10, index = 0, maxIndex = 200;
	private BufferedImage[] background;
	public SpriteSheet spriteSheet;

	public Background(double x, double y) {
		super("/image/space.png", x, y);
		spriteSheet = new SpriteSheet("/image/space.png");
		background = new BufferedImage[200];
		y = 0;
		for (int i = 0; i < background.length; i++){
			background[i] = spriteSheet.getSprite(0,(int) y, 800, 600);
			y = y + 5;
		}
	}

	@Override
	public void update(double s, InputManager keys) {
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index >= maxIndex) {
				index = 0;
			}
		}
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(background[index], (int) x, (int) y, null);
	}

	@Override
	public boolean isInGame() {
		return true;
	}
}
