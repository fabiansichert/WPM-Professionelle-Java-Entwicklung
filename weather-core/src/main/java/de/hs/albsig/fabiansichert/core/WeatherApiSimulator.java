package de.hs.albsig.fabiansichert.core;

import java.io.InputStream;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Locale;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class WeatherApiSimulator {

    private final Map<String, WeatherEntry> data;

    public WeatherApiSimulator() {
        data = loadDataFromJson();
    }

    private static Map<String, WeatherEntry> loadDataFromJson() {
        try (final InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("weather.json")) {
            if (is == null) {
                 throw new IllegalStateException("weather.json not found in resources.");
            }
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(is, new TypeReference<>() {});
        } catch (IOException e) {
           throw new IllegalStateException("Failed to read weather.json", e);
        }
    }

    public WeatherEntry getWeatherForCity(final String city) {
        return data.get(city);
    }

    public Map<String, WeatherEntry> getAllWeather() {
        return new HashMap<>(data);
    }


    public static class WeatherEntry {
        private double temperatureCelsius;
        private String condition;

        /**
         * Default constructor for WeatherEntry.
         * Required for deserialization.
         */
        public WeatherEntry() {
            // No-args constructor for Jackson
        }

        public WeatherEntry(final double temperatureCelsius, final String condition) {
            this.temperatureCelsius = temperatureCelsius;
            this.condition = condition;
        }

        public void setTemperatureCelsius(final double temperatureCelsius) {
            this.temperatureCelsius = temperatureCelsius;
        }

        public void setCondition(final String condition) {
            this.condition = condition;
        }

        public double getTemperatureCelsius() {
            return temperatureCelsius;
        }

        public String getCondition() {
            return condition;
        }

        public double getTemperature(final boolean useFahrenheit) {
            if (useFahrenheit) {
                return temperatureCelsius * 9 / 5 + 32;
            }
            return temperatureCelsius;
        }

        public String getWeatherDescription() {
            if (temperatureCelsius < 0) {
                return "Es ist sehr kalt und " + condition.toLowerCase(Locale.GERMAN);
            } else if (temperatureCelsius < 20) {
                return "Es ist kühl und " + condition.toLowerCase(Locale.GERMAN);
            } else {
                return "Es ist warm und " + condition.toLowerCase(Locale.GERMAN);
            }
        }


        @Override
        public String toString() {
            return "WeatherEntry{" +
                "temperatureCelsius=" + temperatureCelsius +
                ", condition='" + condition + '\'' +
                '}';
        }
    }

}
