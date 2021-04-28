package satt.recorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import satt.recorder.config.JNativeHookConfiguration;

@ComponentScan
public class Recorder {

    private static final Logger logger = LoggerFactory.getLogger(Recorder.class);

    public static void main(String[] args) {
        logger.info("Starting Recorder application");

        new AnnotationConfigApplicationContext(Recorder.class);
//        new JNativeHookConfiguration().setup();
    }

}
