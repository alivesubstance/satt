package satt.recorder.listeners;

import org.jnativehook.keyboard.NativeKeyAdapter;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import satt.recorder.handlers.key.KeyEventHandler;

import java.util.*;

@Component
public class KeyListener extends NativeKeyAdapter {

    private List<KeyEventHandler> keyEventHandlers;

    @Autowired
    public KeyListener(List<KeyEventHandler> keyEventHandlers) {
        this.keyEventHandlers = keyEventHandlers;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        for (KeyEventHandler keyEventHandler : keyEventHandlers) {
            if (keyEventHandler.process(e)) {
                break;
            }
        }


        System.out.println("nativeKeyPressed " +
                "e.getKeyCode() : " + e.getKeyCode() +
                ", e.getModifiers():" + e.getModifiers() +
                ", e.getKeyChar():" + e.getKeyChar());
//        if (e.getModifiers() & NativeKeyEvent.META_MASK
//                 && e.getModifiers() & NativeKeyEvent.CTRL_MASK) {
//            System.out.print("Attempting to consume CMD+F9 event...\t");
//        }

        // Cmd + F9
        if ((e.getModifiers() & NativeKeyEvent.META_MASK) != 0
                && e.getKeyCode() == NativeKeyEvent.VC_F9) {
            System.out.print("Attempting to consume CTRL+ALT+DEL event...\t");
            try {
                System.out.print("[ OK ]\n");
            }
            catch (Exception ex) {
                System.out.print("[ !! ]\n");
                ex.printStackTrace();
            }
        }

//        Ctrl + Alt + Delete
//        if ((e.getModifiers() & NativeKeyEvent.CTRL_MASK) != 0
//                && (e.getModifiers() & NativeKeyEvent.ALT_MASK) != 0
//                && e.getKeyCode() == NativeKeyEvent.VC_DELETE) {
//            System.out.print("Attempting to consume CTRL+ALT+DEL event...\t");
//            try {
//                Field f = NativeInputEvent.class.getDeclaredField("reserved");
//                f.setAccessible(true);
//                f.setShort(e, (short) 0x01);
//
//                System.out.print("[ OK ]\n");
//            }
//            catch (Exception ex) {
//                System.out.print("[ !! ]\n");
//                ex.printStackTrace();
//            }
//        }
//
        /* Terminate program when one press ESCAPE */
//        if (e.getKeyCode() == NativeKeyEvent.VK_ESCAPE) {
//            GlobalScreen.unregisterNativeHook();
//        }
    }
}
