package com.es.trial.biswa;

import java.util.concurrent.Callable;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.searchbox.client.JestResult;

public class CallableSearcher implements Callable<String>{

	private final String searchField;
	private final String searchQueryString;
	
	public CallableSearcher(String searchField, String searchQueryString) {
		this.searchField = searchField;
		this.searchQueryString = searchQueryString;
	}
	
	public String call() throws Exception {
		StringBuffer sb = new StringBuffer(256);
		SimpleSearch simpleSearch = new SimpleSearch();
		JestResult jestResult = simpleSearch.search(searchField, searchQueryString);
		
		JsonObject jsonObject = jestResult.getJsonObject();
		JsonObject hitsObj = jsonObject.getAsJsonObject("hits");
		JsonArray hitsArray = hitsObj.getAsJsonArray("hits");
		if(hitsArray != null && hitsArray.size() > 0) {
		for(int itr = 0; itr<hitsArray.size(); itr++) {
			JsonElement jsonElem = hitsArray.get(itr);
			JsonObject sourceObj = jsonElem.getAsJsonObject().getAsJsonObject("_source");
			sb.append(sourceObj.get("location").getAsString()).append("-").append(sourceObj.get("office").getAsString()).append(":").append("\n");
			//System.out.println(sourceObj.get("city").getAsString());
			//System.out.println(sourceObj.get("office").getAsString());
		}
		}
		return sb.toString();
	}

}
