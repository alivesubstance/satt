package satt.recorder.service;

import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import satt.model.Metadata;
import satt.model.Resolution;
import satt.model.OsType;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;

@Component
public class MetadataService {

    private static final Logger logger = LoggerFactory.getLogger(MetadataService.class);

    public Metadata getMetadata() {
        return new Metadata(
                getUserName(),
                getOs(),
                getResolution()
        );
    }

    private String getUserName() {
        return System.getProperty("user.name");
    }

    private OsType getOs() {
        if (SystemUtils.IS_OS_WINDOWS) {
            return OsType.WINDOWS;
        } else if (SystemUtils.IS_OS_MAC) {
            return OsType.MAC;
        }

        throw new IllegalArgumentException("Unsupported operation type " + System.getProperty("os.name"));
    }

    private Resolution getResolution() {
        try {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            return new Resolution((int)screenSize.getWidth(), (int)screenSize.getHeight());
        } catch (HeadlessException e) {
            logger.error("Failed to get screen resolution", e);
            return new Resolution(-1, -1);
        }
    }

}
