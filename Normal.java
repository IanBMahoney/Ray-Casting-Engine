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
public class Normal {

    public double x, y, z;

    public Normal() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Normal(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Normal Normalize() {
        double magnitude = Math.sqrt(x * x + y * y + z * z);
        x /= magnitude;
        y /= magnitude;
        z /= magnitude;
        return this;
    }

}
