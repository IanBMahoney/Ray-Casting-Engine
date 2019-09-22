/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raytracing3;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyboardFocusManager;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author Ian Mahoney, Alex Truman
 */
public class Driver extends Canvas {

    public final int WIDTH = 100;
    public final int HEIGHT = 100;
    public final int RESOLUTION = 4;
    public final boolean RENDER_TIME = true;
    public boolean running = true;
    public final int FPS = 100;
    public final int UPS = 100;
    public Input input;
    public Timer timer;
    public KeyboardFocusManager manager;
    public String fps = "";
    public BufferedImage frame = new BufferedImage(WIDTH * RESOLUTION, HEIGHT * RESOLUTION, BufferedImage.TYPE_INT_RGB);
    public BufferedImage graphX = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
    public BufferedImage graphY = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
    public Point3D origin;
    public Vector3D direction;
    public int yaw, pitch;

    //public Cube plane, plane2;
    public Cube[] cubes = new Cube[4];

    public Driver() {

        input = new Input(this);

        this.addMouseListener(input);
        this.addKeyListener(input);
        this.requestFocus();
//        Set<AWTKeyStroke> set = this.getFocusTraversalKeys(KeyboardFocusManager.UP_CYCLE_TRAVERSAL_KEYS);
//        
//        set = new HashSet(set);
//        KeyStroke up = KeyStroke.getKeyStroke("A");
//        set.add(up);
//        System.out.println(set);
//        this.setFocusTraversalKeys(KeyboardFocusManager.UP_CYCLE_TRAVERSAL_KEYS, set);

        //tick();
        manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher(this));
        intitializeEnvironment();

        // plane2   = new Plane( new Point3D(200,120,100), new Normal(100,0,0), new Color(15,27,64));
        origin = new Point3D(0, 0, 11000);
        //direction = new Vector3D(0,0,0);
        yaw = 0;
        pitch = 0;

    }

    public void run() {
        System.out.println("run");

        this.timer = new Timer(0, new ActionListener() {
            long timer = System.currentTimeMillis();
            long initialTime = System.nanoTime();
            final double timeU = 1000000000 / UPS;
            final double timeF = 1000000000 / FPS;
            double deltaU = 0, deltaF = 0;
            int frames = 0, ticks = 0;

            public void actionPerformed(ActionEvent e) {

                long currentTime = System.nanoTime();
                deltaU += (currentTime - initialTime) / timeU;
                deltaF += (currentTime - initialTime) / timeF;
                initialTime = currentTime;

                if (deltaU >= 1) {
                    //input.getInput();

//            update();
                    tick();
                    ticks++;
                    deltaU--;
                }

                if (deltaF >= 1) {
                    render();
                    frames++;
                    deltaF--;
                }

                if (System.currentTimeMillis() - timer > 1000) {
                    if (RENDER_TIME) {
                        fps = String.format("UPS: %s, FPS: %s", ticks, frames);
                        System.out.println(String.format("UPS: %s, FPS: %s", ticks, frames));
                    }
                    frames = 0;
                    ticks = 0;
                    timer += 1000;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        timer.start();
    }

    public void intitializeEnvironment() {
        //long start = System.nanoTime();
//        cubes[0] = new Cube(new Point3D(2000, 1000, 200), 1000, 1000, 100, new Color(255, 27, 64));
//        cubes[1] = new Cube(new Point3D(1500, 1500, 210), 1000, 1000, 100, new Color(0, 127, 164));
//        cubes[2] = new Cube(new Point3D(3000, 1000, 255), 1000, 1000, 100, new Color(255, 255, 255));
        cubes[0] = new Cube(new Point3D(0, 1000, 0), 1000, 1000, 1000, new Color(255, 27, 64));
        cubes[1] = new Cube(new Point3D(0, 2000, 0), 1000, 1000, 1000, new Color(0, 127, 164));
        cubes[2] = new Cube(new Point3D(1000, 1000, 0), 1000, 1000, 1000, new Color(255, 255, 255));
        cubes[3] = new Cube(new Point3D(1000, 2000, 0), 1000, 1000, 1000, new Color(0, 255, 0));
//        cubes[4] = new Cube(new Point3D(1500, 1500, 210), 1000, 1000, 100, new Color(0, 127, 164));
//        cubes[5] = new Cube(new Point3D(3, 10, 255), 1000, 1000, 100, new Color(255, 255, 0));
//        cubes[6] = new Cube(new Point3D(20, 100, -200), 1000, 1000, 100, new Color(255, 27, 64));
//        cubes[7] = new Cube(new Point3D(150, 500, 210), 1000, 1000, 100, new Color(0, 127, 164));
//        cubes[8] = new Cube(new Point3D(1000, 400, -1000), 1000, 1000, 100, new Color(255, 255, 255));
//        cubes[9] = new Cube(new Point3D(200, 1000, 200), 1000, 1000, 100, new Color(255, 27, 64));

        //long end = System.nanoTime();
        // System.out.println("Initialization Time: " + (end - start) / 10000000000.0F + " sec");
//        BufferedImage level = loadImage("/rayTracing1/levels/0.png");
//         File file = new File("src/images/image.png");
////
//        try {
//            ImageIO.write(level, "PNG", file);
//        } catch (Exception e) {
//                System.out.println("error##################################################");
//        }
//        int i = 0;
//        for (int x = 0; x < 40; x++){
//            for (int y = 0; y < 20; y++){
//                System.out.println(i +", "+frame.getRGB(x,y));
//                if (new Color(frame.getRGB(x,y)) != Color.WHITE){
//                    cubes[i] = new Cube(new Point3D(x *10, 0, y*10), 10, 10, 10, new Color(frame.getRGB(x, y)));
//                }
//                
//                i++;
//                if (i==200){
//                    return;
//                }
        //  }
        //}
    }

    public BufferedImage loadImage(String fileName) {
        BufferedImage bi = null;
        //System.out.println(getClass().toString());
        try {
            bi = ImageIO.read(getClass().getResourceAsStream(fileName));
        } catch (Exception e) {
            System.out.println("Loading image \"" + fileName + "\" failed: " + e);
            //e.printStackTrace();
        }

        return bi;
    }

    public void tick() {
        //long start = System.nanoTime();
        //System.out.println("location: " + origin.x + ", " + origin.y + ", " + origin.z);
//        if ((yaw >= 360)||(yaw <= 0)) {
//            yaw = 0;
//        }
        //yaw = 0;
        // pitch += 1;
        direction = new Vector3D(100 * (Math.sin(Math.toRadians(yaw))), 0, - 100 * (Math.cos((Math.toRadians(yaw)))));
        //direction = new Point3D(10 * (Math.sin(Math.toRadians(yaw))),0, 10 * (Math.cos((Math.toRadians(yaw)))));
        //System.out.println(yaw + ", x: " + 10 * (Math.sin((Math.toRadians(yaw)))) + " z: " + 10 * (Math.cos(Math.toRadians(yaw))));
        //direction = new Vector3D(10,0,0);
        //origin = origin.add(new Point3D(1,0,0));
        //direction = direction.add(new Vector3D(-100,0,0));
//        yaw = 0;
//          System.out.println(yaw + ", x: " + 10 * (Math.sin((Math.toRadians(yaw)))) + " y: " + 0 + " z: " + 10 * (Math.cos(Math.toRadians(yaw))));
//        yaw = 90;
//        System.out.println(yaw + ", x: " + 10 * (Math.sin((Math.toRadians(yaw)))) + " y: " + 0 + " z: " + 10 * (Math.cos(Math.toRadians(yaw))));
//        yaw = 180;
//        System.out.println(yaw + ", x: " + 10 * (Math.sin((Math.toRadians(yaw)))) + " y: " + 0 + " z: " + 10 * (Math.cos(Math.toRadians(yaw))));
//        yaw = 270;
//        System.out.println(yaw + ", x: " + 10 * (Math.sin((Math.toRadians(yaw)))) + " y: " + 0 + " z: " + 10 * (Math.cos(Math.toRadians(yaw))));
//        yaw = 360;
//        System.out.println(yaw + ", x: " + 10 * (Math.sin((Math.toRadians(yaw)))) + " y: " + 0 + " z: " + 10 * (Math.cos(Math.toRadians(yaw))));
        //h.drawLine(50, 0, 50, 100);
        //h.drawLine(0, 50, 100, 50);

        for (double y = -HEIGHT / 2; y < HEIGHT / 2; y += .25) {

            for (double x = -WIDTH / 2; x < WIDTH / 2; x += .25) {
                //System.out.println(x);
                //System.out.println(y);
                double min = (int) 1e10;
                //Color minCol = new Color(70, 70, 70);
                Color minCol = new Color(0, 0, 0);
                double t = 0;
                for (int i = 0; i < cubes.length; i++) {
                    t = cubes[i].intersection(new Ray(origin, new Vector3D(x * (Math.cos(Math.toRadians(yaw))), y, x * (Math.sin(Math.toRadians(yaw)))).add(direction)));
                    //t = cubes[i].intersection(new Ray(origin.add(new Point3D(x  * (Math.cos(Math.toRadians(yaw))) , y, x * (Math.sin(Math.toRadians(yaw))) )), new Vector3D(x  * (Math.cos(Math.toRadians(yaw))) , y, x * (Math.sin(Math.toRadians(yaw))) ).add(direction)) );
                    //t = cubes[i].intersection(new Ray(origin.add(new Point3D(x  * (Math.cos(Math.toRadians(yaw))) , y, x * (Math.sin(Math.toRadians(yaw)))).add(direction)), new Vector3D(x,y,10)));
                    if (t < min) {
                        min = t;
                        minCol = cubes[i].getColor(t);
                    }
                }

                frame.setRGB((int) (RESOLUTION * x) + (RESOLUTION * WIDTH / 2), (int) (RESOLUTION * y) + (RESOLUTION * HEIGHT / 2), minCol.getRGB());

//                frame.setRGB(x, y, plane2.getColor(plane2.intersection(new Ray(origin, new Vector3D(x, y, 10).add(direction)))).getRGB());
//                if ((x / 2 < 100) && (plane2.intersection(new Ray(origin, new Vector3D(x, y, 10).add(direction))) / 2 < 100)) {
//                    graphX.setRGB(x / 2, plane2.intersection(new Ray(origin, new Vector3D(x, y, 10).add(direction))) / 2, Color.BLUE.getRGB());
//                }
//                if ((y / 2 < 100) && (plane2.intersection(new Ray(origin, new Vector3D(x, y, 10).add(direction))) / 2 < 100)) {
//                    graphY.setRGB(plane2.intersection(new Ray(origin, new Vector3D(x, y, 10).add(direction))) / 2, y / 2, Color.BLUE.getRGB());
//                }
//frame.setRGB(x,y, plane.getColor(plane.intersection(new Ray(new Point3D(0,0,0),new Vector3D(x,y,10)))).getRGB());
                //System.out.println(plane.intersection(new Ray(new Point3D(0,0,0),new Vector3D(x,y,10))));
            }
        }

        Graphics g = frame.getGraphics();
        g.setColor(Color.WHITE);
        g.drawString(fps, 10, 10);
        g.drawString("location: " + origin.x + ", " + origin.y + ", " + origin.z, 10, 20);
        g.drawString("Pitch: " + pitch + " Yaw: " + yaw, 10, 30);
        //long end = System.nanoTime();
        //System.out.println("Tick Time: " + (end - start) / 10000000000.0F + " sec");
    }

    public void render() {
        //long start = System.nanoTime();
        Graphics g = this.getGraphics();
        Graphics f = frame.getGraphics();

        BufferedImage resized = new BufferedImage(this.getWidth() + 1, this.getHeight() + 1, frame.getType());
        Graphics2D g2 = resized.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(frame, 0, 0, this.getWidth() + 1, this.getHeight() + 1, 0, 0, frame.getWidth(),
                frame.getHeight(), null);
        g2.dispose();
        g.drawImage(resized, 0, 0, null);
        //g.drawImage(graphX, 0, 0, null);
        //g.drawImage(graphY, 110, 0, null);

//        g.setColor(Color.BLACK);
//        g.fillRect(5, 5, fps.length()*7, 12);
//        g.setColor(Color.WHITE);
//        g.drawString(fps,10,16);
        g.dispose();
//        File file = new File("src/images/image.png");
//
//        try {
//            ImageIO.write(graphX, "PNG", file);
//        } catch (Exception e) {
//
//        }
//        File file2 = new File("src/images/image2.png");
//
//        try {
//            ImageIO.write(graphY, "PNG", file);
//        } catch (Exception e) {
//
//        }
        //long end = System.nanoTime();
        //System.out.println("Render Time: " + (end - start) / 10000000000.0F + " sec");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        JFrame win = new JFrame("GraphicChallenge2: Clock");
//        win.setSize(800, 800);
//        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        Driver canvas = new Driver();
//        win.add(canvas);
//        win.setLocationRelativeTo(null);
//        win.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//
//        //win.pack(); 
//        win.setVisible(true);
        JFrame frame = new JFrame("Ray Casting");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(new BorderLayout());

        frame.add(new Driver(), BorderLayout.CENTER);

        frame.setResizable(true);

        frame.setVisible(true);

        frame.pack();

        frame.setLocationRelativeTo(null);

    }

}
