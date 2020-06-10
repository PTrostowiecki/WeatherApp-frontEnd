package com.weatherapp.frontend.weathergui;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.selection.SingleSelect;
import com.vaadin.flow.router.Route;
import com.weatherapp.frontend.configuration.WeatherDataFromDataBase;
import com.weatherapp.frontend.configuration.WeatherServiceConnection;
import com.weatherapp.frontend.model.TempUnits;
import com.weatherapp.frontend.model.WeatherDto;

@Route("weather")
public class WeatherGuiMain extends VerticalLayout {

    private WeatherServiceConnection serviceConnection = new WeatherServiceConnection();
    private Grid<WeatherDto> grid = new Grid<>(WeatherDto.class);
    private SingleSelect<Grid<WeatherDto>, WeatherDto> select = grid.asSingleSelect();
    private WeatherDataFromDataBase weatherDataFromDataBase = new WeatherDataFromDataBase();

    public WeatherGuiMain()  {

        TextField textFieldCity = new TextField("Type City");
        textFieldCity.setPlaceholder("London");
        TextField textFieldLocation = new TextField("Type Location");
        textFieldLocation.setPlaceholder("GB,DE,PL..just initials");
        ComboBox<TempUnits> tempUnitsComboBox = new ComboBox<>("Units", TempUnits.values());

        Button buttonShowDataBase = new Button("Show history");
        Button buttonCloseDataBase = new Button("Close history");
        Button buttonCheckWeather = new Button("Check Weather");
        Button buttonDeleteWeatherDB = new Button("Delete All");
        Button buttonDeleteWeatherByIdDB = new Button("Delete it");
        Label labelTemp = new Label();

        buttonCheckWeather.addClickListener(buttonClickEvent -> {
                if(serviceConnection.getTemperature(textFieldCity.getValue(),
                                                    textFieldLocation.getValue(),
                                                    tempUnitsComboBox.getValue()) > 0 ) {
                    labelTemp.setText("Temperature is: " + serviceConnection.getTemp());
                }else {
                    labelTemp.setText("Something gone wrong try again");
                }
                refresh();

        });
        buttonDeleteWeatherDB.addClickListener(buttonClickEvent -> {
           weatherDataFromDataBase.deleteAllFromDataBase();
           refresh();
        });

        buttonDeleteWeatherByIdDB.addClickListener(buttonClickEvent -> {
           weatherDataFromDataBase.deleteWeatherByIdFromDataBase(select.getValue().getId());
           refresh();
        });
        grid.setColumns("id","city","location","temp","unit","time","date");
       refresh();
        buttonShowDataBase.addClickListener(buttonClickEvent -> {
           add(grid);
        });
        buttonCloseDataBase.addClickListener(buttonClickEvent -> {
            remove(grid);
        });
        HorizontalLayout gridButtons = new HorizontalLayout(buttonShowDataBase,buttonCloseDataBase,buttonDeleteWeatherDB,buttonDeleteWeatherByIdDB);
        add(textFieldCity,textFieldLocation,tempUnitsComboBox,buttonCheckWeather,gridButtons,labelTemp);

    }

    public void refresh(){
        grid.setItems(weatherDataFromDataBase.getWeatherDataBase());
    }

}
