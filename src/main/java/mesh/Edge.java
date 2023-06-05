package main.java.mesh;

public class Edge  {

    int point1;
    int point2;

    public Edge(int point1, int point2) {
        this.point1 = point1;
        this.point2 = point2;
    }


    public int getPoint1() {
        return point1;
    }

    public int getPoint2() {
        return point2;
    }
}
