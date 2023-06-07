package main.java.mesh;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Face {
    private List<Integer> vertexIndices;
    private Color color;

    public Face(List<Integer> vertexIndices) {
        this.vertexIndices = vertexIndices;
        this.color = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
    }

    public Face(int p1, int p2, int p3) {
        this.vertexIndices = new ArrayList<>();
        this.vertexIndices.add(p1);
        this.vertexIndices.add(p2);
        this.vertexIndices.add(p3);
        this.color = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
    }

    public Face(int p1, int p2, int p3,int p4) {
        this.vertexIndices = new ArrayList<>();
        this.vertexIndices.add(p1);
        this.vertexIndices.add(p2);
        this.vertexIndices.add(p3);
        this.vertexIndices.add(p4);
        this.color = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public List<Integer> getVertexIndices() {
        return vertexIndices;
    }
}
