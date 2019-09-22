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
public class Ray {

    public Point3D origin;
    public Vector3D direction;

    public Ray(Point3D origin, Vector3D direction) {
        this.origin = origin;
        this.direction = direction;
    }

}
