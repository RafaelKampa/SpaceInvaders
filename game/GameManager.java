package br.pucpr.jge;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager {
	private static final GameManager instance = new GameManager();
	private List<AbstractGameObject> objects = new ArrayList<>();
	private List<AbstractGameObject> newObjects = new ArrayList<>();
	Map<String, BufferedImage> imagesLoaded = new HashMap<>();
	
	private GameManager() {
	}

	public static GameManager getInstance() {
		return instance;
	}

	public GameManager clear() {
		objects.clear();
		return this;
	}

	public GameManager add(AbstractGameObject obj) {
		obj.load();
		newObjects.add(obj);
		return this;
	}

	void update(double s, InputManager keys) {
		var it = objects.iterator();
		while (it.hasNext()) {
			AbstractGameObject go = it.next();
			go.update(s, keys);
			if (!go.isInGame()) {
				it.remove();
			}
		}
		objects.addAll(newObjects);
		newObjects.clear();

		for (int i = 0; i < objects.size() - 1; i++) {
			var obj1 = objects.get(i).getHitBox();
			for (int j = i + 1; j < objects.size(); j++) {
				var obj2 = objects.get(j).getHitBox();

				if (obj1.intersects(obj2)) {
					objects.get(i).onCollision(objects.get(j));
					objects.get(j).onCollision(objects.get(i));
				}
			}
		}
	}

	void draw(Graphics2D g2d) {
		for (AbstractGameObject obj : objects) {
			if(obj.isInGame()) {
				obj.draw(g2d);
			}
		}
	}

}
