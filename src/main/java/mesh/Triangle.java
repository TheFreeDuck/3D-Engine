package main.java.mesh;

public class Triangle  {

    int vertex1;
    int vertex2;
    int vertex3;

    public Triangle(int vertex1, int vertex2, int vertex3) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
    }

    public int getVertex1() {
        return vertex1;
    }

    public int getVertex2() {
        return vertex2;
    }

    public int getVertex3() {
        return vertex3;
    }
}
