package satt.recorder.service;

import org.springframework.stereotype.Service;

import java.awt.im.InputContext;
import java.util.*;

@Service
public class KeyboardLayoutService {

    public String getKeyboardLocale() {
        return InputContext.getInstance().getLocale().toString();
    }

}
