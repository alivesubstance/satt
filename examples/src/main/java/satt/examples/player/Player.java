package satt.examples.player;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Player {
    public static void main(String[] args) {

        try {


            int xCoord = 608;

            int yCoord = 318;

            // Move the cursor

            Robot robot = new Robot();
            robot.setAutoDelay(250);
            robot.mouseMove(xCoord, yCoord);

            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.keyPress(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_A);

            // simulate alt+z
//            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//            robot.keyPress(KeyEvent.VK_ALT);
//            robot.keyPress(KeyEvent.VK_Z);
//            robot.keyRelease(KeyEvent.VK_Z);
//            robot.keyRelease(KeyEvent.VK_ALT);


            // Simulate double mouse click

//            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//            robot.delay(300);
//            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        } catch (AWTException e) {

            System.out.println("Low level input control is not allowed " + e.getMessage());
        }

    }
}
