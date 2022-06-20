package br.pucpr.jge;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public interface AbstractGameObject {

	double getX();

	double getY();

	void load();

	void update(double s, InputManager keys);

	void draw(Graphics2D g2d);

	boolean isInGame();

	Rectangle getHitBox();

	void onCollision(AbstractGameObject obj);

}