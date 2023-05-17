package main.java.math;



/**
 * @author Fredrik
 */
public class Ray  {

    private Point3d p1;
    private Point3d p2;

    public Ray(Point3d p1, Point3d p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    /**
     * Calculates the vector of the ray. p1 is the front of the vector
     *
     * @return the vector of the ray
     */
    public Vector getVector() {
        return new Vector(p2.getX() - p1.getX(), p2.getY() - p1.getY(), p2.getZ() - p1.getZ());
    }

    /**
     * finds the intersection point of a ray going through a plane
     * @param plane the plane to find the intersection point on
     * @return the intersection point
     */
    public Point3d intersectWithPlane(Rectangle plane) {
        double a = plane.getA();
        double b = plane.getB();
        double c = plane.getC();
        double d = plane.getD();
        double denominator = a * this.getVector().getX() + b * this.getVector().getY() + c * this.getVector().getZ();
        if(denominator == 0){
            return null;
        }
        double t = -(a * this.getP1().getX() + b * this.getP1().getY() + c * this.getP1().getX() + d) / denominator;

        if(t<0){
            return null;
        }
        return new Point3d(this.getP1().getX() + this.getVector().getX() * t, this.getP1().getY() + this.getVector().getY() * t, this.getP1().getZ() + this.getVector().getZ() * t);
    }

    public double length() {
        return this.getVector().scalar();
    }

    public Point3d getP1() {
        return p1;
    }

    public void setP1(Point3d p1) {
        this.p1 = p1;
    }

    public Point3d getP2() {
        return p2;
    }

    public void setP2(Point3d p2) {
        this.p2 = p2;
    }


}
