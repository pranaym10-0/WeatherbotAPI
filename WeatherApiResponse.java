package api;

import java.util.List;

public class WeatherApiResponse {

	private String name;
	private List<Weather> weather;
	private Temperature main;
	private Integer cod;
	
	//Initialization of all basic information/responses of the weather.
	public List<Weather> getWeather() {
		return weather;
	}
	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}
	public Temperature getMain() {
		return main;
	}
	public void setMain(Temperature main) {
		this.main = main;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCod() {
		return cod;
	}
	public void setCod(Integer cod) {
		this.cod = cod;
	}
}

class Weather {
	private Integer id;
	private String main;
	private String description;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

class Temperature {
	private Float temp;
	private Float pressure;
	private Float humidity;
	private Float temp_min;
	private Float temp_max;
	
	public Float getTemp() {
		return temp;
	}
	public void setTemp(Float temp) {
		this.temp = temp;
	}
	public Float getPressure() {
		return pressure;
	}
	public void setPressure(Float pressure) {
		this.pressure = pressure;
	}
	public Float getHumidity() {
		return humidity;
	}
	public void setHumidity(Float humidity) {
		this.humidity = humidity;
	}
	public Float getTemp_min() {
		return temp_min;
	}
	public void setTemp_min(Float temp_min) {
		this.temp_min = temp_min;
	}
	public Float getTemp_max() {
		return temp_max;
	}
	public void setTemp_max(Float temp_max) {
		this.temp_max = temp_max;
	}
}
