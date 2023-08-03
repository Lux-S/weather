package com.example.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WeatherController {

    private final WeatherService weatherService;
    private final String apiKey;

    public WeatherController(WeatherService weatherService, @Value("${weather.api.key}") String apiKey) {
        this.weatherService = weatherService;
        this.apiKey = apiKey;
    }

    @GetMapping("/getTemperature")
    public ResponseEntity<Map<String, Object>> getTemperature(@RequestParam String city) {
        WeatherResponse weatherResponse = weatherService.getWeather(city, apiKey);
        double temperature = weatherResponse.getMain().getTemp() - 273.15;
        String formattedTemperature = String.format("%.2f", temperature);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("city", city);
        responseData.put("temperature", formattedTemperature);

        return ResponseEntity.ok(responseData);
    }
}
