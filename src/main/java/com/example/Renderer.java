package com.example;

public class Renderer {
    private final Palette palette;

    public Renderer(Palette palette) {
        this.palette = palette;
    }

    public void drawLine() {
        System.out.println("Drawing Line");
    }

    public Colour getForegroundColour() {
        return palette.getPrimaryColour();
    }
}
