package de.hs.albsig.fabiansichert.core;

import java.util.Map;

public class WeatherService {
    private final WeatherApiSimulator api;

    public WeatherService() {
        this.api = new WeatherApiSimulator();
    }

    public WeatherService(final WeatherApiSimulator api) {
        this.api = api;
    }

    public WeatherApiSimulator.WeatherEntry getWeather(final String city) {
        return api.getWeatherForCity(city);
    }

    public Map<String, WeatherApiSimulator.WeatherEntry> getAllWeather() {
        return api.getAllWeather();
    }
}

