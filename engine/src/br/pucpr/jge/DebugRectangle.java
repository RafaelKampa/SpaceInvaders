package br.pucpr.jge;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class DebugRectangle implements AbstractGameObject{
	public Color color;
	public GameObject obj;

	public DebugRectangle(GameObject obj, Color color) {
		this.obj = obj;
		this.color = color;
	}

	public void draw(Graphics2D g2d) {

		g2d.setColor(color);
		g2d.draw(obj.getHitBox());
		obj.draw(g2d);
	}

	public boolean isInGame() {
		return obj.isInGame();
	}

	@Override
	public double getX() {
		return obj.getX();
	}

	@Override
	public double getY() {
		return obj.getY();
	}

	@Override
	public void load() {
		obj.load();
	}

	@Override
	public void update(double s, InputManager keys) {
		obj.update(s, keys);		
	}

	@Override
	public Rectangle getHitBox() {
		return obj.getHitBox();
	}

	@Override
	public void onCollision(AbstractGameObject obj) {
		this.obj.onCollision(obj);
	}
}
