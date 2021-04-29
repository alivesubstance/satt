package satt.examples.player;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


public class VirtualKeyBoard extends Robot {

    public VirtualKeyBoard() throws AWTException {
        super();
    }

    public void pressKeys(String keysCombination) throws IllegalArgumentException {
        for (String key : keysCombination.split("\\+")) {
            try {
                System.out.println(key);
                this.keyPress((int) KeyEvent.class.getField("VK_" + key.toUpperCase()).getInt(null));

            } catch (IllegalAccessException e) {
                e.printStackTrace();

            } catch (NoSuchFieldException e) {
                throw new IllegalArgumentException(
                        key.toUpperCase() + " is invalid key\n" + "VK_" + key.toUpperCase() + " is not defined in java.awt.event.KeyEvent");
            }
        }
    }


    public void releaseKeys(String keysConbination) throws IllegalArgumentException {

        for (String key : keysConbination.split("\\+")) {
            try { // KeyRelease method inherited from java.awt.Robot
                this.keyRelease((int) KeyEvent.class.getField("VK_" + key.toUpperCase()).getInt(null));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                throw new IllegalArgumentException(
                        key.toUpperCase() + " is invalid key\n" + "VK_" + key.toUpperCase() + " is not defined in java.awt.event.KeyEvent");
            }
        }
    }

    public static void main(String[] args) throws AWTException {


        VirtualKeyBoard kb = new VirtualKeyBoard();
        int xCoord = 935;

        int yCoord = 600;
        kb.setAutoDelay(250);
        kb.mouseMove(xCoord, yCoord);


//        String keyCombination = "control+a"; // select all text on screen
        //String keyCombination = "shift+a+1+c"; // types A!C on screen

        // For your case
//        String keyCombination = "alt+z";

        //----------
        // Mac OS
        //----------
        // command + a
//        String keyCombination = "meta+a";
        // alt + l
        String keyCombination = "alt+l";

        kb.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        kb.pressKeys(keyCombination);
        kb.releaseKeys(keyCombination);


    }


}