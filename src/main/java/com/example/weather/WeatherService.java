package com.example.weather;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final String WEATHER_API_BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}";

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherResponse getWeather(String city, String apiKey) {
        String apiUrl = WEATHER_API_BASE_URL.replace("{city}", city).replace("{apiKey}", apiKey);
        return restTemplate.getForObject(apiUrl, WeatherResponse.class);
    }
}
