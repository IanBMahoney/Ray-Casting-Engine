package raytracing3;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Input that collects key actions and mouse actions
 *
 * @author Lord Byron's Army
 */
class Input extends KeyAdapter implements MouseListener {

    Driver driver;
    boolean escape = false;
    boolean tilde = false;
    final int LEFT = KeyEvent.VK_LEFT;
    final int RIGHT = KeyEvent.VK_RIGHT;
    final int JUMP = KeyEvent.VK_SPACE;
    final int ATTACK = KeyEvent.VK_Z;

    /**
     * Constructor
     *
     * @param display
     */
    public Input(Driver driver) {
        this.driver = driver;
        //driver.log("Input created");
    }

    /**
     * Runs whenever a key is pressed
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println(e.getKeyCode());
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                escape = true;
                break;
            case KeyEvent.VK_BACK_QUOTE:
                tilde = true;
                break;
            case KeyEvent.VK_ENTER:
                //System.out.println("enter");
                driver.run();
                break;

            case KeyEvent.VK_A:
                driver.origin.x -= 1000 * (Math.cos(Math.toRadians(driver.yaw)));
                driver.origin.z -= 1000 * (Math.sin(Math.toRadians(driver.yaw)));
                //System.out.println("left");
                break;
            case KeyEvent.VK_D:
                driver.origin.x += 1000 * (Math.cos(Math.toRadians(driver.yaw)));
                driver.origin.z += 1000 * (Math.sin(Math.toRadians(driver.yaw)));
                //System.out.println("right");
                break;
            case KeyEvent.VK_W:
                driver.origin.x += 1000 * (Math.sin(Math.toRadians(driver.yaw)));
                driver.origin.z -= 1000 * (Math.cos(Math.toRadians(driver.yaw)));
                //System.out.println("right");

                break;
            case KeyEvent.VK_S:
                driver.origin.x -= 1000 * (Math.sin(Math.toRadians(driver.yaw)));
                driver.origin.z += 1000 * (Math.cos(Math.toRadians(driver.yaw)));
                //System.out.println("right");
                break;
            case KeyEvent.VK_LEFT:
                driver.origin.x -= 1000 * (Math.cos(Math.toRadians(driver.yaw)));
                driver.origin.z -= 1000 * (Math.sin(Math.toRadians(driver.yaw)));
                //System.out.println("left");
                break;
            case KeyEvent.VK_RIGHT:
                driver.origin.x += 1000 * (Math.cos(Math.toRadians(driver.yaw)));
                driver.origin.z += 1000 * (Math.sin(Math.toRadians(driver.yaw)));
                //System.out.println("right");
                break;
            case KeyEvent.VK_UP:
                driver.origin.x += 1000 * (Math.sin(Math.toRadians(driver.yaw)));
                driver.origin.z -= 1000 * (Math.cos(Math.toRadians(driver.yaw)));
                //System.out.println("up");
                break;
            case KeyEvent.VK_DOWN:
                driver.origin.x -= 1000 * (Math.sin(Math.toRadians(driver.yaw)));
                driver.origin.z += 1000 * (Math.cos(Math.toRadians(driver.yaw)));
                //System.out.println("down");
                break;
            case KeyEvent.VK_Q:
                driver.yaw -= 5;

                break;
            case KeyEvent.VK_E:
                driver.yaw += 5;

                break;
            case KeyEvent.VK_R:
                driver.pitch -= 10;
                System.out.println("right");
                break;
            case KeyEvent.VK_T:
                driver.pitch += 10;
                System.out.println("right");
                break;
//            case KeyEvent.VK_7:        
//                driver.yaw += 1;
//                    System.out.println("right");
//                    break;
//            case KeyEvent.VK_9:        
//                driver.yaw -= 10;
//                    System.out.println("right");
//                    break;        
        }

        if (escape && tilde) {
            //display.log("Program terminated");
            System.exit(0);
        }
    }

    public void getInput() {

    }

    /**
     * Runs whenever a key is released
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                escape = false;
                break;
            case KeyEvent.VK_BACK_QUOTE:
                tilde = false;
                break;
        }

    }

    @Override
    public void mouseClicked(MouseEvent evt) {

    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {

    }

}
