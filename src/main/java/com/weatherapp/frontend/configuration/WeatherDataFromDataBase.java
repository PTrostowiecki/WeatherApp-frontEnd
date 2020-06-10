package com.weatherapp.frontend.configuration;

import com.weatherapp.frontend.model.WeatherDto;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class WeatherDataFromDataBase {


    private RestTemplate restTemplate = new RestTemplate();

    public List<WeatherDto> getWeatherDataBase(){

        try{
           WeatherDto[] response = restTemplate.getForObject("https://afternoon-forest-83004.herokuapp.com/forecast/getweatherlist",WeatherDto[].class);
            return Arrays.asList(ofNullable(response).orElse(new WeatherDto[0]));
        }catch(RestClientException e){
            return new ArrayList<>();
        }

    }

    public void deleteAllFromDataBase(){

        restTemplate.delete("https://afternoon-forest-83004.herokuapp.com/forecast/deleteall");
    }

    public void deleteWeatherByIdFromDataBase(Long id){
        restTemplate.delete("https://afternoon-forest-83004.herokuapp.com/forecast/deletebyid?id="+id);
    }

}
