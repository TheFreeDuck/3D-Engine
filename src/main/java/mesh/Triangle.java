package main.java.mesh;

public class Triangle  {

    int Point3d1;
    int Point3d2;
    int Point3d3;

    public Triangle(int Point3d1, int Point3d2, int Point3d3) {
        this.Point3d1 = Point3d1;
        this.Point3d2 = Point3d2;
        this.Point3d3 = Point3d3;
    }

    public int getPoint3d1() {
        return Point3d1;
    }

    public int getPoint3d2() {
        return Point3d2;
    }

    public int getPoint3d3() {
        return Point3d3;
    }
}
