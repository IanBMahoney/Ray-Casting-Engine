/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raytracing3;

import java.awt.Color;

/**
 *
 * @author ianmahoney
 */
public class Cube {

    public double height, width, depth;
    public Point3D point;
    public Color color;
    public Point3D[] vertex = new Point3D[8];
    public Plane[] planes = new Plane[6];

    public Cube(Point3D point, int height, int width, int depth, Color color) {

        this.point = point;
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.color = color;

        //front bottom left
        vertex[0] = new Point3D(point.x, point.y, point.z);
        //front bottom right
        vertex[1] = new Point3D(point.x + width, point.y, point.z);
        //front top left
        vertex[2] = new Point3D(point.x, point.y + height, point.z);
        //front top right
        vertex[3] = new Point3D(point.x + width, point.y + height, point.z);
        //back bottom left
        vertex[4] = new Point3D(point.x, point.y, point.z + depth);
        //back bottom right
        vertex[5] = new Point3D(point.x + width, point.y, point.z + depth);
        //back top left
        vertex[6] = new Point3D(point.x, point.y + height, point.z + depth);
        //back top right
        vertex[7] = new Point3D(point.x + width, point.y + height, point.z + depth);

        //front
        planes[0] = new Plane(vertex[0], new Normal(0, 0, 1).Normalize(), new Color(15, 27, 64));
        //right
        planes[1] = new Plane(vertex[1], new Normal(1, 0, 0).Normalize(), new Color(15, 27, 64));
        //back
        planes[2] = new Plane(vertex[5], new Normal(0, 0, 1).Normalize(), new Color(15, 27, 64));
        //top
        planes[3] = new Plane(vertex[2], new Normal(0, 1, 0).Normalize(), new Color(15, 27, 64));
        //left
        planes[4] = new Plane(vertex[4], new Normal(1, 0, 0).Normalize(), new Color(15, 27, 64));
        //bottom
        planes[5] = new Plane(vertex[0], new Normal(0, 1, 0).Normalize(), new Color(15, 27, 64));

    }

    public double intersection(Ray ray) {
        //System.out.println("intersection test");
        double min = (int) 1e10;
        Color minCol = new Color(0, 0, 0);
        for (int i = 0; i < planes.length; i++) {
            double t = planes[i].intersection(ray);
//            if (t != -1){
//                System.out.println("miss : " +t);
//                return - 1;
//
//            }
//            System.out.println("************ hit : " +t + "****************");
            //double x = (t - 1) * ray.origin.x + t * ray.direction.x;//ray.origin.x + ray.direction.x * t;//(t - 1) * ray.origin.x + t * ray.direction.x;
            //double y = (t - 1) * ray.origin.y + t * ray.direction.y;//ray.origin.y + ray.direction.y * t;//(t - 1) * ray.origin.y + t * ray.direction.y;
            //double z = (t - 1) * ray.origin.z + t * ray.direction.z;//ray.origin.z + ray.direction.z * t;//(t - 1) * ray.origin.z + t * ray.direction.z;

            double x = ray.origin.x + ray.direction.x * t;//(t - 1) * ray.origin.x + t * ray.direction.x;
            double y = ray.origin.y + ray.direction.y * t;//(t - 1) * ray.origin.y + t * ray.direction.y;
            double z = ray.origin.z + ray.direction.z * t;//(t - 1) * ray.origin.z + t * ray.direction.z;

//            if (((i == 0) || (i == 2)) && (x > vertex[0].x) && (x < vertex[1].x) && (y > vertex[0].y) && (y < vertex[2].y) && (t < min)){
//                min = t;
//                System.out.println("hit");
//            } else if (((i == 1) || (i == 4)) && (z > vertex[1].z) && (z < vertex[5].z) && (y > vertex[1].y) && (y < vertex[3].y) && (t < min)){
//                min = t;
//                System.out.println("hit");
//            } else if (((i == 3) || (i == 5)) && (z > vertex[1].z) && (z < vertex[5].z) && (x > vertex[0].x) && (x < vertex[1].x) && (t < min)){
//                min = t;
//                System.out.println("hit");
//            }
//            if (min != (int) 1e10){
//            return min;
//            }
            if ((i == 0) || (i == 2)) {
                //System.out.println("0 or 2: " + i + " @ " + t);
                if (((x > vertex[0].x) && (x < vertex[1].x)) && ((y > vertex[0].y) && (y < vertex[2].y))) {
                    //System.out.println("inside box: " + x + ", " + y );
                    if (t < min) {
                        min = t;
                        minCol = getColor((int) t);
                    }
                    continue;

                }
            } else if ((i == 1) || (i == 4)) {
                //System.out.println("1 or 4: " + i + " @ " + t);
                if (((z > vertex[1].z) && (z < vertex[5].z)) && ((y > vertex[0].y) && (y < vertex[2].y))) {
                    //System.out.println("inside box: " + x + ", " + y );
                    if (t < min) {
                        min = t;
                        minCol = getColor((int) t);
                    }
                    continue;

                } else {
                    //System.out.println("outside box: " + x + ", " + y + " @ " + t );
                    //System.out.println("vertex [0].x = " + vertex[0].x+ ", " + "vertex [1].x = " + vertex[1].x+ ", " + "vertex [0].y = " + vertex[0].y+ ", " + "vertex [2].y = " + vertex[2].y);
                }
            } else if ((i == 3) || (i == 5)) {
                //System.out.println("3 or 5: " + i + " @ " + t);
                if (((x > vertex[0].x) && (x < vertex[1].x)) && ((z > vertex[1].z) && (z < vertex[5].z))) {
                    //System.out.println("inside box: " + x + ", " + y );
                    if (t < min) {
                        min = t;
                        minCol = getColor((int) t);
                    }
                    continue;

                } else {
                    //System.out.println("outside box: " + x + ", " + y + " @ " + t );
                    //System.out.println("vertex [0].x = " + vertex[0].x+ ", " + "vertex [1].x = " + vertex[1].x+ ", " + "vertex [0].y = " + vertex[0].y+ ", " + "vertex [2].y = " + vertex[2].y);
                }

            }

        }
        //System.out.println("miss");
        return min;
    }

    public Color getColor(double t) {

        Color tempColor;

        int red, green, blue;
        final int DIST = 50;

        if (t > 0) {
            if (true) {
                return color;
            }
            if (color.getRed() - t * DIST >= 0) {
                red = (color.getRed() - (int) (t * DIST));
            } else {
                red = 0;
            }
            if (color.getBlue() - t * DIST > 0) {
                blue = color.getBlue() - (int) (t * DIST);
            } else {
                blue = 0;
            }
            if (color.getGreen() - t * DIST > 0) {
                green = color.getGreen() - (int) (t * DIST);
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
