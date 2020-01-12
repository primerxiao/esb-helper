package com.primer;

import com.primer.common.config.MySplashScreen;
import com.primer.view.MainView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
public class MainApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) throws IOException {
        launch(MainApplication.class, MainView.class, new MySplashScreen(), args);
    }


}
