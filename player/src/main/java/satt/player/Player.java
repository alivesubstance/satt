package satt.player;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@Slf4j
@SpringBootApplication
public class Player /*implements CommandLineRunner*/ {

    public static void main(String[] args) {
        log.info("Starting Recorder application");
        new SpringApplicationBuilder(Player.class)
                .web(WebApplicationType.SERVLET)
                .headless(false)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame();
//            frame.setVisible(false);
//        });
//    }
}
