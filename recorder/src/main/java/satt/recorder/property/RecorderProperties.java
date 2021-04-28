package satt.recorder.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class RecorderProperties {

    @Value("${properties.delayMultiplier}")
    private int delayMultiplier;

    public int getDelayMultiplier() {
        return delayMultiplier;
    }
}
