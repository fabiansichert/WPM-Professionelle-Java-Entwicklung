package de.hs.albsig.fabiansichert.core;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class WeatherServiceTest {

    private WeatherApiSimulator mockApi;
    private WeatherService service;

    @BeforeEach
    public void setUp() {
        mockApi = Mockito.mock(WeatherApiSimulator.class);
        service = new WeatherService(mockApi);
    }

    @Test
    public void testGetWeather() {
        WeatherApiSimulator.WeatherEntry entry = new WeatherApiSimulator.WeatherEntry(15.0, "Bewölkt");
        when(mockApi.getWeatherForCity("Berlin")).thenReturn(entry);

        WeatherApiSimulator.WeatherEntry result = service.getWeather("Berlin");

        assertNotNull(result);
        assertEquals(15.0, result.getTemperatureCelsius());
        assertEquals("Bewölkt", result.getCondition());
    }

    @Test
    public void testGetAllWeather() {
        Map<String, WeatherApiSimulator.WeatherEntry> fakeData = new HashMap<>();
        fakeData.put("Berlin", new WeatherApiSimulator.WeatherEntry(10.0, "Sonnig"));

        when(mockApi.getAllWeather()).thenReturn(fakeData);

        Map<String, WeatherApiSimulator.WeatherEntry> result = service.getAllWeather();

        assertEquals(1, result.size());
        assertTrue(result.containsKey("Berlin"));
        assertEquals("Sonnig", result.get("Berlin").getCondition());
    }
}
