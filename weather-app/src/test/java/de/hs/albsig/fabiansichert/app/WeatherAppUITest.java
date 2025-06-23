package de.hs.albsig.fabiansichert.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherAppUITest {

    @Test
    void testUIInstantiation() {
        WeatherAppUI ui = new WeatherAppUI();
        assertNotNull(ui);
        ui.dispose(); // Schließt das Fenster wieder
    }
}