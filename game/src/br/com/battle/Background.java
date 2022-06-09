package br.com.battle;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import br.pucpr.jge.GameObject;
import br.pucpr.jge.InputManager;
import br.pucpr.jge.SpriteSheet;

public class Background extends GameObject {
	
	public int frames = 0, maxFrames = 9, index = 0, maxIndex = 4;
	private BufferedImage[] background;
	public SpriteSheet spriteSheet;
	
	public Background(double x, double y) {
		super("/image/space.png", x, y);
		spriteSheet = new SpriteSheet("/image/space.png");
		background = new BufferedImage[4];
		background[0] = spriteSheet.getSprite(0, 100, 800, 100);
		background[1] = spriteSheet.getSprite(0, 100, 800, 100);
		background[2] = spriteSheet.getSprite(0, 100, 800, 100);
		background[3] = spriteSheet.getSprite(0, 100, 800, 300);
//		background[4] = spriteSheet.getSprite(0, 100, 800, 600);
//		background[5] = spriteSheet.getSprite(0, 100, 800, 600);
//		background[6] = spriteSheet.getSprite(0, 100, 800, 600);
//		background[7] = spriteSheet.getSprite(0, 100, 800, 600);
	}
	
	@Override
	public void update(double s, InputManager keys) {
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index >= maxIndex) {
				index = maxIndex;
			}
		}
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(background[index], (int) x, (int) y, null);
	}
	
	@Override
	public boolean isInGame() {
		return index < maxIndex;
	}
}
