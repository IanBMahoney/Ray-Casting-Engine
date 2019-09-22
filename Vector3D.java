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
public class Vector3D {

    public double x, y, z;

    public Vector3D() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D add(Vector3D vector) {
        return new Vector3D(x + vector.x, y + vector.y, z + vector.z);
    }

    public Vector3D subtract(Vector3D vector) {
        return new Vector3D(x - vector.x, y - vector.y, z - vector.z);
    }

    public double dot(Vector3D vector) {
        return x * vector.x + y * vector.y + z * vector.z;
    }

    public double dot(Point3D point) {
        return x * point.x + y * point.y + z * point.z;
    }

    public double dot(Normal normal) {
        return x * normal.x + y * normal.y + z * normal.z;
    }

    public Normal Normalize() {
        double magnitude = Math.sqrt(x * x + y * y + z * z);
        x /= magnitude;
        y /= magnitude;
        z /= magnitude;
        return new Normal(x, y, z);
    }

}
