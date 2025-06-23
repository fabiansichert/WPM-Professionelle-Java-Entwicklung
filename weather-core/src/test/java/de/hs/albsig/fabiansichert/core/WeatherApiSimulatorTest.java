package de.hs.albsig.fabiansichert.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Map;

public class WeatherApiSimulatorTest {

    @Test
    void testLoadDataNotEmpty() {
        WeatherApiSimulator simulator = new WeatherApiSimulator();
        Map<String, WeatherApiSimulator.WeatherEntry> data = simulator.getAllWeather();
        assertNotNull(data, "Daten sollten nicht null sein");
        assertFalse(data.isEmpty(), "Daten sollten nicht leer sein");
    }

    @Test
    void testGetWeatherForCityExisting() {
        WeatherApiSimulator simulator = new WeatherApiSimulator();
        WeatherApiSimulator.WeatherEntry entry = simulator.getWeatherForCity("Berlin");
        assertNotNull(entry, "Berlin sollte Wetterdaten haben");
        assertEquals("Berlin", "Berlin"); // Optional: nur um zu zeigen
    }

    @Test
    void testGetWeatherForCityNonExisting() {
        WeatherApiSimulator simulator = new WeatherApiSimulator();
        WeatherApiSimulator.WeatherEntry entry = simulator.getWeatherForCity("NichtExistierendeStadt");
        assertNull(entry, "Für nicht existierende Stadt sollte null zurückgegeben werden");
    }

    @Test
    void testWeatherEntryTemperatureConversion() {
        WeatherApiSimulator.WeatherEntry entry = new WeatherApiSimulator.WeatherEntry(0, "Clear");
        assertEquals(0, entry.getTemperature(false), 0.01);
        assertEquals(32, entry.getTemperature(true), 0.01);
    }

    @Test
    void testWeatherDescription() {
        WeatherApiSimulator.WeatherEntry cold = new WeatherApiSimulator.WeatherEntry(-5, "Schnee");
        assertTrue(cold.getWeatherDescription().toLowerCase().contains("sehr kalt"));
        
        WeatherApiSimulator.WeatherEntry cool = new WeatherApiSimulator.WeatherEntry(10, "Regen");
        assertTrue(cool.getWeatherDescription().toLowerCase().contains("kühl"));
        
        WeatherApiSimulator.WeatherEntry warm = new WeatherApiSimulator.WeatherEntry(25, "Sonnig");
        assertTrue(warm.getWeatherDescription().toLowerCase().contains("warm"));
    }
}
