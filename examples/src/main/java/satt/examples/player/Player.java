package satt.examples.player;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.im.InputContext;
import java.util.*;

public class Player {
    public static void main(String[] args) {

        try {
//aa


            int xCoord = 518;
            int yCoord = 309;

            // Move the cursor
            Robot robot = new Robot();
            robot.setAutoDelay(250);
            robot.mouseMove(xCoord, yCoord);
            robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);

            robot.mouseWheel(-10);




//            robot.keyPress(157); // cmd
//            robot.keyPress(65); // a
//            robot.keyRelease(65); //a
//            robot.keyRelease(157); //cmd


            ////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////
//            changeKeyboardLayout(robot);


//            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//            robot.keyPress(KeyEvent.VK_F);
//            robot.keyRelease(KeyEvent.VK_F);


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

    private static void changeKeyboardLayout(Robot robot) {
        // change locale to ENG
        InputContext.getInstance().selectInputMethod(Locale.ENGLISH);
//
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);

        // change locale to RUS
//            Locale locale = new Locale.Builder()
//                    .setLanguage("ru")
//                    .setScript("Cyrl")
//                    .build();
//            InputContext.getInstance().selectInputMethod(locale);


        ///////////////////////////////////////////////////////////////////
        //                                                               //
        // change layout by simulate key combination for layout changing //
        //                                                               //
        ///////////////////////////////////////////////////////////////////
    }
}
