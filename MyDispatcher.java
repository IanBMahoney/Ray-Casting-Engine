/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raytracing3;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

/**
 *
 * @author ianmahoney
 */
public class MyDispatcher implements KeyEventDispatcher{
        public Driver driver;
        public MyDispatcher(Driver driver){
            this.driver = driver;
        }
        
        public boolean dispatchKeyEvent(KeyEvent e) {
            synchronized (MyDispatcher.class) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                //System.out.println("tester");
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                //System.out.println("2test2");
            } else if (e.getID() == KeyEvent.KEY_TYPED) {
                //System.out.println("3test3");
            }
            System.out.println(e.getKeyCode());
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                
                break;
            case KeyEvent.VK_BACK_QUOTE:
                
                break;
            case KeyEvent.VK_ENTER:
                //System.out.println("enter");
                //driver.run();
                break;
                
            case KeyEvent.VK_LEFT:
                //driver.origin.x -= 10;
                    //System.out.println("left");
                    break;
            case KeyEvent.VK_RIGHT:        
                //driver.origin.x += 10;
                    //System.out.println("right");
                    break;
        }
            return false;
        }
    }
}
