package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CountriesApi {

	private static final String URL_COUNTRIES_API = "https://restcountries.eu/rest/v2/name/";

	//Creating country and a response using the API and make sure it is found
	public List<String> getCountryInformation(String country) {
		List<String> responseList = new ArrayList();
		try {
			URL url = createUrlWithCountry(country);
			HttpURLConnection httpUrlConnection = createHttpUrlConnection(url);
			String response = callApiAndReadResponseAsString(httpUrlConnection);
			if (!response.isEmpty()) {
				CountriesApiResponse[] countriesApiResponse = transformResponseToBean(response);
				responseList = showCountryInformation(countriesApiResponse);
			} else {
				responseList.add("Country not found!");
			}
		} catch (Exception ex) {
			responseList.add("Country not found!");
		}
		
		return responseList;
	}

	//Show the countries basic information using the API 
	private List<String> showCountryInformation(CountriesApiResponse[] countriesApiResponse) {
		List<String> countriesInfo = new ArrayList<>();
		for (CountriesApiResponse response : countriesApiResponse) {
			StringBuilder timezoneSb = new StringBuilder();
			for (String timezone : response.getTimezones())
				timezoneSb.append(timezone + " ");
			
			StringBuilder bordersSb = new StringBuilder();
			for (String border : response.getBorders())
				bordersSb.append(border + " ");
			
			String countryInfo = "Country is " + response.getName() + " with capital at " + response.getCapital()
				+ " situated in the subregion " + response.getSubregion() + " of " + response.getRegion()
				+ " with a population of " + response.getPopulation() + " and an area of " + response.getArea()
				+ " .The timezones are " + timezoneSb.toString() + " and it is surrounded by the following neighbours "
				+ bordersSb.toString();
			
			countriesInfo.add(countryInfo);
		}

		return countriesInfo;
	}

	//Create a country's response and parse a gson from a json
	private CountriesApiResponse[] transformResponseToBean(String response) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		CountriesApiResponse[] countriesApiResponse = gson.fromJson(response, CountriesApiResponse[].class);
		return countriesApiResponse;
	}

	//Read line
	private String callApiAndReadResponseAsString(HttpURLConnection httpUrlConnection) throws Exception{
		BufferedReader in = in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
		String inputLine = null;
		StringBuffer content = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();
		httpUrlConnection.disconnect();

		return content.toString();
	}

	// Get the json and connect to the URL
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

	//Create the country using the URL
	private URL createUrlWithCountry(String country) {
		String url = URL_COUNTRIES_API + country;
		URL httpUrl = null;
		try {
			httpUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return httpUrl;
	}
}
