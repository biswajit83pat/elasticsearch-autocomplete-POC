package com.es.trial.biswa;

import java.io.IOException;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;

public class JestConnectionFactoryTest {
	private final static String connectionUrl = "http://localhost:9200/";
	private static final String query="{\n"
			 + "\"query\":{\n" + 
			"\"match\":{\n"+
			 "\"city\":\"kis\"\n"+
			"}\n}\n"
			+ "\n}";

	public static void main(String[] args) throws IOException {
		JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(new HttpClientConfig
		       .Builder(connectionUrl)
		       //.discoveryEnabled(true)
		       .multiThreaded(true)
		       .build());
		JestClient client = factory.getObject();
		Search search = (Search) new Search.Builder(query).addIndex("autocomplete_test").addType("city").build();
		
		System.out.println(client.execute(search).getJsonString());
		client.shutdownClient();
	}
}
