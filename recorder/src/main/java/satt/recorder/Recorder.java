package satt.recorder;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import satt.recorder.config.JNativeHookConfiguration;

//@ComponentScan
public class Recorder {

    public static void main(String[] args) {
//        new AnnotationConfigApplicationContext(Recorder.class);
        new JNativeHookConfiguration().setup();
    }

}
