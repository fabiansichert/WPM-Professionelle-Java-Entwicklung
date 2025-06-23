package de.hs.albsig.fabiansichert.app;

import de.hs.albsig.fabiansichert.core.WeatherApiSimulator.WeatherEntry;
import de.hs.albsig.fabiansichert.core.WeatherService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class WeatherAppUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private final transient WeatherService weatherService;
    private final JLabel temperatureLabel;
    private final JLabel conditionLabel;
    private final JComboBox<String> cityComboBox;
    private final JCheckBox fahrenheitToggle;

    public WeatherAppUI() {
        weatherService = new WeatherService();
        setTitle("Wetter App");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Oben: Datum
        final JLabel dateLabel = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), SwingConstants.CENTER);
        dateLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(dateLabel, BorderLayout.NORTH);

        // Mitte: Wetteranzeige
        final JPanel centerPanel = new JPanel(new GridLayout(3, 1));
        temperatureLabel = new JLabel("Temperatur: ", SwingConstants.CENTER);
        temperatureLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        conditionLabel = new JLabel("Zustand: ", SwingConstants.CENTER);
        cityComboBox = new JComboBox<>();

        // Lade Städte aus der API
        final Map<String, WeatherEntry> allWeather = weatherService.getAllWeather();
        for (final String city : allWeather.keySet()) {
            cityComboBox.addItem(city);
        }

        centerPanel.add(temperatureLabel);
        centerPanel.add(conditionLabel);
        centerPanel.add(cityComboBox);
        add(centerPanel, BorderLayout.CENTER);

        // Unten: Celsius/Fahrenheit Toggle
        fahrenheitToggle = new JCheckBox("Fahrenheit anzeigen");
        fahrenheitToggle.setHorizontalAlignment(SwingConstants.CENTER);
        add(fahrenheitToggle, BorderLayout.SOUTH);

        // Events
        cityComboBox.addActionListener(e -> updateWeather());
        fahrenheitToggle.addActionListener(e -> updateWeather());

        cityComboBox.setSelectedIndex(0);
        updateWeather();

        setVisible(true);
    }

    private void updateWeather() {
        final String selectedCity = (String) cityComboBox.getSelectedItem();
        if (selectedCity == null) {
            return;
        }

        final WeatherEntry entry = weatherService.getWeather(selectedCity);
        final boolean isFahrenheit = fahrenheitToggle.isSelected();
        final double temp = entry.getTemperature(isFahrenheit);
        final String unit = isFahrenheit ? "°F" : "°C";

        temperatureLabel.setText("Temperatur: " + String.format("%.1f", temp) + unit);
        conditionLabel.setText("Zustand: " + entry.getCondition());
    }


}
