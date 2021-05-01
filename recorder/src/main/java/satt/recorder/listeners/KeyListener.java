package satt.recorder.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jnativehook.keyboard.NativeKeyAdapter;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import satt.model.OsType;
import satt.recorder.handlers.key.KeyEventHandler;
import satt.recorder.service.MetadataService;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class KeyListener extends NativeKeyAdapter {

    private final MetadataService metadataService;
    private final List<KeyEventHandler> keyEventHandlers;

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        OsType os = metadataService.getOs();
        log.trace("keyCode: {}, modifiers: {}, keyText: {}, rawCode: {}, os: {}",
                e.getKeyCode(),
                e.getModifiers(),
                NativeKeyEvent.getKeyText(e.getKeyCode()),
                e.getRawCode(),
                os
        );

        for (KeyEventHandler keyEventHandler : keyEventHandlers) {
            if (keyEventHandler.getOsType() != null && keyEventHandler.getOsType() != os) {
                continue;
            }

            if (keyEventHandler.process(e)) {
                break;
            }
        }


//        if (e.getModifiers() & NativeKeyEvent.META_MASK
//                 && e.getModifiers() & NativeKeyEvent.CTRL_MASK) {
//            System.out.print("Attempting to consume CMD+F9 event...\t");
//        }

        // Cmd + F9
//        if ((e.getModifiers() & NativeKeyEvent.META_MASK) != 0
//                && e.getKeyCode() == NativeKeyEvent.VC_F9) {
//            System.out.print("Attempting to consume CTRL+ALT+DEL event...\t");
//            try {
//                System.out.print("[ OK ]\n");
//            }
//            catch (Exception ex) {
//                System.out.print("[ !! ]\n");
//                ex.printStackTrace();
//            }
//        }

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
    }
}
