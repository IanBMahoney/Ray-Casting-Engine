/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raytracing3;

/**
 *
 * @author ianmahoney
 */
public class Point3D {

    public double x, y, z;

    public Point3D() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D add(Point3D point) {
        return new Point3D(x + point.x, y + point.y, z + point.z);
    }

    public Point3D subtract(Point3D point) {
        return new Point3D(x - point.x, y - point.y, z - point.z);
    }

    public double dot(Point3D point) {
        return x * point.x + y * point.y + z * point.z;
    }

    public double dot(Normal normal) {
        return x * normal.x + y * normal.y + z * normal.z;
    }

}
