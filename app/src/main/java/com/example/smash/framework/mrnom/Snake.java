package com.example.smash.framework.mrnom;

import com.example.smash.framework.core.Constant;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;

    public static final int HEAD_INDEX = 0;
    public static final int START_X = Constant.MAP_RIGHT / 2;
    public static final int HEAD_START_Y = Constant.MAP_BOTTOM / 2;
    public static final int NUM_BODY_PART = 2;


    public List<SnakePart> partList =
            new ArrayList<SnakePart>();
    public int direction;

    public Snake() {
        this.direction = Snake.UP;
        this.partList.add(new SnakePart(
                Snake.START_X,
                Snake.HEAD_START_Y
        ));
        for (int i = 1; i <= NUM_BODY_PART; i++) {
            this.partList.add(new SnakePart(
                    Snake.START_X,
                    Snake.HEAD_START_Y + i
            ));
        }
    }

    public void turnLeft() {
        this.direction += 1;
        if (this.direction > Snake.RIGHT) {
            this.direction =  UP;
        }
    }

    public void turnRight() {
        this.direction -= 1;
        if (this.direction < Snake.UP) {
            this.direction =  RIGHT;
        }
    }

    public void eat() {
        SnakePart end = partList.get(this.partList.size() - 1);
        this.partList.add(new SnakePart(end.x,end.y));
    }

    public  void advance() {
        SnakePart head = this.partList.get(Snake.HEAD_INDEX);

        int len = this.partList.size() - 1;
        for (int i = len;i > Snake.HEAD_INDEX;i--){
            SnakePart before = this.partList.get(i-1);
            SnakePart part = this.partList.get(i);

            part.x = before.x;
            part.y = before.y;
        }

        if (this.direction == UP) {
            head.y -= 1;
        } else if (this.direction == LEFT)  {
            head.x -= 1;
        } else if (this.direction == DOWN)  {
            head.y += 1;
        } else if (this.direction == RIGHT)  {
            head.x += 1;
        }

        if (head.x < Constant.MAP_LEFT) {
            head.x = Constant.MAP_RIGHT;
        } else if (head.x > Constant.MAP_RIGHT) {
            head.x = Constant.MAP_LEFT;
        }

        if (head.y < Constant.MAP_TOP) {
            head.y = Constant.MAP_BOTTOM;
        } else if (head.y > Constant.MAP_BOTTOM) {
            head.y = Constant.MAP_TOP;
        }
    }

    public boolean isBitten() {
        int len = this.partList.size();
        SnakePart head = this.partList.get(Snake.HEAD_INDEX);
        for(int i = Snake.HEAD_INDEX + 1;i<len;i++) {
            SnakePart part = this.partList.get(i);
            if (part.x == head.x && part.y == head.y) {
                return true;
            }
        }
        return false;
    }

}
