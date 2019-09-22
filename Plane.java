/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raytracing3;

import java.awt.Color;

/**
 *
 * @author hpro2
 */
public class Plane {

    Point3D point;
    Normal normal;
    Color color;

    public Plane(Point3D point, Normal normal, Color color) {
        this.point = point;
        this.normal = normal;
        this.color = color;
    }

    public double intersection(Ray ray) {
        /*
        (p-a) * n = 0
        (o + td - a) * n = 0
        on + tdn - an = 0
        tdn + n(o-a) = 0
        t = n(a-o) / dn
         */
        try {
            double t = point.subtract(ray.origin).dot(normal) / ray.direction.dot(normal);
            if (t > 10E-9) {
                return t;
            }
        } catch (Exception e) {

        }
        return -1;
    }

    public Color getColor(int t) {
        Color tempColor;
        int red, green, blue;
        if (t > 0) {
            if (color.getRed() - t * 5 >= 0) {
                red = (color.getRed() - t * 5);
            } else {
                red = 0;
            }
            if (color.getBlue() - t * 5 > 0) {
                blue = color.getBlue() - t * 5;
            } else {
                blue = 0;
            }
            if (color.getGreen() - t * 5 > 0) {
                green = color.getGreen() - t * 5;
            } else {
                green = 0;
            }
            //System.out.println(red + ", " + green + ", " + blue);
            tempColor = new Color(red, green, blue);
        } else {
            tempColor = new Color(0, 0, 0);
        }
        return tempColor;
    }
}
