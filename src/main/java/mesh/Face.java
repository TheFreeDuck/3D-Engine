package main.java.mesh;

import java.util.ArrayList;
import java.util.List;

public class Face {
    private ArrayList<Integer> vertexIndices;

    public Face(ArrayList<Integer> vertexIndices) {
        this.vertexIndices = vertexIndices;
    }

    public Face(int p1, int p2, int p3) {
        this.vertexIndices = new ArrayList<>();
        this.vertexIndices.add(p1);
        this.vertexIndices.add(p2);
        this.vertexIndices.add(p3);
    }

    public List<Integer> getVertexIndices() {
        return vertexIndices;
    }
}
