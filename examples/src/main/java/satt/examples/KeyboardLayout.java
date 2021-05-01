package satt.examples;

import java.awt.im.InputContext;
import java.util.*;

public class KeyboardLayout {

    public static void main(String[] args) {
        showCurrentKeyboardLayout();

        InputContext.getInstance().selectInputMethod(Locale.ENGLISH);
    }

    private static void showCurrentKeyboardLayout() {
        Locale locale = InputContext.getInstance().getLocale();
        System.out.println(locale);
    }

}
