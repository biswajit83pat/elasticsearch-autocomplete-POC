package com.es.trial.biswa.dataimporter;

import java.io.IOException;

import com.es.trial.biswa.JestSingletonConnection;

import io.searchbox.core.Index;

public class DataImporter {

	
	public static void main(String[] args) throws IOException {
		String json1 = "{\"location\" : \"Delhi\" , \"office\":\"jamimilitan opo\"}";
		String json2 = "{ \"location\" : \"Islamabad\" , \"office\":\"Dum Dum\"}";
		String json3 = "{ \"location\" : \"Pakistan\" , \"office\":\"Burrabazar\"}";
		String json4 = "{ \"location\" : \"Khazakistan\" , \"office\":\"Marathahalli\"}";
		String json5 = "{ \"location\" : \"India\" , \"office\":\"Chinnapanahalli\"}";
		String json6 = "{ \"location\" : \"Dias el Hope\" , \"office\":\"Sheshanahalli\"}";
		String json7 = "{ \"location\" : \"Bangalore\" , \"office\":\"CV Raman Nagar\"}";
		String json8 = "{ \"location\" : \"Kolkata\" , \"office\":\"Lokish\"}";
		String json9 = "{ \"location\" : \"Amsterdam\" , \"office\":\"Shivajdiagar\"}";
		String json10 = "{ \"location\" : \"Mexico\" , \"office\":\"Kalkaji Nagar\"}";
		
		String indexName = "biswa_index";
		String typeName = "cacheType";
		
		indexDocument(json1, indexName, typeName);
		indexDocument(json2, indexName, typeName);
		indexDocument(json3, indexName, typeName);
		indexDocument(json4, indexName, typeName);
		indexDocument(json5, indexName, typeName);
		indexDocument(json6, indexName, typeName);
		indexDocument(json7, indexName, typeName);
		indexDocument(json8, indexName, typeName);
		indexDocument(json9, indexName, typeName);
		indexDocument(json10, indexName, typeName);
		
	}
	
	public static void indexDocument(String jsonStringToBeIndexed, String indexName, String typeName) throws IOException {
		Index index = new Index.Builder(jsonStringToBeIndexed).index(indexName).type(typeName).build();
		JestSingletonConnection.getInstance().getJestClient().execute(index);
	}
}
