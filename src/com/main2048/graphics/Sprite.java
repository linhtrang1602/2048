package com.main2048.graphics;

import com.main2048.gameobject.GameObject;

public class Sprite {

	public int width, height;
	public int[] pixels;

	public Sprite(int width, int height, int color) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];

		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				pixels[x + y * width] = color;

				if(x % GameObject.CELL_SIZE < GameObject.CELL_PADDING || x % GameObject.CELL_SIZE > GameObject.CELL_SIZE - GameObject.CELL_PADDING
						|| y % GameObject.CELL_SIZE < GameObject.CELL_PADDING || y % GameObject.CELL_SIZE > GameObject.CELL_SIZE - GameObject.CELL_PADDING) {
					pixels[x + y * width] = 0xffff00ff;
				}
			}
		}
	}

}
