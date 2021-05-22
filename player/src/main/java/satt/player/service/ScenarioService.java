package satt.player.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import satt.model.DelayEvent;
import satt.model.Event;
import satt.model.KeyEvent;
import satt.model.MouseClickEvent;
import satt.model.MouseWheelEvent;
import satt.model.Scenario;

import javax.annotation.PostConstruct;
import java.awt.AWTException;
import java.awt.Robot;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScenarioService {

    private final Map<Integer, Integer> mouseButtonMaskByNumber = new HashMap<>(3);

    @PostConstruct
    public void init() {
        mouseButtonMaskByNumber.put(1, java.awt.event.KeyEvent.BUTTON1_DOWN_MASK);
        mouseButtonMaskByNumber.put(2, java.awt.event.KeyEvent.BUTTON2_DOWN_MASK);
        mouseButtonMaskByNumber.put(3, java.awt.event.KeyEvent.BUTTON3_DOWN_MASK);
    }

    public void startScenario(Scenario scenario) {
        log.info("Start playing scenario {}", scenario.getId());

        Robot robot = createRobot();

        for (Event event : scenario.getEvents()) {
            try {
                playEvent(event, robot);
            } catch (Exception e) {
                log.error("Failed to play event {}", event, e);
            }
        }

        log.info("Finish playing scenario {}", scenario.getId());
    }

    private void playEvent(Event event, Robot robot) {
        switch (event.getType()) {
            case KEY:
                processKeyEvent((KeyEvent) event, robot);
                break;
            case MOUSE_CLICK:
                processMouseClickEvent((MouseClickEvent) event, robot);
                break;
            case MOUSE_WHEEL:
                processMouseWheelEvent((MouseWheelEvent) event, robot);
                break;
            case DELAY:
                processDelayEvent((DelayEvent) event);
                break;
            default:
                throw new IllegalArgumentException("Unknown event type " + event.getType());
        }
    }

    private void processDelayEvent(DelayEvent event) {
        log.trace("Playing delay event for {}ms", event.getMillis());

        try {
            Thread.sleep(event.getMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to sleep for " + event.getMillis() + "ms", e);
        }
    }

    private void processMouseWheelEvent(MouseWheelEvent event, Robot robot) {
        log.trace("Playing mouse wheel event for amount {} and special key codes {}",
                event.getAmount(), event.getSpecialKeyCodes()
        );

        event.getSpecialKeyCodes().forEach(robot::keyPress);
        // JNativeHook amount and amount of mouse wheels for robot have different signs
        // in order to make mouse wheel movements consistent
        // the sign should be change to opposite
        robot.mouseWheel(-1 * event.getAmount());
        event.getSpecialKeyCodes().forEach(robot::keyRelease);
    }

    private void processMouseClickEvent(MouseClickEvent event, Robot robot) {
        int mouseButtonMask = mouseButtonMaskByNumber.get(event.getButton());
        log.trace("Playing mouse click event for button {}(mask {}) and special key codes {}",
                event.getButton(), mouseButtonMask, event.getSpecialKeyCodes()
        );

        robot.mouseMove(event.getX(), event.getY());
        event.getSpecialKeyCodes().forEach(robot::keyPress);
        robot.mousePress(mouseButtonMask);
        robot.mouseRelease(mouseButtonMask);
        event.getSpecialKeyCodes().forEach(robot::keyRelease);
    }

    private void processKeyEvent(KeyEvent event, Robot robot) {
        log.trace("Playing key event with locale {} and key codes {}", event.getLocale(), event.getKeyCodes());

        event.getKeyCodes().forEach(robot::keyPress);
        event.getKeyCodes().forEach(robot::keyRelease);
    }

    private Robot createRobot() {
        try {
            return new Robot();
        } catch (AWTException e) {
            throw new RuntimeException("Failed to create AWT robot", e);
        }
    }

}
