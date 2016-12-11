package com.es.trial.biswa;

import java.io.IOException;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Search;

public class SimpleSearch {
	
	private static final String query="q=location:kis&pretty=True";
	
	public JestResult search(String queryField, String queryString) throws IOException {
		Search search = (Search) new Search.Builder("{\n"
				 + "\"query\":{\n" + 
					"\"match\":{\n"+
					 "\"" + queryField + "\":\"" + queryString + 
					 "\"\n"+
						"}\n}\n"
						+ "\n}")
                // multiple index or types can be added.
                .addIndex("biswa_index").addType("cacheType")
                .build();

		return JestSingletonConnection.getInstance().getJestClient().execute(search);
	}
}
