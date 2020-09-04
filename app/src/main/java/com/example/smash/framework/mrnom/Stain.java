package com.example.smash.framework.mrnom;

public class Stain extends GameObject {


    public int type;

    public Stain(
            int x,
            int y,
            int type
    ) {
        super(x,y);
        this.type = type;
    }
}
