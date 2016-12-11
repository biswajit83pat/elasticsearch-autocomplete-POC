package com.es.trial.biswa;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.elasticsearch.search.SearchHit;

public class MainES {
	public static void main(String[] args) {
		// elasticsearchTest
		Node node = NodeBuilder.nodeBuilder().settings(Settings.builder().put("path.home","C:\\Users\\bittu.DESKTOP-CQC0JKE\\Downloads\\elasticsearch-2.3.5\\elasticsearch-2.3.5")).clusterName("elasticsearchTest").node();
		Client client = node.client();

		create(client);
		get(client);
		searchDocument(client, "biswa", "es", "title", "elasticsearch");
		deleteDocument(client, "biswa", "es", "1");
		//node.close();
	}
	
	private static void create(Client client) {
		client.prepareIndex("biswa", "es", "1").setSource(putJsonDocument("Test ES", "Insert into ES", new Date(0), new String[]{"elasticsearch","Biswajit"}, "Biswa")).execute().actionGet();
	}
	
	public static void updateDocument(Client client, String index, String type, String id, String field, String newValue){
		Map<String, Object> updateObject = new HashMap<String, Object>();
		updateObject.put(field, newValue);
        client.prepareUpdate(index, type, id).execute().actionGet();
	}
	
	public static void deleteDocument(Client client, String index, String type, String id){
		DeleteResponse response = client.prepareDelete(index, type, id).execute().actionGet();
		System.out.println("Information on the deleted document:");
		System.out.println("Index: " + response.getIndex());
		System.out.println("Type: " + response.getType());
		System.out.println("Id: " + response.getId());
		System.out.println("Version: " + response.getVersion());
	}
	
	public static void searchDocument(Client client, String index, String type,String field, String value){
		SearchResponse response = client.prepareSearch(index)
				.setTypes(type)
				.setSearchType(SearchType.QUERY_AND_FETCH)
				//.setQuery(fieldQ)
				.setFrom(0).setSize(60).setExplain(true)
				.execute().actionGet();
		
		SearchHit[] results = response.getHits().getHits();
		
		System.out.println("Current results: " + results.length);
        for (SearchHit hit : results) {
        	System.out.println("------------------------------");
        	Map<String,Object> result = hit.getSource();   
            System.out.println(result);
        }
	}

	
	private static void get(Client client) {
		GetResponse getResponse = client.prepareGet("biswa", "es", "1").execute().actionGet();
		
		Map<String, Object> source = getResponse.getSource();
		System.out.println("------------------------------");
		System.out.println("Index: " + getResponse.getIndex());
		System.out.println("Type: " + getResponse.getType());
		System.out.println("Id: " + getResponse.getId());
		System.out.println("Version: " + getResponse.getVersion());
		System.out.println(source);
		System.out.println("------------------------------");
	}

	public static Map<String, Object> putJsonDocument(String title, String content, Date postDate, String[] tags,
			String author) {

		Map<String, Object> jsonDocument = new HashMap<String, Object>();
		jsonDocument.put("title", title);
		jsonDocument.put("conten", content);
		jsonDocument.put("postDate", postDate);
		jsonDocument.put("tags", tags);
		jsonDocument.put("author", author);
		return jsonDocument;
	}

}
