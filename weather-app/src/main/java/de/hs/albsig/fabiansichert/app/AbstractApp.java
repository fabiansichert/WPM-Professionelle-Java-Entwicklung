package de.hs.albsig.fabiansichert.app;

import org.apache.log4j.Logger;

public abstract class AbstractApp {

    private static final Logger LOGGER = Logger.getLogger(AbstractApp.class);

    public static void main(final String[] args) {

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Weather App gestartet");
        }

        new WeatherAppUI();
    }
}