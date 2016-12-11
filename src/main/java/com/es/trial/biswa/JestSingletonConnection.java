package com.es.trial.biswa;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;

public class JestSingletonConnection {

	
	private final static String connectionUrl = "http://localhost:9200/";
	private static JestSingletonConnection INSTANCE = new JestSingletonConnection();
	
	private JestSingletonConnection() {
		//throw new UnsupportedOperationException();
	}
	
	public static JestSingletonConnection getInstance() {
		return INSTANCE;
	}
	
	public static JestClient getJestClient() {
		// Construct a new Jest client according to configuration via factory
		JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(new HttpClientConfig
		       .Builder(connectionUrl)
		       .multiThreaded(true)
		       .build());
		JestClient client = factory.getObject();
		return client;
	}
	
}
