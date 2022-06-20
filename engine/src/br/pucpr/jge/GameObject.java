package br.pucpr.jge;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class GameObject implements AbstractGameObject {
	protected double x;
	protected double y;
	protected BufferedImage sprite;
	private String spriteName;

	public GameObject(String spriteName, double x, double y) {
		this.spriteName = spriteName;
		this.x = x;
		this.y = y;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void load() {
		sprite = new Loader().loadImage(spriteName);
	}

	@Override
	public void update(double s, InputManager keys) {
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(sprite, (int) x, (int) y, null);
	}

	@Override
	public boolean isInGame() {
		return true;
	}

	@Override
	public Rectangle getHitBox() {
		Rectangle rect = new Rectangle((int) x, (int) y, sprite.getWidth(), sprite.getHeight());
		return rect;
	}

	@Override
	public void onCollision(AbstractGameObject obj) {

	}
}
