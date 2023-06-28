package com.main2048.gameobject;

import com.main2048.Main;
import com.main2048.game.Game;
import com.main2048.graphics.Renderer;
import com.main2048.graphics.Sprite;

import java.util.Random;

public class GameObject {
    public double x, y;
    public boolean remove = false, moving = false, hasMoved = false;
    public int value, speed = 12;
    public int width, height;
    public static final int CELL_SIZE = 60, CELL_PADDING = 3;
    public Sprite sprite;

    Random random = new Random();

    public GameObject(double x, double y) {
        this.x = x;
        this.y = y;
        this.value = (random.nextBoolean() ? 2 : 4);
        createSprite();
        this.width = sprite.width;
        this.height = sprite.height;

    }

    public void createSprite() {
        if (this.value == 2) {
            this.sprite = new Sprite(CELL_SIZE, CELL_SIZE, 0xefe5db);
        }
        if (this.value == 4) {
            this.sprite = new Sprite(CELL_SIZE, CELL_SIZE, 0xece0c8);
        }
        if (this.value == 8) {
            this.sprite = new Sprite(CELL_SIZE, CELL_SIZE, 0xf1b078);
        }
        if (this.value == 16) {
            this.sprite = new Sprite(CELL_SIZE, CELL_SIZE, 0xEB8C52);
        }
        if (this.value == 32) {
            this.sprite = new Sprite(CELL_SIZE, CELL_SIZE, 0xF57C5F);
        }
        if (this.value == 64) {
            this.sprite = new Sprite(CELL_SIZE, CELL_SIZE, 0xEC563D);
        }
        if (this.value == 128) {
            this.sprite = new Sprite(CELL_SIZE, CELL_SIZE, 0xF2D86A);
        }
        if (this.value == 256) {
            this.sprite = new Sprite(CELL_SIZE, CELL_SIZE, 0xECC750);
        }
        if (this.value == 512) {
            this.sprite = new Sprite(CELL_SIZE, CELL_SIZE, 0xE5BF2D);
        }
        if (this.value == 1024) {
            this.sprite = new Sprite(CELL_SIZE, CELL_SIZE, 0xE2B913);
        }
        if (this.value == 2048) {
            this.sprite = new Sprite(CELL_SIZE, CELL_SIZE, 0xEDC22E);
        }
        if (this.value == 4096) {
            this.sprite = new Sprite(CELL_SIZE, CELL_SIZE, 0x5DDB92);
        }
        if (this.value == 8192) {
            this.sprite = new Sprite(CELL_SIZE, CELL_SIZE, 0xEC4D58);
        }
    }

    public Boolean canMove() {
        if (x < 0 || x + width > Main.WIDTH || y < 0 || y + height > Main.HEIGHT) {
            return false;
        }
        for (int i = 0; i < Game.objects.size(); i++) {
            GameObject o = Game.objects.get(i);
            if (this == o) continue;
            if (x + width > o.x && x < o.x + o.width && y + height > o.y && y < o.y + o.height && value != o.value) {
                return false;
            }
        }
        return true;
    }

    public void update() {
        if (Game.moving) {
            if (!hasMoved) {
                hasMoved = true;
            }
            if (canMove()) {
                moving = true;
            }
            if (moving) {
                if (Game.dir == 0) x -= speed;
                if (Game.dir == 1) x += speed;
                if (Game.dir == 2) y -= speed;
                if (Game.dir == 3) y += speed;
            }
            if (!canMove()) {
                moving = false;
                x = Math.round(x / CELL_SIZE) * CELL_SIZE;
                y = Math.round(y / CELL_SIZE) * CELL_SIZE;
            }
        }
    }


    public void render() {
        Renderer.renderSprite(sprite, (int) x, (int) y);
    }

}
