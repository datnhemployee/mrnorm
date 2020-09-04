package com.example.smash.framework.mrnom;

import android.util.Log;

import com.example.smash.framework.core.Constant;

import java.util.Random;

public class World {
    static final int WORLD_WIDTH = Constant.MAP_RIGHT + 1;
    static final int WORLD_HEIGHT = Constant.MAP_BOTTOM + 1;
    static final int SCORE_INSCREMENT = 10;
    static final float TICK_INITIAL = 0.5f;

    // Usecase: speed up
    static final int SPEED_UP_THRESHOLD = 100;
    static final float TICK_DECREMENT = 0.05f;

    public Snake snake;
    public Stain stain;
    public boolean gameOver = false;
    public int score = 0;

    boolean fields[][] = new boolean[WORLD_WIDTH][WORLD_HEIGHT];
    Random random = new Random();
    float tickTime = 0;
    float tick = TICK_INITIAL;

    public World() {
        this.snake = new Snake();
        this.placeStain();
    }

    private void placeStain() {
        for (int x=0;x < WORLD_WIDTH;x++) {
            for (int y = 0; y < WORLD_HEIGHT; y++) {
                this.fields[x][y] = false;
            }
        }

        int len = this.snake.partList.size();
        for(int i = 0; i < len; i++) {
            SnakePart part = this.snake.partList.get(i);
            this.fields[part.x][part.y] = true;
        }

        int stainX = this.random.nextInt(WORLD_WIDTH);
        int stainY = this.random.nextInt(WORLD_HEIGHT);
        while (true) {
            if (fields[stainX][stainY] == false) {
                break;
            }
            stainX += 1;
            if (stainX >= WORLD_WIDTH) {
                stainX = 0;
                stainY += 1;
                if (stainY >= WORLD_HEIGHT) {
                    stainY = 0;
                }
            }
        }
        this.stain = new Stain(
                stainX,
                stainY,
                this.random.nextInt(Constant.NUM_STAIN_TYPE)
        );
    }

    public void update(float deltaTime) {
        if (this.gameOver) {
            return;
        }

        this.tickTime += deltaTime;

        while (this.tickTime > this.tick) {
            this.tickTime -= this.tick;
            this.snake.advance();
            if (this.snake.isBitten()) {
                this.gameOver = true;
                return;
            }

            SnakePart head = this.snake.partList.get(Snake.HEAD_INDEX);
            if (head.x == stain.x && head.y == stain.y) {
                this.score += SCORE_INSCREMENT * this.stain.type;
                this.snake.eat();

                if (this.snake.partList.size() == WORLD_WIDTH * WORLD_HEIGHT) {
                    this.gameOver = true;
                    return;
                } else {
                    this.placeStain();
                }

                // The last thing we do is check whether Mr. Nom
                //  has just eaten ten more stains.
                //  If that’s the case,
                //  and our threshold is above zero,
                //  we decrease it by 0.05 seconds.
                //  The tick will be shorter and
                //  thus will make Mr. Nom move faster

                if (this.score % SPEED_UP_THRESHOLD == 0 && this.tick - TICK_DECREMENT > 0) {
                    this.tick -= TICK_DECREMENT;
                }
            }
        }
    }
}
