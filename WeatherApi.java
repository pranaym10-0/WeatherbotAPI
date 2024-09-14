package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WeatherApi {

	// Declare the url and the API key
	private static final String URL_WEATHER_API = "http://api.openweathermap.org/data/2.5/weather?";
	private static final String API_KEY = "00cc956221b3bf8e91a6c3b888878cf9";

	//This method gets the Weather API and determines if it a valid city or not
	public String getWeather(String city) {
		String weatherResponse = null;
		try {
			URL cityUrl = createUrlWithCity(city);
			HttpURLConnection httpUrlConnection = createHttpUrlConnection(cityUrl);
			String response = callApiAndReadResponseAsString(httpUrlConnection);
			if (!response.isEmpty()) {
				WeatherApiResponse weatherApiResponse = transformResponseToBean(response);
				weatherResponse = showWeather(weatherApiResponse);
			} else {
				weatherResponse = "Please give me a valid City!";
			}
		}catch(Exception ex) {
			weatherResponse = "Please give me a valid City!";
		}
		
		return weatherResponse;
	}

	//This method gets the Weather API and determines if it a valid zip or not
	public String getWeather(Integer zipCode) {
		String weatherResponse = null;
		try {
			URL zipCodeUrl = createUrlWithZipCode(zipCode);
			HttpURLConnection httpUrlConnection = createHttpUrlConnection(zipCodeUrl);
			String response = callApiAndReadResponseAsString(httpUrlConnection);
			if (!response.isEmpty()) {
				WeatherApiResponse weatherApiResponse = transformResponseToBean(response);
				weatherResponse =  showWeather(weatherApiResponse);
			} else {
				weatherResponse = "Please give me a valid zip code!";
			}
		}catch(Exception ex) {
			weatherResponse = "Please give me a valid zip code!";
		}

		return weatherResponse;
	}

	// Display information about the Weather through the Weather API
	private String showWeather(WeatherApiResponse response) {
		String weatherInformation = "Weather in: " + response.getName() + " is "
				+ response.getWeather().get(0).getMain() + " | " + response.getWeather().get(0).getDescription()
				+ ", with a temperature of " + response.getMain().getTemp() + " ,minimum temperature of "
				+ response.getMain().getTemp_min() + " and maximum temperature of " + response.getMain().getTemp_max()
				+ ". The humidity is " + response.getMain().getHumidity() + " and the pressure is "
				+ response.getMain().getPressure();
		return weatherInformation;
	}

	//Tranform the json to a gson
	private WeatherApiResponse transformResponseToBean(String response) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		WeatherApiResponse weatherApiResponse = gson.fromJson(response, WeatherApiResponse.class);
		return weatherApiResponse;
	}

	//Input the lines read
	private String callApiAndReadResponseAsString(HttpURLConnection httpUrlConnection) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
		String inputLine = null;
		StringBuffer content = new StringBuffer();
		
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();
		httpUrlConnection.disconnect();

		return content.toString();
	}

	//Create a zip code using the API Key and URL
	private URL createUrlWithZipCode(Integer zipCode) {
		String url = URL_WEATHER_API + "zip=" + zipCode + "&appid=" + API_KEY;
		URL httpUrl = null;
		try {
			httpUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return httpUrl;
	}

	//Create a city using the API Key and URL
	private URL createUrlWithCity(String city) {
		String url = URL_WEATHER_API + "q=" + city + "&appid=" + API_KEY;
		URL httpUrl = null;
		try {
			httpUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return httpUrl;
	}

	//Create requests using the Connection 
	private HttpURLConnection createHttpUrlConnection(URL url) {
		HttpURLConnection httpUrlConnection = null;
		try {
			httpUrlConnection = (HttpURLConnection) url.openConnection();
			httpUrlConnection.setRequestMethod("GET");
			httpUrlConnection.setRequestProperty("Content-Type", "application/json");
			httpUrlConnection.setConnectTimeout(5000);
			httpUrlConnection.setReadTimeout(5000);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return httpUrlConnection;
	}
}
