package satt.recorder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@ComponentScan
public class Recorder {

    public static void main(String[] args) {
        log.info("Starting Recorder application");

        new AnnotationConfigApplicationContext(Recorder.class);
    }

}
