package com.main2048.game;

import com.main2048.gameobject.GameObject;
import com.main2048.graphics.Renderer;
import com.main2048.graphics.Sprite;
import com.main2048.input.Keyboard;
import com.main2048.Main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    public static List<GameObject> objects;

    public static boolean moving = false, hasMoved = true, someThingIsMoving = false;
    public static int dir = 0;

    private Random rand = new Random();
    public Game() {
        init();
    }

    public void init() {
        objects = new ArrayList<GameObject>();
        moving = false;
        hasMoved = true;
        someThingIsMoving = false;
        spawn();
    }

    public void update() {
        if (Keyboard.keyUp(KeyEvent.VK_R)) {
            init();
        }

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).update();
        }

        checkForValueIncrease();

        movingLogic();
    }

    private void checkForValueIncrease() {
        for (int i = 0; i < objects.size(); i++) {
            for (int j = 0; j < objects.size(); j++) {
                if (i == j) continue;
                if (objects.get(i).x == objects.get(j).x && objects.get(i).y == objects.get(j).y
                        && !objects.get(i).remove && !objects.get(j).remove) {
                    objects.get(j).remove = true;
                    objects.get(i).value *= 2;
                    objects.get(i).createSprite();
                }
            }
        }

        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).remove) objects.remove(i);
        }
    }

    private void movingLogic() {
        someThingIsMoving = false;
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).moving) {
                someThingIsMoving = true;
            }
        }
        if (!someThingIsMoving) {
            moving = false;
            for (int i = 0; i < objects.size(); i++) {
                objects.get(i).hasMoved = false;
            }
        }
        if (!moving && hasMoved) {
            spawn();
            hasMoved = false;
        }
        if (!moving && !hasMoved) {
            if (Keyboard.keyDown(KeyEvent.VK_LEFT) || Keyboard.keyDown(KeyEvent.VK_A)) {
                hasMoved = true;
                moving = true;
                dir = 0;
            } else if (Keyboard.keyDown(KeyEvent.VK_RIGHT) || Keyboard.keyDown(KeyEvent.VK_D)) {
                hasMoved = true;
                moving = true;
                dir = 1;
            } else if (Keyboard.keyDown(KeyEvent.VK_UP) || Keyboard.keyDown(KeyEvent.VK_W)) {
                hasMoved = true;
                moving = true;
                dir = 2;
            } else if (Keyboard.keyDown(KeyEvent.VK_DOWN) || Keyboard.keyDown(KeyEvent.VK_S)) {
                hasMoved = true;
                moving = true;
                dir = 3;
            }
        }
    }

    private void spawn() {
        if (objects.size() == 16) return;

        boolean available = false;
        int x = 0, y = 0;
        while(!available) {
            x = rand.nextInt(4);
            y = rand.nextInt(4);
            boolean isAvailavle = true;
            for (int i = 0; i < objects.size(); i++) {
                if (objects.get(i).x / GameObject.CELL_SIZE == x && objects.get(i).y / GameObject.CELL_SIZE == y) {
                    isAvailavle = false;
                }
            }
            if (isAvailavle) available = true;
        }
        objects.add(new GameObject(x * GameObject.CELL_SIZE, y * GameObject.CELL_SIZE));
    }

    public void render() {
        Renderer.renderBackground();
//        Renderer.renderSprite(new Sprite(100, 100, 0xffffff00), 100, 100);

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).render();
        }

        for (int i = 0; i < Main.pixels.length; i++) {
            Main.pixels[i] = Renderer.pixels[i];
        }
    }

    public void renderText(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(new Font("Verdana", 0, 60));
        g.setColor(Color.BLACK);

        for (int i = 0; i < objects.size(); i++) {
            GameObject o = objects.get(i);
            String s = o.value + "";
            int sw = (int) (g.getFontMetrics().stringWidth(s) / 2 / Main.scale);
            g.drawString(s, (int) (o.x + o.width / 2 - sw) * Main.scale, (int) (o.y + o.height / 2 + 18) * Main.scale);
        }
    }
}
