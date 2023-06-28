package com.main2048.graphics;

import com.main2048.Main;
import com.main2048.gameobject.GameObject;

public class Renderer {

	public static int width = Main.WIDTH, height = Main.HEIGHT;
	public static int[] pixels = new int[width * height];

	public static void renderBackground() {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				pixels[x + y * width] = 0xfff4f4f4;

				if(x % GameObject.CELL_SIZE < GameObject.CELL_PADDING || x % GameObject.CELL_SIZE > GameObject.CELL_SIZE - GameObject.CELL_PADDING
						|| y % GameObject.CELL_SIZE < GameObject.CELL_PADDING || y % GameObject.CELL_SIZE > GameObject.CELL_SIZE - GameObject.CELL_PADDING) {
					pixels[x + y * width] = 0xffcccccc;
				}
			}
		}
	}

	public static void renderSprite(Sprite sprite, int xp, int yp) {
		if(xp < -sprite.width || xp > width || yp < -sprite.height || yp > height) return;

		for(int y = 0; y < sprite.height; y++) {
			int yy = y + yp;
			if(yy < 0 || yy > height) continue;
			for(int x = 0; x < sprite.width; x++) {
				int xx = x +xp;
				if(xx < 0 || xx > width) continue;
				int col = sprite.pixels[x + y * sprite.width];
				if(col == 0xffff00ff) continue;
				else pixels[xx + yy * width] = col;
			}
		}
	}

}
