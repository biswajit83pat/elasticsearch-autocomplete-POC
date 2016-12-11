package com.es.trial.biswa;

import java.io.IOException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.searchbox.client.JestResult;

public class SearchTest {
	public static void main(String[] args) throws IOException {
		SimpleSearch simpleSearch = new SimpleSearch();
		//City search
		JestResult jestResult = simpleSearch.search("location","dia");
		System.out.println(jestResult.getJsonString());
		JsonObject jsonObject = jestResult.getJsonObject();
		JsonObject hitsObj = jsonObject.getAsJsonObject("hits");
		JsonArray hitsArray = hitsObj.getAsJsonArray("hits");
		if(hitsArray != null && hitsArray.size() > 0) {
		for(int itr = 0; itr<hitsArray.size(); itr++) {
			JsonElement jsonElem = hitsArray.get(itr);
			//System.out.println("Element " + jsonElem);
			JsonObject sourceObj = jsonElem.getAsJsonObject().getAsJsonObject("_source");
			System.out.println(sourceObj.get("location").getAsString());
			System.out.println(sourceObj.get("office").getAsString());
		}
		}
		
		//Office search
		jestResult = simpleSearch.search("office","halli");
		System.out.println(jestResult.getJsonString());
		jsonObject = jestResult.getJsonObject();
		hitsObj = jsonObject.getAsJsonObject("hits");
		hitsArray = hitsObj.getAsJsonArray("hits");
		if(hitsArray != null && hitsArray.size() > 0) {
		for(int itr = 0; itr<hitsArray.size(); itr++) {
			JsonElement jsonElem = hitsArray.get(itr);
			//System.out.println("Element " + jsonElem);
			JsonObject sourceObj = jsonElem.getAsJsonObject().getAsJsonObject("_source");
			System.out.println(sourceObj.get("location").getAsString());
			System.out.println(sourceObj.get("office").getAsString());
		}
		}
	}
}
