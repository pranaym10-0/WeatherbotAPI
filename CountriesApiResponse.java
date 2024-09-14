package api;

import java.util.List;

public class CountriesApiResponse {
	
	private String name;
	private String capital;
	private String region;
	private String subregion;
	private Double population;
	private Float area;
	private List<String> timezones;
	private List<String> borders;
	
	// Initialization of all basic information/responses of each country.
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getSubregion() {
		return subregion;
	}
	public void setSubregion(String subregion) {
		this.subregion = subregion;
	}
	public Double getPopulation() {
		return population;
	}
	public void setPopulation(Double population) {
		this.population = population;
	}
	public Float getArea() {
		return area;
	}
	public void setArea(Float area) {
		this.area = area;
	}
	public List<String> getTimezones() {
		return timezones;
	}
	public void setTimezones(List<String> timezones) {
		this.timezones = timezones;
	}
	public List<String> getBorders() {
		return borders;
	}
	public void setBorders(List<String> borders) {
		this.borders = borders;
	}
}
