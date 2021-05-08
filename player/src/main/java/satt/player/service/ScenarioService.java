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

import java.awt.AWTException;
import java.awt.Robot;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScenarioService {

    public void startScenario(Scenario scenario) {
        log.info("Start playing scenario {}", scenario.getId());

        Robot robot = createRobot();
        for (Event event : scenario.getEvents()) {
            switch (event.getType()) {
                case KEY: processKeyEvent((KeyEvent)event, robot); break;
                case MOUSE_CLICK: processMouseClickEvent((MouseClickEvent)event, robot); break;
                case MOUSE_WHEEL: processMouseWheelEvent((MouseWheelEvent)event, robot); break;
                case DELAY: processDelayEvent((DelayEvent)event); break;
                default:
                    throw new IllegalArgumentException("Unknown event type " + event.getType());
            }
        }

        log.info("Finish playing scenario {}", scenario.getId());
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
        robot.mouseWheel(event.getAmount());
        event.getSpecialKeyCodes().forEach(robot::keyRelease);
    }

    private void processMouseClickEvent(MouseClickEvent event, Robot robot) {
        log.trace("Playing mouse click event for button {} and special key codes {}",
                event.getButton(), event.getSpecialKeyCodes()
        );

        robot.mouseMove(event.getX(), event.getY());
        event.getSpecialKeyCodes().forEach(robot::keyPress);
        robot.mousePress(event.getButton());
        robot.mouseRelease(event.getButton());
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
