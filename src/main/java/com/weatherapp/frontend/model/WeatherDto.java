package com.weatherapp.frontend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WeatherDto {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("city")
    private String city;
    @JsonProperty("location")
    private String location;
    @JsonProperty("temp")
    private double temp;
    @JsonProperty("unit")
    private String unit;
    @JsonProperty("time")
    private LocalTime time;
    @JsonProperty("date")
    private LocalDate date;
}
